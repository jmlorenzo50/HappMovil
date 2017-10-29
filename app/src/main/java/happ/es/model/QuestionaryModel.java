package es.happ.server.model;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class QuestionaryEntity.
 */
public class QuestionaryModel implements HappModel{

	/** The questionary id. */
	private Long questionaryId;
	
	/** The description. */
	private String description;
	
	/** The statement. */
	private String statement;
	
	/** The questions. */
	private Set<QuestionModel> questions = new HashSet<QuestionModel>();


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
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public Set<QuestionModel> getQuestions() {
		return questions;
	}

	/**
	 * Sets the questions.
	 *
	 * @param questions the new questions
	 */
	public void setQuestions(Set<QuestionModel> questions) {
		this.questions = questions;
	}
	
	
}
