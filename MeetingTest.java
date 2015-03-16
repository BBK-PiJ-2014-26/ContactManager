import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
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

	/**
	 * Instantiates a meeting object for use in testing.
	 */
	@Before
	public void buildUp() {
		Set<Contact> tempContactSet = new CopyOnWriteArraySet<Contact>();
		batman = new ContactImpl("Bruce Wayne");
		tempContactSet.add(batman);
		superman = new ContactImpl("Clark Kent");
		tempContactSet.add(superman);
		myMeeting = new MeetingImpl(tempContactSet, new GregorianCalendar(2015, 10, 10, 10, 0));
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
}