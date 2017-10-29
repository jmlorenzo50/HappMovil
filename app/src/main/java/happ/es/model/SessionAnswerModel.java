package es.happ.server.model;


/**
 * The Class SessionAnswer.
 */
public class SessionAnswerModel  implements HappModel{
	
	/** The session answer id. */
	private Long sessionAnswerId;
	
	/** The questionary id. */
	private Long questionaryId;

	/** The question id. */
	private Long questionId;
	
	/** The answer id. */
	private Long answerId;

	/**
	 * Instantiates a new session answer.
	 */
	public SessionAnswerModel() {
		super();
	}

	/**
	 * Gets the session answer id.
	 *
	 * @return the session answer id
	 */
	public Long getSessionAnswerId() {
		return sessionAnswerId;
	}

	/**
	 * Sets the session answer id.
	 *
	 * @param sessionAnswerId the new session answer id
	 */
	public void setSessionAnswerId(Long sessionAnswerId) {
		this.sessionAnswerId = sessionAnswerId;
	}

	/**
	 * Gets the questionary id.
	 *
	 * @return the questionary id
	 */
	public Long getQuestionaryId() {
		return questionaryId;
	}

	/**
	 * Sets the questionary id.
	 *
	 * @param questionaryId the new questionary id
	 */
	public void setQuestionaryId(Long questionaryId) {
		this.questionaryId = questionaryId;
	}

	/**
	 * Gets the question id.
	 *
	 * @return the question id
	 */
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * Sets the question id.
	 *
	 * @param questionId the new question id
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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
	
	

}
