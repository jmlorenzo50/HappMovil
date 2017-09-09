package happ.es.model;

import happ.es.types.Gender;

/**
 * The Class DeviceModel.
 */
public class DeviceModel {
	
	/** The android id. */
	private String androidId;
	
	/** The gender. */
	private Gender gender;
	
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
	

}
