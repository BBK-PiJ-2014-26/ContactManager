/**
 * Implements the Contact interface.
 *
 * @author Gareth Moore.
 */
public class ContactImpl implements Contact {
	/**
	 * A unique id for every Contact object.
	 * Cannot be negative number or zero.
	 */
	private int id;
	/**
	 * The name of each Contact object.
	 * It is not necessarily unique.
	 */
	private String name;
	/**
	 * Meeting notes to be held against each contact.
	 * If not relevaant, a null value is held.
	 */
	private String notes;
	/**
	 * A static counter which tracks the most recently used id.
	 * Each time a constructor is called, a nextId() call increments the counter.
	 * When the ContactManager application is restarted,
	 * the value is reassigned using the most recently used id.
	 */
	public static int iDCounter = 0;


	/**
	 * Contstructor method. Creates a Contact object.
	 *
	 * On every call, a unique id is assigned using nextId().
	 *
	 * @param name is the name of the newly created contact.
	 */
	public ContactImpl(String name) {
		this.name = name;
		this.notes = null;
		this.id = nextId();
	}

	public int getId() {
		return id;
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

	/**
	 * Increments the iDCounter by 1.
	 */
	public int nextId() {
		iDCounter++;
		return iDCounter;
	}
}