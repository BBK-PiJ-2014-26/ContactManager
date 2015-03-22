import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

/**
 * Tests the class Contact.
 *
 * @author Gareth Moore.
 */
public class ContactTest {

	Contact myContact; // A contact object to be used in testing.

	/**
	 * Creates a contact object to be used in all tests.
	 */
	@Before
	public void buildUp() {
		myContact = new ContactImpl("Bruce Wayne"); //Should be Id = 1.
	}

	/**
	 * iDCounter is a static variable so may interfere with test results.
	 * To avoid this problem, iDCounter is rest to 0 after each test.
	 */
	@After
	public void cleanUp() {
		ContactImpl.iDCounter = 0;
	}

	/**
	 * Tests getId().
	 * The returned contact ID should match the ID assigned during instantiation.
	 */
	@Test
	public void shouldReturnCorrectId() {
		int expectedId = 1;
		int actualId = myContact.getId();
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests nextId(). nextId() is enacted on each call of the Constructor.
	 *
	 * After 100 000 Contact objects are created, the 100 000th object should have Id 100 000.
	 */
	@Test
	public void shouldReturnId100000() {
		Contact testContact = null;
		for (int i = 1; i <= 99999; i++) { //Creates 99999 contact objects. 1 object was created before test.
			testContact = new ContactImpl("Mirror Master");
		}
		int expectedId = 100000;
		int actualId = testContact.getId();
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getName().
	 * The returned name should match the name created during instantiation.
	 */
	@Test
	public void shouldReturnCorrectName() {
		String expectedName = "Bruce Wayne";
		String actualName = myContact.getName();
		assertEquals(expectedName, actualName);
	}

	/**
	 * Tests addNotes() and getNotes().
	 * The returned notes should match those we added.
	 */
	@Test
	public void shouldReturnCorrectNotes() {
		myContact.addNotes("We better catch that pesky Joker.");
		String expectedNotes = "We better catch that pesky Joker.";
		String actualNotes = myContact.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}

	/**
	 * Tests that when a Contact object has a null notes field,
	 * getNotes() returns an empty String.
	 */
	@Test
	public void shouldReturnEmptyString() {
		String expectedNotes = "";
		String actualNotes = myContact.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}


}