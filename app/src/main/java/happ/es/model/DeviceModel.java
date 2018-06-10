package happ.es.model;

import happ.es.types.Gender;
import happ.es.types.MaritalStatus;

/**
 * The Class DeviceModel.
 */
public class DeviceModel {
	
	/** The android id. */
	private String androidId;
	
	/** The gender. */
	private Gender gender;

	/** The age */
	private Integer age;

	/** The martial status */
	private MaritalStatus martialStatus;

	/** The education level code */
	private String educationLevelCode;

	/** The group */
	private String group;

	/** The video view. */
	private String videoView;


	/**
	 * Instantiates a new device model.
	 */
	public DeviceModel() {
		super();
	}

	/**
	 * Instantiates a new device model.
	 *
	 * @param androidId the android id
	 * @param gender the gender
	 */
	public DeviceModel(String androidId, Gender gender) {
		super();
		this.androidId = androidId;
		this.gender = gender;
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
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}


	/**
	 * Get the age
	 * @return
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Set the age
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 *
	 * @return
	 */
	public MaritalStatus getMartialStatus() {
		return martialStatus;
	}

	/**
	 *
	 * @param martialStatus
	 */
	public void setMartialStatus(MaritalStatus martialStatus) {
		this.martialStatus = martialStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getEducationLevelCode() {
		return educationLevelCode;
	}

	/**
	 *
	 * @param educationLevelCode
	 */
	public void setEducationLevelCode(String educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}

	/**
	 *
	 * @return
	 */
	public String getGroup() {
		return group;
	}

	/**
	 *
	 * @param group
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 *
	 * @return
	 */
	public String getVideoView() {
		return videoView;
	}

	/**
	 *
	 * @param videoView
	 */
	public void setVideoView(String videoView) {
		this.videoView = videoView;
	}
}
