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
	public shouldReturnCorrectId() {
		Contact myContact = new Contact();
		int expectedId = 1;
		int actualId = myContact.getId();
		assertEquals(expectedId, actualId);
	}

	/**
	 * Tests that the returned name matches the name created during instantiation.
	 */
	public shouldReturnCorrectName() {
		Contact myContact = new Contact();
		String expectedName = "Bruce Wayne";
		String actualName = myContact.getName();
		assertEquals(expectedName, actualName);
	}
}