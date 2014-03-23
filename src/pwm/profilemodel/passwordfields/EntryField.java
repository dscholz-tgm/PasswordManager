package pwm.profilemodel.passwordfields;

/**
 * A generic Password-Field
 * @author Adrian Bergler
 * @version 0.1
 */
public abstract class EntryField {
	
	protected String value;
	
	public EntryField(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public abstract byte[] getType();
	
}