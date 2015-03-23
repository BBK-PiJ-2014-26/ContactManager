import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements the Meeting interface.
 *
 * @author Gareth Moore.
 */
 public class MeetingImpl implements Meeting{
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

	/**
	 * Constructor method to be used when converting a Meeting into a PastMeeting.
	 * Should only be used when the existing meeting has been removed.
	 *
	 * @param futureMeeting, the FutureMeeting to be converted to a PastMeeting.
	 * @param notes are the notes to be recorded from the meeting.
	 */
	public MeetingImpl(Meeting meeting, String notes) {
		this.contacts = meeting.getContacts();
		this.date = meeting.getDate();
		this.id = meeting.getId();
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