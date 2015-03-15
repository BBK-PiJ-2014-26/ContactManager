import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Set;

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
	 * Tests getId().
	 *
	 * Should return the expeced Meeting Id.
	 */
	@Test
	public void shouldReturnCorrectId() {
		int expectedId = 1;
		int actualId = myMeeting.getId();
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getDate().
	 *
	 * Should return the correct date.
	 */
	@Test
	public void shouldReturnCorrectDate() {
		Calendar expectedDate = new GregorianCalendar(2015, 10, 10, 10, 0);
		Calendar actualDate = getDate();
		assertEquals(expectedDate, actualDate);
	}

}