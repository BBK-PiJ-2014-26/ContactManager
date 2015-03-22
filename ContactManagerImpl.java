import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Implements the interface ContactManager.
 *
 * @author Gareth Moore
 */
public class ContactManagerImpl  {
	/**
	 * A data structure to store contacts.
	 */
	Set<Contact> contacts;
	/**
	 * A data structure to store future meetings.
	 * Using FutureMeetinImpl instead of PastMeeting because
	 * FutureMeeting does not contain instance variables.
	 */
	List<FutureMeetingImpl> futureMeetings;
	/**
	 * A data structure to store past meetings.
	 * Using PastMeetinImpl instead of PastMeeting because
	 * PastMeeting does not contain instance variables.
	 */
	List<PastMeetingImpl> pastMeetings;

	int addFutureMeeting(Set<Contact> contacts, Calendar date) throws IllegalArgumentException {
		if (date.compareTo(new GregorianCalendar()) < 0) { //Compares the provided date to the current date and time.
			throw new IllegalArgumentException();
		} else if (!this.contacts.containsAll(contacts) || contacts.isEmpty()) {
						//Validates the supplied sets of contacts.
						//If a contact is not contained within the ContactManager, an exception is thrown.
						//If the set empty, an exception is also thrown.
			throw new IllegalArgumentException();
		} else {
			//Meeting newMeeting = new FutureMeetingImpl(contacts, date);
			//int result = futureMeetings.add(newMeeting);
			return 1;
		}
	}

	/**
	 * Verfies whether the Meeting id exists in the pastMeetings list.
	 *
	 * @param id, id to be verfied.
	 * @return true if the meeting is contained with the list.
	 */
	public boolean containsPastMeetingId(int id) {
		boolean result = false;
		Iterator<PastMeetingImpl> listIterator = pastMeetings.iterator();
			//Must be PastMeetingImpl because PastMeeting interface does not have an id variable.
		boolean finished = false;
		while (!finished) {
			if (listIterator.hasNext()) { //Tests whether the end of the list has been reached.
				PastMeetingImpl temp = listIterator.next();
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
	 * @return true if the meeting is contained with the list.
	 */
	public boolean containsFutureMeetingId(int id) throws IllegalArgumentException {
		boolean result = false;
		Iterator<FutureMeetingImpl> listIterator = futureMeetings.iterator();
			//Must be MeetingImpl because Meeting interface does not have an id variable.
		boolean finished = false;
		while (!finished) {
			if (listIterator.hasNext()) { //Tests whether the end of the list has been reached.
				FutureMeetingImpl temp = listIterator.next();
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
			Iterator<PastMeetingImpl> listIterator = pastMeetings.iterator();
			boolean finished = false;
			while (!finished) {
				if(listIterator.hasNext()) { //Tests whether the end of the list has been reached.
					PastMeetingImpl temp = listIterator.next();
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
			Iterator<FutureMeetingImpl> listIterator = futureMeetings.iterator();
			boolean finished = false;
			while (!finished) {
				if(listIterator.hasNext()) { //Tests whether the end of the list has been reached.
					FutureMeetingImpl temp = listIterator.next();
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
}