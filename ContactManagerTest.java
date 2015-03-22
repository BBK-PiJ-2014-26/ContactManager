import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;
import org.junit.After;

/**
 * Tests the class ContactManagerImpl.
 *
 * @author Gareth Moore.
 */
public class ContactManagerTest {
	ContactManagerImpl myContactManager; //A ContactManager object to be used in testing.
	Set<Contact> batmanSuperman; // A set of contacts to be used in testing. To be used to create one FutureMeeting and one PastMeeting;
	Set<Contact> lanternSuperman; //A set of cotnatcs for testing. Will have multiple past and future meetings.
	Contact batman; //A contact for testing. Will have one future and one past meeting.
	Contact greenLantern, superman; // Contacts for testing. These contacts should have multiple future and past meetings.
	Contact wonderWoman; //A contact for testing. Should have no future meetings or past meetings.

	/**
	 * Instantiates objects to be used in testing.
	 */
	@Before
	public void buildUp() {
		batman = new ContactImpl("Bruce Wayne");
		superman = new ContactImpl("Clark Kent");
		wonderWoman = new ContactImpl("Diana Prince");
		greenLantern = new ContactImpl("Hal Jordan");
		batmanSuperman = new HashSet<Contact>();
		batmanSuperman.add(batman);
		batmanSuperman.add(superman);
		myContactManager = new ContactManagerImpl();
		myContactManager.contacts = new HashSet<Contact>();
		myContactManager.contacts.add(batman);
		myContactManager.contacts.add(superman);
		myContactManger.contacts.add(wonderWoman);
		myContactManager.addNewPastMeeting(batmanSuperman, new GregorianCalendar(2014, 11, 26, 10, 5), "Acheiving Justice: Finding A Betterhis World");
				//Should be meeting id = 1
		myContactManager.addFutureMeeting(batmanSuperman, new GregorianCalendar(2015, 11, 26, 10, 5));
				//Should be meeting id = 2
		myContactManager.addPastMeeting(lanternSuperman, new GregorianCalendar(2013, 10, 5, 12, 30), "The Apokolips problem?");
				//Should be meetingdis = 3
		myContactManager.addPastMeeting(lanternSuperman, new GregorianCalendar(2012, 10, 5, 12, 30), "Trouble on Oa?");
				//Should be meetingdis = 4
		myContactManager.addPastMeeting(lanternSuperman, new GregorianCalendar(2013, 10, 5, 12, 30), "What happenned to Mongul?");
				//Should be meetingdis = 5
		myContactManager.addFutureMeeting(lanternSuperman, new GregorianCalendar());
				//Should be meeting id = 6
				//Meeting to occur at current system time. Allows this meeting to be used to test addMeetingNotes().
		myContactManager.addFutureMeeting(lanternSuperman, new GregorianCalendar(2017, 11, 26, 10, 5));
				//Should be meeting id = 7
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
			myContactManager.addFutureMeeting(batmanSuperman, new GregorianCalendar(1900, 1, 1, 10, 15));
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
		int actualId = myContactManager.addFutureMeeting(batmanSuperman, new GregorianCalendar(2020, 1, 1, 10, 15));
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
	 * Tests getFutureMeetingList(Contact).
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
	 * Tests getFutureMeetingList(Contact).
	 *
	 * Should throw exception when contact does not exist.
	 */
	@Test
	public void shouldThrowEceptionWhenContactDoesNotExistFuture() {
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
	 * Tests getFutureMeetingList(Contact).
	 *
	 * Should return a list containg one meeting.
	 * Should assert that the Meeting's id is 1.
	 */
	@Test
	public void shouldReturnMeetingId2() {
		try {
			List<Meeting> testList = myContactManager.getFutureMeetingList(batman);
		} catch (IllegalArgumentException ex) {} //No action required to deal with caught exception.
		Meeting testMeeting = testList.get(0); //List should contain only one meeting, therefore the single meeting should be at index 0.
		int expectedMeetingId = 2;
		int actualMeetingId = testMeeting.getId();
		assertEquals(expectedMeetingId, actualMeetingId);
	}

	/**
	 * Tests getPastMeetingList(Contact).
	 *
	 * Should return an empty list because wonderWoman has no future meetings.
	 */
	@Test
	public void shouldReturnEmptyList() {
		try {
			List<Meeting> emptyList = myContactManager.getPastMeetingList(wonderWoman);
		} catch (IllegalArgumentException ex) {} //No action required to deal with caught exception.
		assertTrue(emptyList.isEmpty());
	}

	/**
	 * Tests getPastMeetingList(Contact).
	 *
	 * Should throw exception when contact does not exist.
	 */
	@Test
	public void shouldThrowExceptionWhenContactDoesNotExistPast() {
		Contact theFlash = new ContactImpl("Barry Allen"); //A contact who does not exist on myContactManager.
		boolean exceptionThrown = false;
		try {
			myContactManager.getPastMeetingList(theFlash);
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests getPastMeetingList(Contact).
	 *
	 * Should return a list containg one meeting.
	 * Should assert that the Meeting's id is 1.
	 */
	@Test
	public void shouldReturnMeetingId1() {
		try {
			List<Meeting> testList = myContactManager.getPastMeetingList(batman);
		} catch (IllegalArgumentException ex) {} //No action required to deal with caught exception.
		Meeting testMeeting = testList.get(0); //List should contain only one meeting, therefore the single meeting should be at index 0.
		int expectedMeetingId = 1;
		int actualMeetingId = testMeeting.getId();
		assertEquals(expectedMeetingId, actualMeetingId);
	}

	/**
	 * Tests getFutureMeetingList(Calendar).
	 *
	 * Should return an empty list because there are no meeting on Christmas Day 2017.
	 */
	@Test
	public void shouldReturnEmptyFutureListUsingCalendar() {
		List<Meeting> emptyList = myContactManager.getFutureMeetingList(new GregorianCalendar(2017, 12, 25));
		assertTrue(emptyList.isEmpty());
	}

	/**
	 * Tests getPastMeetingList(Calendar).
	 *
	 * Should return an empty list because there are no meeting on Christmas Day 2010.
	 */
	@Test
	public void shouldReturnEmptyPastListUsingCalendar() {
		List<Meeting> emptyList = myContactManager.getPastMeetingList(new GregorianCalendar(2010, 12, 25));
		assertTrue(emptyList.isEmpty());
	}

	/**
	 * Tests getFutureMeetingList().
	 *
	 * Should return a list containing batman's one FutureMeeting.
	 * Verify by comparing ids.
	 */
	@Test
	public void shouldReturnBatmansFutureMeeting() {
		List<Meeting> actualList = myContactManager.getFutureMeetingList(new GregorianCalendar(2015, 11, 26));
		Meeting actualMeeting = actualList.get(0); //List should contain one meeting so the meeting I am checking should be at index 0.
		int actualId = actualMeeting.getId();
		int expectedId = 2; //See Before, batman has only one future meeting which should have id 2.
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests addNewPastMeeting().
	 *
	 * Should throw exception if a null list of notes is entered.
	 */
	@Test
	public void shouldThrowExceptionWhenNullNotes() {
		boolean exceptionThrown = false;
		String notes = null;
		try {
			myContactManager.addNewPastMeeting(lanternSuperman, new GregorianCalendar(2012, 10, 1, 10, 30), notes);
		} catch (NullPointerException ex) {
			exceptionThrown =  true;
		} catch (IllegalArgumentException ex) {} //No action required if this exception is thrown.
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addNewPastMeeting().
	 *
	 * Should throw exception if a null Calendar is entered.
	 */
	@Test
	public void shouldThrowExceptionWhenNullNotes() {
		boolean exceptionThrown = false;
		Calendar date = null;
		try {
			myContactManager.addNewPastMeeting(lanternSuperman, date, "Brightest Day");
		} catch (NullPointerException ex) {
			exceptionThrown =  true;
		} catch (IllegalArgumentException ex) {} //No action required if this exception is thrown.
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addNewPastMeeting().
	 *
	 * Should throw exception if a empty set is entered.
	 */
	@Test
	public void shouldThrowExceptionWhenEmptySet() {
		boolean exceptionThrown = false;
		Set<Contact> testSet =  new HashSet<Contact>();
		try {
			myContactManager.addNewPastMeeting(testSet, new GregorianCalendar(2012, 10, 1, 10, 30), "Brightest Day");
		} catch (NullPointerException ex) { //No action required if this exception is thrown.
		} catch (IllegalArgumentException ex) {
			exceptionThrown =  true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addNewPastMeeting().
	 *
	 * Should throw exception if a null set is entered.
	 */
	@Test
	public void shouldThrowExceptionWhenNullSet() {
		boolean exceptionThrown = false;
		Set<Contact> testSet =  null;
		try {
			myContactManager.addNewPastMeeting(testSet, new GregorianCalendar(2012, 10, 1, 10, 30), "Brightest Day");
		} catch (NullPointerException ex) {
			exceptionThrown =  true;
		} catch (IllegalArgumentException ex) {} //No action required if this exception is thrown.
		assertTrue(exceptionThrown);
	}

`	/**
	 * Tests addNewPastMeeting().
	 *
	 * Verifies a new PastMeeting has been entered by checking its text field.
	 */
	@Test
	public void shouldThrowExceptionWhenNullSet() {
		try {
			myContactManager.addNewPastMeeting(lanternSuperman, new GregorianCalendar(2012, 10, 1, 10, 30), "Brightest Day");
				//Should be meeting id = 8.
		} catch (NullPointerException ex) {
		} catch (IllegalArgumentException ex) {} //No action required if these exceptions are thrown.
		PastMeeting testMeeting = myContactManager.getPastMeeting(8);
		String actualText = testMeeting.getNotes();
		assertEquals("Brightest Day", actualText);
	}

	/**
	 * Tests addMeetingNotes()
	 *
	 * Should throw exception if meeting id does not exist.
	 */
	@Test
	public void shouldThrowExceptionWhenMeetingIdDoesNotExist() {
		boolean exceptionThrown = false;
		try {
			addMeetingNotes(10000, "Rogue's Gallery"); //10000 is an invalid meeting id.
		} catch (IllegalStateException ex) {
		} catch (NullPointerException ex) { //No action required if these exceptions are thrown.
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addMeetingNotes()
	 *
	 * Should throw exception if meeting id is null.
	 */
	@Test
	public void shouldThrowExceptionWhenMeetingIdIsNull() {
		boolean exceptionThrown = false;
		int id = null;
		try {
			addMeetingNotes(id, "Rogue's Gallery");
		} catch (NullPointerException ex) {
			exceptionThrown = true;
		} catch (IllegalStateException ex) {
		} catch (IllegalArgumentException ex) {} //No action required if these exceptions are thrown.
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addMeetingNotes()
	 *
	 * Should throw exception if notes is null.
	 */
	@Test
	public void shouldThrowExceptionWhenMeetingIdIsNull() {
		boolean exceptionThrown = false;
		String notes = null;
		try {
			addMeetingNotes(6, notes);
				//Meeting id 6 is sceduled for the current system time.
				//So it should be in the past by the time of this method call.
		} catch (NullPointerException ex) {
			exceptionThrown = true;
		} catch (IllegalStateException ex) {
		} catch (IllegalArgumentException ex) {} //No action required if these exceptions are thrown.
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addMeetingNotes()
	 *
	 * Should throw an exception if Meeting is a FutureMeeting.
	 */
	@Test
	public void shouldThrowExceptionWhenMeetingIsFutureMeeting() {
		boolean exceptionThrown = false;
		try {
			addMeetingNotes(7, "Rogue's Gallery");
				//Meeting id 7 is scheduled for the future.
		} catch (IllegalStateException ex) {
			exceptionThrown = true;
		} catch (NullPointerException ex) {
		} catch (IllegalArgumentException ex) {} //No action required if these exceptions are thrown.
		assertTrue(exceptionThrown);
	}
}

	/**
	 * Tests addMeetingNotes()
	 *
	 * Verfies that notes were added successfully.
	 */
	@Test
	public void shouldAddNotesSuccessfully() {
		try {
			addMeetingNotes(6, "Rogue's Gallery");
				//Meeting id 6 is sceduled for the current system time.
				//So it should be in the past by the time of this method call.
		} catch (IllegalStateException ex) {
		} catch (NullPointerException ex) {
		} catch (IllegalArgumentException ex) {} //No action required if these exceptions are thrown.
		PastMeeting testMeeting = myContactManager.getPastMeeting(6);
		assertEquals("Rogue's Gallery", testMeeting.getNotes());
	}
}