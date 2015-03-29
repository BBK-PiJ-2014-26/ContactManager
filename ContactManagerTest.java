import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
import org.junit.After;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

/**
 * Tests the class ContactManagerImpl.
 *
 * @author Gareth Moore.
 */
public class ContactManagerTest {
	ContactManager myContactManager; //A ContactManager object to be used in testing.
	Set<Contact> batmanSuperman; // A set of contacts to be used in testing. To be used to create one FutureMeeting and one PastMeeting;
	Set<Contact> lanternSuperman; //A set of contacts for testing. Will have multiple past and future meetings.

	/**
	 * Sets up objects to be used in testing.
	 */
	@Before
	public void buildUp() {
		myContactManager = new ContactManagerImpl();
		myContactManager.addNewContact("Bruce Wayne", "The Dark Knight");
				//Contact id = 1
				//Will have one future and one past meeting.
		myContactManager.addNewContact("Clark Kent", "The Man of Steel");
				//Contact id = 2
				// This contact should have multiple future and past meetings.
		myContactManager.addNewContact("Diana Prince", "From Themscyria");
				//Contact id = 3
				//A contact for testing. Should have no future meetings or past meetings.
		myContactManager.addNewContact("Hal Jordan", "By Green Lantern's light");
				//Contact id = 4
				// This contact should have multiple future and past meetings.
		batmanSuperman = myContactManager.getContacts(1, 2);
		lanternSuperman = myContactManager.getContacts(2, 4);
		myContactManager.addNewPastMeeting(batmanSuperman, new GregorianCalendar(2014, 11, 26, 10, 5), "Acheiving Justice: Finding A Betterhis World");
				//Meeting id = 1
		myContactManager.addFutureMeeting(batmanSuperman, new GregorianCalendar(2015, 11, 26, 10, 5));
				//Meeting id = 2
		myContactManager.addNewPastMeeting(lanternSuperman, new GregorianCalendar(2013, 10, 5, 12, 30), "The Apokolips problem?");
				//Meeting id = 3
		myContactManager.addNewPastMeeting(lanternSuperman, new GregorianCalendar(2012, 10, 5, 12, 30), "Trouble on Oa?");
				//Meeting id = 4
		myContactManager.addNewPastMeeting(lanternSuperman, new GregorianCalendar(2013, 10, 5, 12, 30), "What happenned to Mongul?");
				//Meeting id = 5
		myContactManager.addFutureMeeting(lanternSuperman, new GregorianCalendar(2018, 11, 26, 10, 30));
				//Meeting id = 6
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
		int expectedId = 8;
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
		FutureMeeting testMeeting = myContactManager.getFutureMeeting(10000);
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
		Meeting testMeeting = myContactManager.getMeeting(10000);
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
	 * Should return an empty list because Wonder Woman has no future meetings.
	 */
	@Test
	public void shouldReturnEmptyListUsingGetFutureMeeting() {
		Set<Contact> testSet = myContactManager.getContacts("Diana Prince");
		//Remove a set containing a single contact who has no future meetings.
		Iterator<Contact> setIterator = testSet.iterator();
		Contact wonderWoman = setIterator.next();
		//The list has only one item so removing index 0 removes the correct Contact
		List<Meeting> emptyList = null;
		try {
			emptyList = myContactManager.getFutureMeetingList(wonderWoman);
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
		Set<Contact> testSet = myContactManager.getContacts("Bruce Wayne");
		//Remove a set containing a single contact who has one future meetings.
		Iterator<Contact> setIterator = testSet.iterator();
		Contact batman = setIterator.next();
		//The list has only one item so removing index 0 removes the correct Contact
		List<Meeting> testList = null;
		try {
			testList = myContactManager.getFutureMeetingList(batman);
		} catch (IllegalArgumentException ex) {} //No action required to deal with caught exception.
		Meeting testMeeting = testList.get(0);
		//List should contain only one meeting, therefore the single meeting should be at index 0.
		int actualMeetingId = testMeeting.getId();
		assertEquals(2, actualMeetingId);
	}

	/**
	 * Tests getPastMeetingList(Contact).
	 *
	 * Should return an empty list because wonderWoman has no past meetings.
	 */
	@Test
	public void shouldReturnEmptyListUsingGetPastMeeting() {
		Set<Contact> testSet = myContactManager.getContacts("Diana Prince");
		//Remove a set containing a single contact who has no past meetings.
		Iterator<Contact> setIterator = testSet.iterator();
		Contact wonderWoman = setIterator.next();
		//The list has only one item so removing index 0 removes the correct Contact
		List<PastMeeting> emptyList = null;
		try {
			emptyList = myContactManager.getPastMeetingList(wonderWoman);
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
		Set<Contact> testSet = myContactManager.getContacts("Bruce Wayne");
		//Remove a set containing a single contact who has one past meeting.
		Iterator<Contact> setIterator = testSet.iterator();
		Contact batman = setIterator.next();
		//The list has only one item so removing index 0 removes the correct Contact
		List<PastMeeting> testList = null;
		try {
			testList = myContactManager.getPastMeetingList(batman);
		} catch (IllegalArgumentException ex) {} //No action required to deal with caught exception.
		Meeting testMeeting = testList.get(0);
		//List should contain only one meeting, therefore the single meeting should be at index 0.
		int actualMeetingId = testMeeting.getId();
		assertEquals(1, actualMeetingId);
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
	 * Tests getFutureMeetingList().
	 *
	 * Should return a list containing batman's one FutureMeeting.
	 * Verify by comparing ids.
	 */
	@Test
	public void shouldReturnBatmansFutureMeeting() {
		List<Meeting> actualList = myContactManager.getFutureMeetingList(new GregorianCalendar(2015, 11, 26, 10, 5));
		Meeting actualMeeting = actualList.get(0);
		//List should contain one meeting so the meeting I am checking should be at index 0.
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
			myContactManager.addMeetingNotes(10000, "Rogue's Gallery"); //10000 is an invalid meeting id.
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
	 * Should throw exception if notes is null.
	 */
	@Test
	public void shouldThrowExceptionWhenNotesAreNull() {
		boolean exceptionThrown = false;
		String notes = null;
		try {
			myContactManager.addMeetingNotes(6, notes);
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
			myContactManager.addMeetingNotes(7, "Rogue's Gallery");
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
	 * Should convert a future meeting into a past meeting.
	 */
	@Test
	public void shouldAddNotesSuccessfully() {
		try {
			myContactManager.addFutureMeeting(lanternSuperman, Calendar.getInstance());
			//Add a new meeting id = 8
			//Uses the current system date.
			//addMeetingNotes() should convert a meeting carrying today's date into a PastMeeting.
			myContactManager.addMeetingNotes(8, "Rogue's Gallery");
				//Meeting id 6 is scheduled for the current system time.
				//So it should be in the past by the time of this method call.
		} catch (IllegalStateException ex) {
		} catch (NullPointerException ex) {
		} catch (IllegalArgumentException ex) {} //No action required if these exceptions are thrown.
		PastMeeting testMeeting = myContactManager.getPastMeeting(8);
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
		Set<Contact> testSet = myContactManager.getContacts(5);
		//Creates a set containg newly added Contact
		Iterator<Contact> setIterator = testSet.iterator();
		Contact testContact = setIterator.next();
		//There is only one member of the set.
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
	 * Verifies the returned Set is the same as batmanSuperman.
	 */
	@Test
	public void shouldReturnCorrectSetOfContacts() {
		Set<Contact> testSet = null;
		try {
			testSet = myContactManager.getContacts(1, 2);
		} catch (IllegalArgumentException ex) {} //No action required to handle a thrown exception
		Iterator<Contact> testIterator = testSet.iterator();
		boolean batmanExists =  false;
		boolean supermanExists = false;
		while (testIterator.hasNext()) {
			Contact testContact = testIterator.next();
			if (testContact.getName().equals("Bruce Wayne")) {
				batmanExists = true;
			} else if (testContact.getName().equals("Clark Kent")) {
				supermanExists = true;
			}
		}
		assertTrue(batmanExists && supermanExists);
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
		Set<Contact> testSet = null;
		try {
			testSet = myContactManager.getContacts("Bruce Wayne");
				//Bruce Wayne refers to Contact batman whose id is 1
		} catch (NullPointerException ex) {} //No action required to handle a thrown exception
		Iterator<Contact> setIterator = testSet.iterator();
		Contact testContact = setIterator.next();
				//The returned set should only contain one element
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
		myContactManager.addNewContact("Bruce Wayne", "Caped Crusader");
				//Adds new contact with the name Bruce Wayne
		Set<Contact> testSet = null;
		try {
			testSet = myContactManager.getContacts("Bruce Wayne");
				//Removes all the Contacts with the name Bruce Wayne
		} catch (NullPointerException ex) {} //No action required to handle a thrown exception
		assertEquals(2, testSet.size());
				//Verifies that the returned set contains two Contacts
	}

	/**
	 * Tests flush().
	 *
	 * Confirm that flush() create a file called "contacts.txt"
	 */
	@Test
	public void shouldCreateFile() {
		myContactManager.flush();
		File contacts = new File("./contacts.txt");
		assertTrue(contacts.exists());
	}

	/**
	 * Tests flush().
	 *
	 * Verfies that data is written to "contacts.txt".
	 * The tests read each line sequentially.
	 * It increments a counter for each line of date.
	 * The file should 13 lines: 2 for iDcounters, 4 for contacts and 7 for meetings.
	 */
	@Test
	public void fileShouldContain13Lines() {
		int counter = 0;
		BufferedReader myReader = null;
		try {
			myContactManager.flush();
			myReader = new BufferedReader(new FileReader(new File("./contacts.txt")));
			String line = myReader.readLine();
			while (line != null) {
				counter++;
				line = myReader.readLine();
			}
		} catch (IOException ex) { //No action required for caught exception.
		} finally {
			try {
				myReader.close();
			} catch (IOException ex) {} //No action required for caught exception.
		}
		assertEquals(13, counter);
	}

}