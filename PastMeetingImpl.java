import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements the interface PastMeeting.
 *
 * @author Gareth Moore
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	/**
	 * A notes field which holds notes about this PastMeeting.
	 */
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

	/**
	 * Constructor method to create a PastMeetingImpl object.
	 *
	 * o be used only when converting a FutureMeeting into a PastMeeting.
	 * Should only be used after the existing FutureMeeting has been deleted.
	 *
	 * @param meeting, the Meeting to be converted into a PastMeeting.
	 * @param notes are the notes to be recorded from the meeting.
	 */
	public PastMeetingImpl(Meeting meeting, String notes) {
		super(meeting);
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}
}