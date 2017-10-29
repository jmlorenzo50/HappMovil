package es.happ.server.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * The Class SessionCuestionary.
 */
public class SessionQuestionaryModel implements HappModel{
	
	/** The session id. */
	private Long sessionId;
	
	/** The android id. */
	private String androidId;

	/** The date session. */
	private Timestamp dateSession;  
	
	/** The finished. */
	private Boolean finished;
	
	/** The session answers. */
	private Set<SessionAnswerModel> sessionAnswers = new HashSet<SessionAnswerModel>();
	

	/**
	 * Instantiates a new session cuestionary.
	 */
	public SessionQuestionaryModel() {
		super();
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId the new session id
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the android id.
	 *
	 * @return the android id
	 */
	public String getAndroidId() {
		return androidId;
	}

	/**
	 * Sets the android id.
	 *
	 * @param androidId the new android id
	 */
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	/**
	 * Gets the date session.
	 *
	 * @return the date session
	 */
	public Timestamp getDateSession() {
		return dateSession;
	}

	/**
	 * Sets the date session.
	 *
	 * @param dateSession the new date session
	 */
	public void setDateSession(Timestamp dateSession) {
		this.dateSession = dateSession;
	}

	/**
	 * Gets the finished.
	 *
	 * @return the finished
	 */
	public Boolean getFinished() {
		return finished;
	}

	/**
	 * Sets the finished.
	 *
	 * @param finished the new finished
	 */
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	/**
	 * Gets the session answers.
	 *
	 * @return the session answers
	 */
	public Set<SessionAnswerModel> getSessionAnswers() {
		return sessionAnswers;
	}

	/**
	 * Sets the session answers.
	 *
	 * @param sessionAnswers the new session answers
	 */
	public void setSessionAnswers(Set<SessionAnswerModel> sessionAnswers) {
		this.sessionAnswers = sessionAnswers;
	}

}
