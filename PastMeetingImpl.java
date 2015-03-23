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

	/**
	 * Constructor method to create a PastMeetingImpl object.
	 *
	 * Uses an existing Meeting whose id is kept the same.
	 * Should only be used after the existing object has been deleted.
	 *
	 * @param meeting, the Meeting to be converted into a PastMeeting.
	 * @param notes are the notes to be recorded from the meeting.
	 */
	public PastMeetingImpl(Meeting meeting, String notes) {
		super(meeting, notes);
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	/**
	 * Adder for notes.
	 *
	 * To be used when adding notes to an existing PastMeeting object.
	 *
	 * @param notes, the notes to be written to the notes field.
	 */
	public void addNotes(String notes) {
		this.notes = notes;
	}
}