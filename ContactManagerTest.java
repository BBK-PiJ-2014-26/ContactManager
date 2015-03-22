import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;

/**
 * Tests the class ContactManagerImpl.
 *
 * @author Gareth Moore.
 */
public class ContactManagerTest {
	ContactManagerImpl myContactManager; //A ContactManager object to be used in testing.
	Set<Contact> testSet; // A set of contacts to be used in testing.
	Contact batman, superman; // Contacts for testing. These contacts should have future and past meetings.
	Contact wonderWoman; //A contact for testing. Should have no future meetings scheduled.

	/**
	 * Instantiates objects to be used in testing.
	 */
	@Before
	public void buildUp() {
		batman = new ContactImpl("Bruce Wayne");
		superman = new ContactImpl("Clark Kent");
		wonderWoman = new ContactImpl("Diana Prince");
		testSet = new HashSet<Contact>();
		testSet.add(batman);
		testSet.add(superman);
		myContactManager = new ContactManagerImpl();
		myContactManager.contacts = new HashSet<Contact>();
		myContactManager.contacts.add(batman);
		myContactManager.contacts.add(superman);
		myContactManger.contacts.add(wonderWoman);
		myContactManager.addNewPastMeeting(testSet, new GregorianCalendar(2014, 11, 26, 10, 5), "Acheiving Justice: Finding A Betterhis World");
				//Should be meetingId = 1
		myContactManager.addFutureMeeting(testSet, new GregorianCalendar(2015, 11, 26, 10, 5)); //Should be meetingId = 2
	}

	/**
	 * Resets the static variables after each test.
	 *
	 * ContactImpl and MeetingImpl contain static variables for tracking their respective iD fields.
	 * After each test, the static variables are reset to zero to avoid interference.
	 */
	@After
	public void cleanUp() {
		ContactImpl.iDCounter = 0;
		MeetingImpl.iDCounter = 0;
	}

	/**
	 * Tests addFutureMeeting().
	 *
	 * Should throw IllegalArgumentException when supplied date is in the past.
	 */
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenPastDate() {
		boolean exceptionThrown = false;
		try {
			myContactManager.addFutureMeeting(testSet, new GregorianCalendar(1900, 1, 1, 10, 15));
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
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
		boolean exceptionThrown = false;
		try {
			Set<Contact> mySet = new HashSet<Contact>();
			mySet.add(new ContactImpl("Harvey Dent"));
			myContactManager.addFutureMeeting(mySet, new GregorianCalendar(2020, 1, 1, 10, 15));
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addFutureMeeting().
	 *
	 * Should throw IllegalArgumentException when contact set is empty.
	 */
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenContactsIsEmpty() {
		boolean exceptionThrown = false;
		try {
			Set<Contact> mySet = new HashSet<Contact>();
			myContactManager.addFutureMeeting(mySet, new GregorianCalendar(2020, 1, 1, 10, 15));
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addFutureMeeting().
	 *
	 * Should return expected MeetingId.
	 */
	@Test
	public void shouldReturnCorrectMeetingID() {
		int expectedId = 1;
		int actualId = myContactManager.addFutureMeeting(testSet, new GregorianCalendar(2020, 1, 1, 10, 15));
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getPastMeeting().
	 *
	 * Should return Null because Meeting does not exist.
	 */
	@Test
	public void shouldReturnNullPastMeeting() {
		PastMeeting testMeeting = myContactManager.getPastMeeting(10000);
		assertNull(testMeeting);
	}

	/**
	 * Tests getPastMeeting().
	 *
	 * Shold throw IllegalArgumentException when meeting is in the future.
	 */
	@Test
	public void shouldReturnExceptionWhenMeetingIsInFuture() {
		boolean exceptionThrown = false;
		try {
			myContactManager.getPastMeeting(2);
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	/**
	 * Tests getPastMeeting().
	 *
	 * Confirms the returned Meeting is correct by validating the Id are the same.
	 */
	@Test
	public void shouldReturnCorrectPastMeeting() {
		Meeting returnedMeeting = myContactManager.getPastMeeting(1);
		int actualId = returnedMeeting.getId();
		int expectedId = 1;
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getFutureMeeting().
	 *
	 * Should return Null because Meeting does not exist.
	 */
	@Test
	public void shouldReturnNullFutureMeeting() {
		PastMeeting testMeeting = myContactManager.getFutureMeeting(10000);
		assertNull(testMeeting);
	}

	/**
	 * Tests getFutureMeeting().
	 *
	 * Shold throw IllegalArgumentException when meeting is in the past.
	 */
	@Test
	public void shouldReturnExceptionWhenMeetingIsInPast() {
		boolean exceptionThrown = false;
		try {
			myContactManager.getFutureMeeting(1);
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests getFutureMeeting().
	 *
	 * Confirms the returned Meeting is correct by validating the IDs are equal.
	 */
	@Test
	public void shouldReturnCorrectFutureMeeting() {
		Meeting returnedMeeting = myContactManager.getFutureMeeting(2);
		int actualId = returnedMeeting.getId();
		int expectedId = 2;
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getMeeting().
	 *
	 * Should return Null because Meeting does not exist.
	 */
	@Test
	public void shouldReturnNullMeeting() {
		PastMeeting testMeeting = myContactManager.getFutureMeeting(10000);
		assertNull(testMeeting);
	}

	/**
	 * Tests getMeeting().
	 *
	 * Confirms the returned Meeting is correct by validating the Id are equal.
	 */
	@Test
	public void shouldReturnCorrectMeeting() {
		Meeting returnedMeeting = myContactManager.getMeeting(1);
		int actualId = returnedMeeting.getId();
		int expectedId = 1;
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getFutureMeetingList().
	 *
	 * Should return an empty list because wonderWoman has no future meetings.
	 */
	@Test
	public void shouldReturnEmptyList() {
		try {
			List<Meeting> emptyList = myContactManager.getFutureMeetingList(wonderWoman);
		} catch (IllegalArgumentException ex) {} //No action required to deal with caught exception.
		assertTrue(emptyList.isEmpty());
	}

	/**
	 * Tests getFutureMeetingList().
	 *
	 * Should throw exception when contact does not exist.
	 */
	@Test
	public void shouldThrowEceptionWhenContactDoesNotExist() {
		Contact theFlash = new ContactImpl("Barry Allen"); //A contact who does not exist on myContactManager.
		boolean exceptionThrown = false;
		try {
			myContactManager.getFutureMeetingList(theFlash);
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests getFutureMeetingList().
	 *
	 * Should return a list containg one meeting.
	 * Should assert that the Meeting's id is 1.
	 */
	@Test
	public void shouldReturnMeetingId1() {
		try {
			List<Meeting> testList = myContactManager.getFutureMeetingList(batman);
		} catch (IllegalArgumentException ex) {} //No action required to deal with caught exception.
		Meeting testMeeting = testList.get(0); //List should contain only one meeting, therefore the single meeting should be at index 0.
		int expectedMeetingId = 1;
		int actualMeetingId = testMeeting.getId();
		assertEquals(expectedMeetingId, actualMeetingId);
	}
}