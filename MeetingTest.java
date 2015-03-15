import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests the class MeetingImpl.
 *
 * @author Gareth Moore.
 */
public class MeetingTest {
	Meeting myMeeting; // A Meeting object to be used in testing.

	/**
	 * Instantiates a meeting object for use in testing.
	 */
	@Before
	public void buildUp() {
		myMeeting = new MeetingImpl();
	}

	/**
	 * Tests getId.
	 *
	 * Should return the expeced Meeting Id.
	 */
	@Test
	public void shouldReturnCorrectId() {
		int expectedId = 1;
		int actualId = myMeeting.getId();
		assertEquals(expectedId, actualId);
	}
}