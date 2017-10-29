package es.happ.server.model;

/**
 * The Class AnswerEntity.
 */
public class AnswerModel implements HappModel{
	
	/** The answer id. */
	private Long answerId;
	
	/** The text. */
	private Integer value;
	
	/** The text. */
	private String text;
	
	/**
	 * Instantiates a new answer entity.
	 */
	public AnswerModel() {
		super();
	}

	/**
	 * Gets the answer id.
	 *
	 * @return the answer id
	 */
	public Long getAnswerId() {
		return answerId;
	}

	/**
	 * Sets the answer id.
	 *
	 * @param answerId the new answer id
	 */
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}


}
