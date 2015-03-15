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

	/**
	 * Instantiates a meeting object for use in testing.
	 */
	@Before
	public void buildUp() {
		Set<Contact> tempContactSet = new CopyOnWriteArraySet<Contact>();
		tempContactSet.add(new ContactImpl("Bruce Wayne"));
		tempContactSet.add(new ContactImpl("Clark Kent"));
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
		Set<Contact> tempSet = new CopyOnWriteArraySet<Contact>();
		tempSet.add(new ContactImpl("Bruce Wayne"));
		tempSet.add(new ContactImpl("Clark Kent"));
		Object[] expectedSet = tempSet.toArray();
		tempSet = myMeeting.getContacts();
		Object[] actualSet = tempSet.toArray();
		assertArrayEquals(expectedSet, actualSet);
	}
}