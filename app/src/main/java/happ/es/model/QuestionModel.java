package es.happ.server.model;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class QuestionModel.
 */
public class QuestionModel implements HappModel {

	/** The question id. */
	private Long questionId;
	
	/** The description. */
	private String description;
	
	/** The statement. */
	private String statement;
	
	/** The answers. */
	private Set<AnswerModel> answers = new HashSet<AnswerModel>();

	
	/**
	 * Instantiates a new question entity.
	 */
	public QuestionModel() {
		super();
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the statement.
	 *
	 * @return the statement
	 */
	public String getStatement() {
		return statement;
	}

	/**
	 * Sets the statement.
	 *
	 * @param statement the new statement
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}

	/**
	 * Gets the answers.
	 *
	 * @return the answers
	 */
	public Set<AnswerModel> getAnswers() {
		return answers;
	}

	/**
	 * Sets the answers.
	 *
	 * @param answers the new answers
	 */
	public void setAnswers(Set<AnswerModel> answers) {
		this.answers = answers;
	}
	

	
}
