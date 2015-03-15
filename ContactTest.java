import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests the class Contact.
 *
 * @author Gareth Moore.
 */
public class ContactTest {

	/**
	 * Creates a contact object to be used in all tests.
	 */
	@Before
	public void buildUp() {
		Contact myContact = new Contact("Bruce Wayne");
	}

	/**
	 * Tests getId().
	 * The returned contact ID should match the ID assigned during instantiation.
	 */
	public void shouldReturnCorrectId() {
		buildUp();
		int expectedId = 1;
		int actualId = myContact.getId();
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests getName().
	 * The returned name should match the name created during instantiation.
	 */
	public void shouldReturnCorrectName() {
		buildUp();
		String expectedName = "Bruce Wayne";
		String actualName = myContact.getName();
		assertEquals(expectedName, actualName);
	}

	/**
	 * Tests addNotes() and getNotes().
	 * The returned notes should match those we added.
	 */
	public void shouldReturnCorrectNotes() {
		buildUp();
		myContact.addNotes("We better catch that pesky Joker.");
		String expectedNotes = "We better catch that pesky Joker.";
		String actualNotes = myContact.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}

	/**
	 * Tests that when a Contact object has a null notes field,
	 * getNotes() returns an empty String.
	 */
	public void shouldReturnEmptyString() {
		buildUp();
		String expectedNotes = "";
		String actualNotes = myContact.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}
}