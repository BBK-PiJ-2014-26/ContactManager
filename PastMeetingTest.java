import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
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
	Set<Contact> tempContactSet; //Set of contacts to be used in testing

	/**
	 * Instantiates a PastMeeting object for testing.
	 */
	@Before
	public void buildUp() {
		tempContactSet = new CopyOnWriteArraySet<Contact>();
		batman = new ContactImpl("Bruce Wayne");
		tempContactSet.add(batman);
		superman = new ContactImpl("Clark Kent");
		tempContactSet.add(superman);
		myPastMeeting = new PastMeetingImpl(tempContactSet, new GregorianCalendar(2015, 10, 10, 10, 0), "Scarecrow has escaped");
	}

	/**
	 * PastMeetingImpl has a static variable called iDCounter.
	 * This method resets the iDCounter to 0.
	 * This avoids interference from other tests.
	 */
	@After
	public void cleanUp() {
		MeetingImpl.iDCounter = 0;
	}

	/**
	 * Tests the method getNotes(). Duplicates the same test from MeetingTest.
	 *
	 * Should return the correct notes from a past meeting object.
	 */
	@Test
	public void shouldReturnCorrectPastMeetingNotes() {
		String expectedNotes = "Scarecrow has escaped";
		String actualNotes = myPastMeeting.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}

	/**
	 * Tests getId(). Duplicates the same test from MeetingTest.
	 *
	 * Should return the expeced Meeting Id.
	 */
	@Test
	public void shouldReturnCorrectId() {
		int expectedId = 1;
		int actualId = myPastMeeting.getId();
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
		Calendar actualDate = myPastMeeting.getDate();
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
		Set<Contact> actualSet = myPastMeeting.getContacts();
		assertEquals(expectedSet, actualSet);
	}

	/**
	 * Tests addNotes().
	 *
	 * Should add notes correctly.
	 */
	@Test
	public void shouldAddMeetingNotes() {
		PastMeetingImpl temp = new PastMeetingImpl(tempContactSet, new GregorianCalendar(2015, 10, 10, 10, 0), "Scarecrow has escaped");
			//Need to use PastMeetingImpl because addNotes is not on the PastMeeting interface.
		temp.addNotes("Scarecrow has been recaptured");
		assertEquals("Scarecrow has been recaptured", temp.getNotes());
	}
}