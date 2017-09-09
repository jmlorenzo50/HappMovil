package happ.es.wrapper;

import org.json.JSONObject;

import happ.es.model.DeviceModel;
import happ.es.model.ResponseModel;
import happ.es.types.Gender;
import happ.es.types.TypeResponse;

/**
 * Created by jorge on 5/09/17.
 */

public class HappResponseWrapper {


    public ResponseModel toModel (String datajson) {
        ResponseModel model = new ResponseModel();
        try {

            JSONObject reader = new JSONObject(datajson);
            model.setTypeResponse(TypeResponse.valueOf(reader.getString("typeResponse")));
            model.setError(reader.getString("error"));

            DeviceModel device = new DeviceModel();
            JSONObject deviceModel = reader.getJSONObject("deviceModel");
            device.setAndroidId(reader.getString(""));
            device.setGender(Gender.valueOf(reader.getString("")));
            model.setDeviceModel(device);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return model;
    }

}
