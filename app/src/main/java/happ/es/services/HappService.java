package happ.es.services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import happ.es.model.QuestionModel;
import happ.es.model.QuestionaryModel;
import happ.es.model.ResponseModel;
import happ.es.types.Gender;
import happ.es.types.MaritalStatus;
import happ.es.types.TypeResponse;
import happ.es.wrapper.HappResponseWrapper;


public class HappService {

    private static final String URL_BASE = "http://192.168.1.40:8080";

    public ResponseModel conectar(String id) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() == TypeResponse.ERROR) {
            model = new HappResponseWrapper().toModel(this.add(id));
        }
        return model;
    }


    public ResponseModel actualizarDispositivo(String id, int age, Gender gender, MaritalStatus maritalStatus, String codeEducationLevel) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            model = new HappResponseWrapper().toModel(update(id, age, gender, maritalStatus, codeEducationLevel));
        }
        return model;
    }

    public ResponseModel getAllEducationLevels(String id) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/happ/envirotment/educationLevels";
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }

    public ResponseModel getSessionsForAnswer(String id) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/happ/questionary/session/forAnswer?id=" + id;
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }

    public ResponseModel getAllQuestionary(String id) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/happ/questionary/all";
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }


    public ResponseModel sendQuestinary(String id, Long sessionId, QuestionaryModel questionaryModel) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            List<QuestionModel> questions = questionaryModel.getQuestions();
            for (QuestionModel q: questions) {
                String peticion = URL_BASE
                                + "/happ/questionary/session/answer?"
                                + "id=" + id
                                + "&session=" + sessionId
                                + "&answer=" + q.getAnswerSelected().getAnswerId();
                String response = hacerPeticion(peticion);
                //model = new HappResponseWrapper().toModel(response);
            }

        }
        return model;
    }

    public ResponseModel getListValorationsLastWeek(String id, String dd, String mm, String yyyy) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/happ/valuation/list"
                                       + "/" + dd
                                       + "/" + mm
                                       + "/" + yyyy
                                       + "?id=" + id;
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }

    private String search (String id) {
        StringBuffer salida = new StringBuffer();
        String peticion = URL_BASE + "/happ/device/search?id=" + id;
        return hacerPeticion(peticion);

    }

    private String add (String id) {
        StringBuffer salida = new StringBuffer();
        String peticion = URL_BASE + "/happ/device/add?id=" + id;
        return hacerPeticion(peticion);
    }


    private String update (String id, int age, Gender gender, MaritalStatus maritalStatus, String codeEducationLevel) {
        StringBuffer salida = new StringBuffer();
        String peticion = URL_BASE + "/happ/device/update?id=" + id
                                   + "&age=" + age
                                   + "&gender=" + gender.name()
                                   + "&maritalstatus=" + maritalStatus.name()
                                   + "&codeEducationLevel=" + codeEducationLevel;
        return hacerPeticion(peticion);
    }



    private String hacerPeticion (String peticion) {
        StringBuffer salida = new StringBuffer();
        HttpURLConnection urlConnection = null;
        try {

            URL url = new URL(peticion);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = rd.readLine()) != null) {
                salida.append(line);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return salida.toString();
    }

}
