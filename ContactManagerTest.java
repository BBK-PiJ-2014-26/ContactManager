import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;

/**
 * Tests the class ContactManagerImpl.
 *
 * @author Gareth Moore.
 */
public class ContactManagerTest {
	ContactManager myContactManager; //A ContactManager object to be used in testing.
	Set<Contact> testSet; // A set of contacts to be used in testing.

	/**
	 * Instantiates objects to be used in testing.
	 */
	@Before
	public void buildUp() {
		testSet = new HashSet<Contact>();
		testSet.add(new Contact("Bruce Wayne"));
		testSet.add(new Contact("Clark Kent"));
		myContactManager = new ContactManagerImpl();
	}

	/**
	 * Tests addFutureMeeting().
	 *
	 * Should throw IllegalArgumentException when supplied date is in the past.
	 */
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenPastDate() {
		try {
			myContactManager.addFutureMeeting(testSet, new GregorianCalendar(1900, 1, 1, 10, 15));
		} catch (IllegalArgumentException ex) {
			boolean exceptionThrown;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addFutureMeeting().
	 *
	 * Should throw IllegalArgumentException when contact does not exist.
	 */
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenContactDoesNotExist() {
		try {
			myContactManager.addFutureMeeting(new HashSet<Contact>(new Contact("Harvey Dent")), new GregorianCalendar(1900, 1, 1, 10, 15));
		} catch (IllegalArgumentException ex) {
			boolean exceptionThrown;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addFutureMeeting().
	 *
	 * Should return expected MeetingId.
	 */
	@Test
	public void shoulfReturnCorrectMeetingID {
		int expectedId = 1;
		int actualId = myContactManager.addFutureMeeting(testSet, new GregorianCalendar(1900, 1, 1, 10, 15));
		assertEquals(expectedId, actualId);
	}
}