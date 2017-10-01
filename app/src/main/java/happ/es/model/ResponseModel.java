package happ.es.model;

import java.util.List;

import happ.es.types.TypeResponse;

/**
 * The Class ResponseModel.
 */
public class ResponseModel {

    /** The type response. */
    private TypeResponse typeResponse;

    /** The error. */
    private String error;

    /** The device model. */
    private DeviceModel deviceModel;

    /** The education levels. */
    private List<EducationLevelModel> educationLevels;


    /**
     * Gets the type response.
     *
     * @return the type response
     */
    public TypeResponse getTypeResponse() {
        return typeResponse;
    }

    /**
     * Sets the type response.
     *
     * @param typeResponse the new type response
     */
    public void setTypeResponse(TypeResponse typeResponse) {
        this.typeResponse = typeResponse;
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Gets the device model.
     *
     * @return the device model
     */
    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    /**
     * Sets the device model.
     *
     * @param deviceModel the new device model
     */
    public void setDeviceModel(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
     * Gets the educations levels
     * @return all the educations levels ordered by field ORDERED
     */
    public List<EducationLevelModel> getEducationLevels() {
        return educationLevels;
    }

    /**
     * Sets the educations levels
     * @param educationLevels then new educations levels
     */
    public void setEducationLevels(List<EducationLevelModel> educationLevels) {
        this.educationLevels = educationLevels;
    }
}
