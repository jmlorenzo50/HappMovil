package happ.es.services;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.util.List;

import happ.es.model.QuestionModel;
import happ.es.model.QuestionaryModel;
import happ.es.model.ResponseModel;
import happ.es.types.Gender;
import happ.es.types.MaritalStatus;
import happ.es.types.TypeResponse;
import happ.es.wrapper.HappResponseWrapper;


public class HappService extends HappServiceComun {

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
            String peticion = URL_BASE + "/envirotment/educationLevels";
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }

    public ResponseModel getSessionsForAnswer(String id) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/questionary/session/forAnswer?id=" + id;
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }
    //MGL
    public ResponseModel numeroCuestionario(String id) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/questionary/session/numeroCuestionario?id=" + id;
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }

    public ResponseModel getAllQuestionary(String id) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/questionary/all";
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
                                + "/questionary/session/answer?"
                                + "id=" + id
                                + "&session=" + sessionId
                                + "&answer=" + q.getAnswerSelected().getAnswerId();
                String response = hacerPeticion(peticion);
                //model = new HappResponseWrapper().toModel(response);
            }

        }
        return model;
    }

    public ResponseModel getListValorationsLastWeek(String id, int dd, int mm, int yyyy) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/valuation/list"
                                       + "/" + dd
                                       + "/" + mm
                                       + "/" + yyyy
                                       + "?id=" + id;
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }


    public void wellness(String id, int valueGood, int valueBad) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/valuation/wellness"
                    + "/" + valueGood
                    + "/" + valueBad
                    + "?id=" + id;
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
            if (model.getTypeResponse() != TypeResponse.ERROR) {

            }
        }
    }


    public void valuationAdd(String id, List<String> listaValues ) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {

            for (String text : listaValues) {

                if (text != null && text.trim().length() > 0) {

                    byte[] data = text.getBytes(StandardCharsets.UTF_8);
                    String base64 = Base64.encodeToString(data, Base64.DEFAULT);

                    String peticion = URL_BASE + "/valuation/add"
                            + "?id=" + id
                            + "&text=" + base64;
                    String response = hacerPeticion(peticion);
                    model = new HappResponseWrapper().toModel(response);
                    if (model.getTypeResponse() != TypeResponse.ERROR) {

                    }
                }
            }
        }
    }


    public ResponseModel changeShowVideo(String id, String videoAnswer, Long videoValue) {
        ResponseModel model = new HappResponseWrapper().toModel(this.search(id));
        if (model.getTypeResponse() != TypeResponse.ERROR) {
            String peticion = URL_BASE + "/device/changeShowVideo"
                    + "?id=" + id
                    + "&videoAnswer=" + videoAnswer
                    + "&videoValue=" + videoValue;
            String response = hacerPeticion(peticion);
            model = new HappResponseWrapper().toModel(response);
        }
        return model;
    }



}
