import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.GregorianCalendar;

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
	 */
	List<Meeting> futureMeetings;
	/**
	 * A data structure to store past meetings.
	 */
	List<PastMeeting> pastMeetings;

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




}