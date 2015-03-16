import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests the class PastMeetingImpl.
 *
 * @author Gareth Moore.
 */
public class PastMeetingTest {

	PastMeeting myPastMeeting; //A PastMeeting object for testing purposes.

	/**
	 * Instantiate a PastMeeting object for testing.
	 */
	@Before
	public void buildUp() {
		myPastMeeting = new PastMeetingImpl("Scarecrow has escaped");
	}

	/**
	 * Tests the method getNotes().
	 *
	 * Should return the correct notes from a past meeting object.
	 */
	@Test
	public void shouldReturnCorrectPastMeetingNotes() {
		String expectedNotes = "Scarecrow has excaped";
		String actualNotes = myPastMeeting.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}
}