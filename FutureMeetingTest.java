import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Tests the class FutureMeetingImpl.
 *
 * Duplicates tests from MeetingTest because FutureMeeting is a naming class only.
 *
 * @author Gareth Moore.
 */
public class FutureMeetingTest {
	FutureMeeting myFutureMeeting; //A FutureMeeting object for testing purposes.
	Contact batman, superman; // Contact object for testing.

	/**
	 * Instantiates a FutureMeeting object for use in testing.
	 */
	@Before
	public void buildUp() {
		Set<Contact> tempContactSet = new CopyOnWriteArraySet<Contact>();
		batman = new ContactImpl("Bruce Wayne");
		tempContactSet.add(batman);
		superman = new ContactImpl("Clark Kent");
		tempContactSet.add(superman);
		myFutureMeeting = new FutureMeetingImpl(tempContactSet, new GregorianCalendar(2015, 10, 10, 10, 0));
	}

	/**
	 * Tests getId(). Duplicates the same test from MeetingTest.
	 *
	 * Should return the expeced Meeting Id.
	 */
	@Test
	public void shouldReturnCorrectId() {
		int expectedId = 1;
		int actualId = myFutureMeeting.getId();
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getDate(). Duplicates the same test from MeetingTest.
	 *
	 * Should return the correct date.
	 */
	@Test
	public void shouldReturnCorrectDate() {
		Calendar expectedDate = new GregorianCalendar(2015, 10, 10, 10, 0);
		Calendar actualDate = myFutureMeeting.getDate();
		assertEquals(expectedDate, actualDate);
	}

	/**
	 * Tests getContacts(). Duplicates the same test from MeetingTest.
	 *
	 * Should return correct set of Contacts.
	 */
	@Test
	public void shouldReturnCorrectContactSet() {
		Set<Contact> expectedSet = new CopyOnWriteArraySet<Contact>();
		expectedSet.add(batman);
		expectedSet.add(superman);
		Set<Contact> actualSet = myFutureMeeting.getContacts();
		assertEquals(expectedSet, actualSet);
	}
}