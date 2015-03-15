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
	public shouldReturnCorrectID() {
		Contact myContact = new Contact();
		int expectedId = 1;
		int actualId = myContact.getId();
		assertEquals(expectedId, actualId);
	}
}