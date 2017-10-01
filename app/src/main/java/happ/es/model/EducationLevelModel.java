package happ.es.model;

/**
 * The Class EducationLevel.
 * @author jorge
 * @version 1.0
 */
public class EducationLevelModel {
	
	/** The code. */
	private String code;
	
	/** The value. */
	private String value;
	
	/** The order. */
	private Integer ordered;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the ordered.
	 *
	 * @return the ordered
	 */
	public Integer getOrdered() {
		return ordered;
	}

	/**
	 * Sets the ordered.
	 *
	 * @param ordered the new ordered
	 */
	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return value;
	}

}
