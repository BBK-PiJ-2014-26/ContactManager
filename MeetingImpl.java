import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements the Meeting interface.
 *
 * @author Gareth Moore.
 */
 public class MeetingImpl {
	/**
	 * A unique for each Meeting object.
	 */
	private int id;
	/**
	 * The date for the Meeting.
	 */
	private Calendar date;
	/**
	 * The set of contacts who will attend the meeting.
	 */
	private Set<Contact> contacts;
	/**
	 * A static counter which tracks the most recent id assignment.
	 * The method nextId() increments the counter by 1.
	 * Each time the application is restarted, this value must be reset to the most recent id assignment.
	 */
	public static int iDCounter = 0;

	/**
	 * Constructor method/
	 *
	 * @param contacts, the set of contacts who will attend the meeting.
	 * @param date, the date and time of the scheduled meeting.
	 */
	public MeetingImpl(Set<Contact> contacts, Calendar date) {
		this.contacts = contacts;
		this.date = date;
		this.id = nextId();
	}

	public int getId() {
		return id;
	}

	public Calendar getDate() {
		return date;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	/**
	 * Increments the iDCounter.
	 */
	public int nextId() {
		iDCounter++;
		return iDCounter;
	}
 }