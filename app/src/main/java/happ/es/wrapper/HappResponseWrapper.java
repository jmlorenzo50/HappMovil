package happ.es.wrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import happ.es.model.DeviceModel;
import happ.es.model.EducationLevelModel;
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

            // Lectura de los datos del mensaje
            JSONObject reader = new JSONObject(datajson);
            model.setTypeResponse(TypeResponse.valueOf(reader.getString("typeResponse")));
            model.setError(reader.getString("error"));

            // Lectura de los datos del dispositivo
            DeviceModel device = new DeviceModel();
            if (exist(reader, "deviceModel")) {
                JSONObject deviceModel = reader.getJSONObject("deviceModel");
                //device.setAndroidId(reader.getString(""));
                //device.setGender(Gender.valueOf(reader.getString("")));
                model.setDeviceModel(device);
            }

            // Lectura de los niveles educativos
            if (exist(reader, "educationLevels")) {
                List<EducationLevelModel> educationLevels = new ArrayList<EducationLevelModel>();
                JSONArray educationLevelModel = reader.getJSONArray("educationLevels");

                for (int i = 0; i < educationLevelModel.length(); i++) {
                    JSONObject jsonobject = educationLevelModel.getJSONObject(i);

                    EducationLevelModel elm = new EducationLevelModel();
                    elm.setCode(jsonobject.getString("code"));
                    elm.setValue(jsonobject.getString("value"));
                    elm.setOrdered(jsonobject.getInt("ordered"));
                    educationLevels.add(elm);
                }

                model.setEducationLevels(educationLevels);
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
        return model;
    }


    public boolean exist(JSONObject reader, String field) {
        boolean value = true;
        try {
            JSONObject salida = reader.getJSONObject(field);
        } catch (Exception e1) {
            try {
                JSONArray salida = reader.getJSONArray(field);
            } catch (Exception e2) {
                value = false;
            }
        }
        return value;
    }

}
