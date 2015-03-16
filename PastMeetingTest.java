import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Tests the class PastMeetingImpl.
 *
 * @author Gareth Moore.
 */
public class PastMeetingTest {

	PastMeeting myPastMeeting; //A PastMeeting object for testing purposes.
	Contact batman, superman; // Contact object for testing.

	/**
	 * Instantiates a PastMeeting object for testing.
	 */
	@Before
	public void buildUp() {
		Set<Contact> tempContactSet = new CopyOnWriteArraySet<Contact>();
		batman = new ContactImpl("Bruce Wayne");
		tempContactSet.add(batman);
		superman = new ContactImpl("Clark Kent");
		tempContactSet.add(superman);
		myPastMeeting = new PastMeetingImpl(tempContactSet, new GregorianCalendar(2015, 10, 10, 10, 0), "Scarecrow has escaped");
	}

	/**
	 * Tests the method getNotes().
	 *
	 * Should return the correct notes from a past meeting object.
	 */
	@Test
	public void shouldReturnCorrectPastMeetingNotes() {
		String expectedNotes = "Scarecrow has escaped";
		String actualNotes = myPastMeeting.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}
}