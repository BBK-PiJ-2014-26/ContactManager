import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Calendar;

/**
 * Tests the class MeetingImpl.
 *
 * @author Gareth Moore.
 */
public class MeetingTest {
	Meeting myMeeting; // A Meeting object to be used in testing.
	Contact batman, superman; // Contact objects to be used in testings.
	Set<Contact> tempContactSet; //A set of contacts to be used in testing.

	/**
	 * Instantiates a meeting object for use in testing.
	 */
	@Before
	public void buildUp() {
		tempContactSet = new CopyOnWriteArraySet<Contact>();
		batman = new ContactImpl("Bruce Wayne");
		tempContactSet.add(batman);
		superman = new ContactImpl("Clark Kent");
		tempContactSet.add(superman);
		myMeeting = new MeetingImpl(tempContactSet, new GregorianCalendar(2015, 10, 10, 10, 0));
	}

	/**
	 * iDCounter is as static variable therefore it may interfere with test results.
	 * To avoiid this problem, iDCounter is reset to 0 after each test.
	 */
	@After
	public void cleanUp() {
		MeetingImpl.iDCounter = 0;
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
		Calendar actualDate = myMeeting.getDate();
		assertEquals(expectedDate, actualDate);
	}

	/**
	 * Tests getContacts().
	 *
	 * Should return correct set of Contacts.
	 */
	@Test
	public void shouldReturnCorrectContactSet() {
		Set<Contact> expectedSet = new CopyOnWriteArraySet<Contact>();
		expectedSet.add(batman);
		expectedSet.add(superman);
		Set<Contact> actualSet = myMeeting.getContacts();
		assertEquals(expectedSet, actualSet);
	}

	/**
	 * Tests nextId(). nextId() is enacted on each call of the Constructor.
	 *
	 * After 100 000 Contact objects are created, the 100 000th object should have Id 100 000.
	 */
	@Test
	public void shouldReturnId100000() {
		Meeting testMeeting = null;
		Calendar myDate = new GregorianCalendar(2015, 3, 22, 10, 5);
		for (int i = 1; i <= 99999; i++) { //Creates 99999 contact objects. One meeting has been created under the Before method.
			testMeeting = new MeetingImpl(tempContactSet, myDate);
			myDate.add(myDate.HOUR, 1); //Increments the date by 1 hour, so meeting are not duplicates.
		}
		int expectedId = 100000;
		int actualId = testMeeting.getId();
		assertEquals(expectedId, actualId);
	}
}