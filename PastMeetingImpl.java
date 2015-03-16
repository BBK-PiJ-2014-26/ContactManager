import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements the interface PastMeeting.
 *
 * @author Gareth Moore
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

	private String notes;

	/**
	 * Constructor method to create a PastMeetingImpl object.
	 *
	 * @param notes are the notes to be recorded from the meeting.
	 */
	public PastMeetingImpl(Set<Contact> contacts, Calendar date, String notes) {
		super(contacts, date);
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}
}