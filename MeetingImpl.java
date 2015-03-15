import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements the Meeting interface.
 *
 * @author Gareth Moore.
 */
 public class MeetingImpl {

	private int Id;
	private Calendar date;
	private Set<Contact> contacts;

	/**
	 * Constructor method/
	 *
	 * @param contacts, the set of contacts who will attend the meeting.
	 * @param date, the date and time of the scheduled meeting.
	 */
	public MeetingImpl(Set<Contacts> contacts, Calendar date) {
		this.contacts = contacts;
		this.date = date;
	}
 }