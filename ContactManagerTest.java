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
	ContactManager myContactManager; //A ContactManager object to be used in testing.
	Set<Contact> batmanSuperman; // A set of contacts to be used in testing. To be used to create one FutureMeeting and one PastMeeting;
	Set<Contact> lanternSuperman; //A set of contacts for testing. Will have multiple past and future meetings.
	Contact batman; //A contact for testing. Will have one future and one past meeting.
	Contact greenLantern, superman; // Contacts for testing. These contacts should have multiple future and past meetings.
	Contact wonderWoman; //A contact for testing. Should have no future meetings or past meetings.

	/**
	 * Instantiates objects to be used in testing.
	 */
	@Before
	public void buildUp() {
		batman = new ContactImpl("Bruce Wayne");
				//Contact id = 1
		superman = new ContactImpl("Clark Kent");
				//Contact id = 2
		wonderWoman = new ContactImpl("Diana Prince");
				//Contact id = 3
		greenLantern = new ContactImpl("Hal Jordan");
				//Contact id = 4
		batmanSuperman = new HashSet<Contact>();
		batmanSuperman.add(batman);
		batmanSuperman.add(superman);
		lanternSuperman = new HashSet<Contact>();
		lanternSuperman.add(superman);
		lanternSuperman.add(greenLantern);
		myContactManager = new ContactManagerImpl();
		myContactManager.contacts = new HashSet<Contact>();
		myContactManager.contacts.add(batman);
		myContactManager.contacts.add(superman);
		myContactManger.contacts.add(wonderWoman);
		myContactManger.contacts.add(greenLantern);
		myContactManager.addNewPastMeeting(batmanSuperman, new GregorianCalendar(2014, 11, 26, 10, 5), "Acheiving Justice: Finding A Betterhis World");
				//Meeting id = 1
		myContactManager.addFutureMeeting(batmanSuperman, new GregorianCalendar(2015, 11, 26, 10, 5));
				//Meeting id = 2
		myContactManager.addPastMeeting(lanternSuperman, new GregorianCalendar(2013, 10, 5, 12, 30), "The Apokolips problem?");
				//Meeting id = 3
		myContactManager.addPastMeeting(lanternSuperman, new GregorianCalendar(2012, 10, 5, 12, 30), "Trouble on Oa?");
				//Meeting id = 4
		myContactManager.addPastMeeting(lanternSuperman, new GregorianCalendar(2013, 10, 5, 12, 30), "What happenned to Mongul?");
				//Meeting ID = 5
		myContactManager.addFutureMeeting(lanternSuperman, new GregorianCalendar());
				//Meeting id = 6
				//Meeting to occur at current system time. Allows this meeting to be used to test addMeetingNotes().
		myContactManager.addFutureMeeting(lanternSuperman, new GregorianCalendar(2017, 11, 26, 10, 5));
				//Meeting id = 7
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
	public void shouldReturnEmptyListUsingGetFutureMeeting() {
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
	public void shouldReturnEmptyListUsingGetPastMeeting() {
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
	public void shouldThrowExceptionWhenNullCalendar() {
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

	/**
	 * Tests addNewPastMeeting().
	 *
	 * Verifies a new PastMeeting has been entered by checking its text field.
	 */
	@Test
	public void shouldVerifyPastMeetingIsAddedCorrectly() {
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
	public void shouldThrowExceptionWhenNotesAreNull() {
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

	/**
	 * Tests addNewContact()
	 *
	 * Should throw exception if name is null.
	 */
	@Test
	public void shouldThrowExceptionIfContactNameIsNull() {
		boolean exceptionThrown = false;
		String name = null;
		try {
			myContactManager.addNewContact(name, "Welcome to Thanagar.");
		} catch (NullPointerException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addNewContact()
	 *
	 * Should throw exception if notes is null.
	 */
	@Test
	public void shouldThrowExceptionIfNotesIsNull() {
		boolean exceptionThrown = false;
		String notes = null;
		try {
			myContactManager.addNewContact("Carter Hall", notes);
		} catch (NullPointerException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests addNewContact()
	 *
	 * Verify Contact is added correctly by checking name field.
	 */
	@Test
	public void shouldAddContactCorrectly() {
		try {
			myContactManager.addNewContact("Carter Hall", "Welcome to Thanagar");
					//Contact id will equal 5
		} catch (NullPointerException ex) {} //No action required if exception is caught
		Set<Contact> testSet = myContactManager.getContacts(5); //Gets a set containg newly added Contact
		Contact testContact = testSet.get(0); //There is only one member of the set so the contact must lie at index 0.
		assertEquals("Carter Hall", testContact.getName());
	}

	/**
	 * Tests getContacts(int...)
	 *
	 * Should throw exception if any of the ids are invalid.
	 */
	@Test
	public void shouldThrowExceptionIfIdIsInvalid() {
		boolean exceptionThrown = false;
		try {
			Set<Contact> testSet = myContactManager.getContacts(1, 2, 100);
					//100 is an invalid id.
		} catch (IllegalArgumentException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests getContacts(int...)
	 *
	 * Verifies the returned Set is correct by checking ids.
	 */
	@Test
	public void shouldReturnCorrectSetOfContacts() {
		try {
			Set<Contact> testSet = myContactManager.getContacts(1, 2);
		} catch (IllegalArgumentException ex) {} //No action required to handle a thrown exception
		assertTrue(testSet.containsAll(batmanSuperman));
				//The existing set batmanSuperman should contain exactly the same elements.
	}

	/**
	 * Tests getContacts(String)
	 *
	 * Should throw exception if name is null.
	 */
	@Test
	public void shouldThrowExceptionIfNameIsNull() {
		boolean exceptionThrown = false;
		String name = null;
		try {
			Set<Contact> testSet = myContactManager.getContacts(name);
		} catch (NullPointerException ex) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Tests getContacts(String)
	 *
	 * Verifies the returned Set is correct by checking id.
	 */
	@Test
	public void shouldReturnCorrectSetOfContactsUsingName() {
		try {
			Set<Contact> testSet = myContactManager.getContacts("Bruce Wayne");
				//Bruce Wayne refers to Contact batman whose id is 1
		} catch (NullPointerException ex) {} //No action required to handle a thrown exception
		Contact testContact = testSet.get(0);
				//The returned set should only contain one element therefore it should exist at index 0
		assertEquals(1, testContact.getId());
	}

	/**
	 * Tests getContacts(String)
	 *
	 * Verifies that when more than one Contact has the same name
	 * all of the Contacts are returned.
	 * This test adds a new "Bruce Wayne.@
	 * A set of two Contacts should be returned.
	 */
	@Test
	public void shouldReturnASetOf2Contacts() {
		myContactManager.Contacts.add(new ContactImpl("Bruce Wayne"));
				//Adds new contact with the name Bruce Wayne
		try {
			Set<Contact> testSet = myContactManager.getContacts("Bruce Wayne");
				//Removes all the Contacts with the name Bruce Wayne
		} catch (NullPointerException ex) {} //No action required to handle a thrown exception
		Contact testContact = testSet.get(0);
		assertEquals(2, testContact.size());
				//Verifies that the returned set contains two Contacts
	}

	/**
	 * Tests containsPastMeetingId()
	 *
	 * Should return false when checking id 7 which is a FutureMeeting.
	 */
	@Test
	public void shouldReturnFasleIfIdIsFutureMeeting() {
		assertFalse(myContactManager.containsPastMeetingId(7));
	}

	/**
	 * Tests containsPastMeetingId()
	 *
	 * Should return true when checking meeting id 3.
	 */
	@Test
	public void shouldReturnTrueIfPastMeetingExists() {
		assertTrue(myContactManager.containsPastMeetingId(3));
	}

	/**
	 * Tests containsPastMeetingId()
	 *
	 * Should return false when checking id 100 does not exist.
	 */
	@Test
	public void shouldReturnFasleIfPastMeetingDoesNotExist() {
		assertFalse(myContactManager.containsPastMeetingId(7));
	}

	/**
	 * Tests containsFutureMeetingId()
	 *
	 * Should return false when checking id 3 which is a PastMeeting.
	 */
	@Test
	public void shouldReturnFasleIfIdIsPastMeeting() {
		assertFalse(myContactManager.containsFutureMeetingId(3));
	}

	/**
	 * Tests containsFutureMeetingId()
	 *
	 * Should return true when checking meeting id 7.
	 */
	@Test
	public void shouldReturnTrueIfFutureMeetingExists() {
		assertTrue(myContactManager.containsFutureMeetingId(7));
	}

	/**
	 * Tests containsFutureMeetingId()
	 *
	 * Should return false when checking id 100 which does not exist.
	 */
	@Test
	public void shouldReturnFalseIfFutureMeetingDoesNotExist() {
		assertFalse(myContactManager.containsFutureMeetingId(100));
	}

	/**
	 * Tests containsContact(int id)
	 *
	 * Should return false when checking id 100 which is an invalid Contact id.
	 */
	@Test
	public void shouldReturnFasleIfInvalidId() {
		assertFalse(myContactManager.containsContact(100));
	}

	/**
	 * Tests containsContact(int id)
	 *
	 * Should return true when checking meeting id 1.
	 */
	@Test
	public void shouldReturnTrueIfContactIdExists() {
		assertTrue(myContactManager.containsContact(1));
	}


}