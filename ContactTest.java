import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests the class Contact.
 *
 * @author Gareth Moore.
 */
public class ContactTest {

	/**
	 * Tests that the returned contact ID matches the ID assigned during instantiation.
	 */
	public void shouldReturnCorrectId() {
		Contact myContact = new Contact();
		int expectedId = 1;
		int actualId = myContact.getId();
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests that the returned name matches the name created during instantiation.
	 */
	public void shouldReturnCorrectName() {
		Contact myContact = new Contact();
		String expectedName = "Bruce Wayne";
		String actualName = myContact.getName();
		assertEquals(expectedName, actualName);
	}

	/**
	 * Tests that the returned notes matches the notes assigned to the Contact object.
	 */
	public void shouldReturnCorrectNotes() {
		Contact myContact = new Contact();
		String expectedNotes = "We better catch that pesky Joker.";
		String actualNotes = myContact.getNotes();
		assertEquals(expectedNotes, actualNotes);
	}

}