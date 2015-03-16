import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;

/**
 * Implements the interface FutureMeeting.
 *
 * @author Gareth Moore.
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {

	/**
	 * Constructor method.
	 *
	 * @param contacts, the set of contacts who will attend the meeting.
	 * @param date, the date and time of the scheduled meeting.
	 */
	public FutureMeetingImpl(Set<Contact> contacts, Calendar date) {
		super(contacts, date);
	}
}