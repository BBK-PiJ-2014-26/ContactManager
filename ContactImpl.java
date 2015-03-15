/**
 * Implements the Contact interface.
 *
 * @author Gareth Moore.
 */
public class ContactImpl implements Contact {

	private int Id;
	private String name;
	private String notes;

	/**
	 * Contstructor method. Creates a Contact object.
	 *
	 * @param name is the name of the newly created contact.
	 */
	public ContactImpl(String name) {
		this.name = name;
	}

	public int getId() {
		return 1;
	}

	public String getName() {
		return name;
	}

	public String getNotes() {
		String result = ""; // If the notes field is null, then the mthod should reurn an empty string.
		if(notes != null) {
			result = notes; // If the notes field has a value, then the empty string is replaced by the field's contents.
		}
		return result;
	}

	public void addNotes(String note) {
		this.notes = note;
	}
}