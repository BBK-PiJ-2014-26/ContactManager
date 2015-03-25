import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Implements the interface ContactManager.
 *
 * @author Gareth Moore
 */
public class ContactManagerImpl implements ContactManager {
	/**
	 * A data structure to store contacts.
	 */
	Set<Contact> contacts = new HashSet<Contact>();
	/**
	 * A data structure to store future meetings.
	 * Using FutureMeetinImpl instead of PastMeeting because
	 * FutureMeeting does not contain instance variables.
	 */
	List<FutureMeeting> futureMeetings = new ArrayList<FutureMeeting>();
	/**
	 * A data structure to store past meetings.
	 * Using PastMeetinImpl instead of PastMeeting because
	 * PastMeeting does not contain instance variables.
	 */
	List<PastMeeting> pastMeetings = new ArrayList<PastMeeting>();

	public int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		if (date.compareTo(new GregorianCalendar()) < 0) { //Compares the provided date to the current date and time.
			throw new IllegalArgumentException();
		} else if (!this.contacts.containsAll(contacts) || contacts.isEmpty()) {
						//Validates the supplied sets of contacts.
						//If a contact is not contained within the ContactManager, an exception is thrown.
						//If the set empty, an exception is also thrown.
			throw new IllegalArgumentException();
		} else {
			FutureMeeting newMeeting = new FutureMeetingImpl(contacts, date);
			futureMeetings.add(newMeeting);
			int result = newMeeting.getId();
			return result;
		}
	}

	/**
	 * Verfies whether the Meeting id exists in the pastMeetings list.
	 *
	 * @param id, id to be verfied.
	 * @return true if the meeting is contained within the list.
	 */
	public boolean containsPastMeetingId(int id) {
		boolean result = false;
		Iterator<PastMeeting> listIterator = pastMeetings.iterator();
			//Must be PastMeetingImpl because PastMeeting interface does not have an id variable.
		boolean finished = false;
		while (!finished) {
			if (listIterator.hasNext()) { //Tests whether the end of the list has been reached.
				PastMeeting temp = listIterator.next();
				if (temp.getId() == id) { //Tests whether the current iteration's id matches parameter.
					result = true;
					finished = true;
				}
			} else {
				finished = true;
			}
		}
		return result;
	}

	/**
	 * Verfies whether the Meeting id exists in the futureMeetings list.
	 *
	 * @param id, id to be verfied.
	 * @return true if the meeting is contained within the list.
	 */
	public boolean containsFutureMeetingId(int id) {
		boolean result = false;
		Iterator<FutureMeeting> listIterator = futureMeetings.iterator();
			//Must be MeetingImpl because Meeting interface does not have an id variable.
		boolean finished = false;
		while (!finished) {
			if (listIterator.hasNext()) { //Tests whether the end of the list has been reached.
				FutureMeeting temp = listIterator.next();
				if (temp.getId() == id) { //Tests whether the current iteration's id matches parameter.
					result = true;
					finished = true;
				}
			} else {
				finished = true;
			}
		}
		return result;
	}

	public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {
		PastMeeting result = null;
		if (containsFutureMeetingId(id)) {
			//Checks if the requested meeting id is a FutureMeeting
			throw new IllegalArgumentException();
		} else {
			Iterator<PastMeeting> listIterator = pastMeetings.iterator();
			boolean finished = false;
			while (!finished) {
				if(listIterator.hasNext()) { //Tests whether the end of the list has been reached.
					PastMeeting temp = listIterator.next();
					if (temp.getId() == id) { //Tests whether the current iteration's id matches parameter.
						result = temp;
						finished = true;
					}
				} else {
					finished = true;
				}
			}
		}
		return result;
	}

	public FutureMeeting getFutureMeeting(int id) {
		FutureMeeting result = null;
		if (containsPastMeetingId(id)) {
			//Checks if the requested meeting id is a PastMeeting
			throw new IllegalArgumentException();
		} else {
			Iterator<FutureMeeting> listIterator = futureMeetings.iterator();
			boolean finished = false;
			while (!finished) {
				if(listIterator.hasNext()) { //Tests whether the end of the list has been reached.
					FutureMeeting temp = listIterator.next();
					if (temp.getId() == id) { //Tests whether the current iteration's id matches parameter.
						result = temp;
						finished = true;
					}
				} else {
					finished = true;
				}
			}
		}
		return result;
	}

	public Meeting getMeeting(int id) {
		Meeting result = null;
		try {
			if (containsPastMeetingId(id)) {
				result = getPastMeeting(id);
			} else if (containsFutureMeetingId(id)) {
				result = getFutureMeeting(id);
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public List<Meeting> getFutureMeetingList(Contact contact) throws IllegalArgumentException  {
		if (!contacts.contains(contact)) { //Checks whether the contact is a member of the set contacts.
			throw new IllegalArgumentException();
		} else {
			List<Meeting> result = new ArrayList<Meeting>();
			Iterator<FutureMeeting> listIterator = futureMeetings.iterator();
			while (listIterator.hasNext()) {
				FutureMeeting tempMeeting = listIterator.next();
				Set<Contact> tempSet = tempMeeting.getContacts();
				if (tempSet.contains(contact)) { //Checks whether the contact is attending this meeting
					result.add(tempMeeting); //If contact is attending the meeting, the meeting is added to our list.
				}
			}
			if (!result.isEmpty()) { //Tests to see if the list is empty. If false, the list is ordered by date.
				result = ContactManagerImpl.bubbleSortMeetingByDate(result);
			}
			return result;
		}
	}

	/**
	 * Takes a List of Meetings and orders the elements by date.
	 * Uses bubble sort.
	 * Not compatible with PastMeeting.
	 *
	 * @param list the list to be ordered.
	 * @return a list in chronological order.
	 */
	public static List<Meeting> bubbleSortMeetingByDate(List<Meeting> list) {
		int listSize = list.size();
		for (int i = 0; i < (listSize - 1); i++) {
			for (int j = 1; j < (listSize - i); j++) {
				Meeting left = list.remove(j-1);
				Calendar leftDate = left.getDate();
				Meeting right = list.remove(j);
				Calendar rightDate = right.getDate();
				if (leftDate.compareTo(rightDate) > 0) { //If the left value is greater than the right, the element are swapped.
					list.add((j-1), right);
					list.add(j, left);
				}
			}
		}
		return list;
	}

	/**
	 * Takes a List of PastMeetings and orders the elements by date.
	 * Uses bubble sort.
	 * Not compatible with FutureMeeting.
	 *
	 * @param list the list to be ordered.
	 * @return a list in chronological order.
	 */
	public static List<PastMeeting> bubbleSortPastMeetingByDate(List<PastMeeting> list) {
		int listSize = list.size();
		for (int i = 0; i < (listSize - 1); i++) {
			for (int j = 1; j < (listSize - i); j++) {
				PastMeeting left = list.remove(j-1);
				Calendar leftDate = left.getDate();
				PastMeeting right = list.remove(j);
				Calendar rightDate = right.getDate();
				if (leftDate.compareTo(rightDate) > 0) { //If the left value is greater than the right, the element are swapped.
					list.add((j-1), right);
					list.add(j, left);
				}
			}
		}
		return list;
	}

	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> result = new ArrayList<Meeting>();
		Iterator<FutureMeeting> listIterator = futureMeetings.iterator();
		while (listIterator.hasNext()) {
			FutureMeeting tempMeeting = listIterator.next();
			Calendar tempDate = tempMeeting.getDate();
			if (tempDate.equals(date)) { //Tests if the parameter matches the date of this meeting
				result.add(tempMeeting); //If dates match, the meeting is added to the list.
			}
		}
		if (!result.isEmpty()) { //Tests to see if the list is empty. If false, the list is ordered by date.
			result = ContactManagerImpl.bubbleSortMeetingByDate(result);
		}
		return result;
	}

	public List<PastMeeting> getPastMeetingList(Contact contact) throws IllegalArgumentException {
		if (!contacts.contains(contact)) { //Checks whether the contact is a member of the set contacts.
			throw new IllegalArgumentException();
		} else {
			List<PastMeeting> result = new ArrayList<PastMeeting>();
			Iterator<PastMeeting> listIterator = pastMeetings.iterator();
			while (listIterator.hasNext()) {
				PastMeeting tempMeeting = listIterator.next();
				Set<Contact> tempSet = tempMeeting.getContacts();
				if (tempSet.contains(contact)) { //Checks whether the contact is attending this meeting
					result.add(tempMeeting); //If contact is attending the meeting, the meeting is added to the list.
				}
			}
			if (!result.isEmpty()) { //Tests to see if the list is empty. If false, the list is ordered by date.
				result = ContactManagerImpl.bubbleSortPastMeetingByDate(result);
			}
			return result;
		}
	}

	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) throws IllegalArgumentException, NullPointerException {
		if (contacts.isEmpty()) {
			//Tests whether  contacts is empty. If true, an exception is thrown.
			throw new IllegalArgumentException();
		} else if (!this.contacts.containsAll(contacts)) {
			//Test whether contacts is a subset of the instance variable contacts.
			//If false, an exception is thrown.
			throw new IllegalArgumentException();
		} else if (contacts.equals(null) || date.equals(null) || text.equals(null)) {
			//Test if any of the arguments are null.
			//If true, an exception is thrown.
			throw new NullPointerException();
		} else {
			pastMeetings.add(new PastMeetingImpl(contacts, date, text));
		}
	}

	public void addMeetingNotes(int id, String text) throws IllegalArgumentException, NullPointerException, IllegalStateException {
		if (!containsPastMeetingId(id) && !containsFutureMeetingId(id)) {
			//Tests whether the meeting is not on the pastMeetings and not on the futureMeetings list.
			//If true, an exception is thrown.
			throw new IllegalArgumentException();
		} else if (text.equals(null)) {
			//Tests whether text is null
			//If true, an exception is thrown.
			throw new NullPointerException();
		} else if (containsPastMeetingId(id)) {
			//Tests whether id is on the pastMeetings list.
			//If true, notes are added to that PastMeeting's notes field.
			//If PastMeeting already has notes, those notes are overwritten.
			PastMeeting temp = getPastMeeting(id);
			pastMeetings.remove(id);
			pastMeetings.add(new PastMeetingImpl(temp, text)); //Adds amended PastMeeting back to list
		} else if (containsFutureMeetingId(id)) {
			FutureMeeting temp = getFutureMeeting(id);
			Calendar tempDate = temp.getDate();
			if (tempDate.compareTo(new GregorianCalendar()) >= 0) {
				//Compare the FutureMeeting's date to the current date.
				//If the meeting is in the future, an exception is thrown.
				throw new IllegalStateException();
			} else {
				futureMeetings.remove(id);
				pastMeetings.add(new PastMeetingImpl(temp, text));
			}
		}
	}

	public void addNewContact(String name, String notes) throws NullPointerException {
		if (name.equals(null) || notes.equals(null)) {
			//Checks if either argument is null
			//If true, an excepion is thrown
			throw new NullPointerException();
		} else {
			//Creates a new Contact object and adds it to the Set contacts
			Contact temp = new ContactImpl(name);
			temp.addNotes(notes);
			contacts.add(temp);
		}
	}

	/**
	 * Checks if the instance variable contacts contains a Contact with a matching id.
	 *
	 * @param id the id to be checked against the set of contacts.
	 * @return true if there is a matching id in contacts.
	 */
	public boolean containsContact(int id) {
		boolean result = false;
		Iterator<Contact> contactIterator = contacts.iterator();
		boolean finished = false;
		while (!finished) {
			if (contactIterator.hasNext()) {
				//Tests whether there are remaining contacts to iterate.
				Contact temp = contactIterator.next();
				if (temp.getId() == id) {
					//Tests if the current iteration has a matching id.
					finished = true;
					result = true;
				}
			} else {
				finished = true;
			}
		}
		return result;
	}

	public Set<Contact> getContacts(int... ids) throws IllegalArgumentException {
		Set<Contact> result = new HashSet<Contact>();
		for (int i = 0; i < ids.length; i++) {
			//Iterates throug the array of ids passed as an argument.
			if (!containsContact(ids[i])) {
				//Checks if the id exists on set of contacts.
				//If the id does not exist, an exception is thrown.
				throw new IllegalArgumentException();
			} else {
				Iterator<Contact> contactIterator = contacts.iterator();
				boolean finished = false;
				while (!finished) {
					if (contactIterator.hasNext()) {
						//Tests whether there are remaining contacts to iterate.
						Contact temp = contactIterator.next();
						if (temp.getId() == ids[i]) {
							//Tests if the current iteration has a matching id.
							finished = true;
							result.add(new ContactImpl(temp.getId(), temp.getName(), temp.getNotes()));
						}
					} else {
						finished = true;
					}
				}
			}
		}
		return result;
	}

	public Set<Contact> getContacts(String name) throws NullPointerException {
		Set<Contact> result = new HashSet<Contact>();
		if (name.equals(null)) {
			//Checks if the name exists on set of contacts.
			//If the id does not exist, an exception is thrown.
			throw new NullPointerException();
		} else {
			Iterator<Contact> contactIterator = contacts.iterator();
			while (contactIterator.hasNext()) {
				//Tests whether there are remaining contacts to iterate.
				Contact temp = contactIterator.next();
				if (name.equals(temp.getName())) {
					//Tests if the current iteration has a matching name.
					result.add(new ContactImpl(temp.getId(), temp.getName(), temp.getNotes()));
				}
			}
		}
		return result;
	}

	/**
	 * Verfiies if a Set of contacts is a subset of the instance variable contacts.
	 *
	 * @param contacts, the set of contacts to check against the instance variable contacts.
	 * @return true if contacts is a subset.
	 */
	public boolean containsAll(Set<Contact> contacts) {
		boolean result = true;
		Iterator<Contact> argIterator = contacts.iterator();
		//Creates an iterator for the Set passed as an argument.
		while (argIterator.hasNext()) {
			boolean contactExists = false;
			//For each iteration, the value is reset to false.
			Contact argContact = argIterator.next();
			Iterator<Contact> thisIterator = this.contacts.iterator();
			//Creates an iterator for the instance variable.
			while (thisIterator.hasNext()) {
				Contact thisContact = thisIterator.next();
				if (thisContact.getId() == argContact.getId()) {
					//Tests if the id matches.
					String thisName = thisContact.getName();
					if (thisName.equals(argContact.getName())) {
						//Tests if the name matches.
						String thisNotes = thisContact.getNotes();
						if (thisNotes.equals(argContact.getNotes())) {
							//Tests if the notes field matches.
							contactExists = true;
						}
					}
				}
			}
			if (!contactExists) {
				//Tests if the current Contact exists.
				//If it doesn't, the result is set to false.
				result = false;
			}
		}
		return result;
	}

	public void flush() {	}
}