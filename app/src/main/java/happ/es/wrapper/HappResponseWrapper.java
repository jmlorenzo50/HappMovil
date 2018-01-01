package happ.es.wrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import happ.es.model.AnswerModel;
import happ.es.model.DeviceModel;
import happ.es.model.EducationLevelModel;
import happ.es.model.QuestionModel;
import happ.es.model.QuestionaryModel;
import happ.es.model.ResponseModel;
import happ.es.model.SessionQuestionaryModel;
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
            if (exist(reader, "device")) {
                JSONObject deviceModel = reader.getJSONObject("device");
                device.setAndroidId(deviceModel.getString("androidId"));
                device.setGender(Gender.valueOf(deviceModel.getString("gender")));
                device.setAge(deviceModel.getInt("age"));
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

            // Lectura de las sesiones pendientes
            if (exist(reader, "firstSessionQuestionary")) {
                JSONObject deviceModel = reader.getJSONObject("firstSessionQuestionary");

                SessionQuestionaryModel sqm = new SessionQuestionaryModel();
                sqm.setSessionId(deviceModel.getLong("scheduledTaskQuestionaryId"));
                sqm.setAndroidId(deviceModel.getString("androidId"));
                //sqm.setDateSession(deviceModel.getLong("dateSession"));
                sqm.setFinished(deviceModel.getString("finishedDate") != null);
                //@TODO Establecer las respuestas ya procesadas
                //sqm.setSessionAnswers();

                model.setFirstSessionQuestionary(sqm);
            }

            // Lectura de los cuestionarios
            if (exist(reader, "questionary")) {
                JSONArray questionaries = reader.getJSONArray("questionary");
                JSONObject questionary = questionaries.getJSONObject(0); // TODO Pedir el cuestionario activo

                QuestionaryModel qm = new QuestionaryModel();
                qm.setQuestionaryId(questionary.getLong("questionaryId"));
                qm.setDescription(questionary.getString("description"));
                qm.setStatement(questionary.getString("statement"));

                JSONArray questions = questionary.getJSONArray("questions");
                List<QuestionModel> questionsModel = new ArrayList<>();

                for (int i = 0; i < questions.length(); i++) {
                    JSONObject jsonobject = questions.getJSONObject(i);

                    QuestionModel qqm = new QuestionModel();
                    qqm.setQuestionId(jsonobject.getLong("questionId"));
                    qqm.setDescription(jsonobject.getString("description"));
                    qqm.setStatement(jsonobject.getString("statement"));

                    List<AnswerModel> answerModel = new ArrayList<>();
                    JSONArray answers = jsonobject.getJSONArray("answers");
                    for (int a = 0; a < answers.length(); a++) {
                        JSONObject jsonanswer = answers.getJSONObject(a);

                        AnswerModel aam = new AnswerModel();
                        aam.setAnswerId(jsonanswer.getLong("answerId"));
                        aam.setValue(jsonanswer.getInt("value"));
                        aam.setText(jsonanswer.getString("text"));

                        answerModel.add(aam);
                    }

                    qqm.setAnswers(answerModel);

                    questionsModel.add(qqm);
                }


                qm.setQuestions(questionsModel);

                model.setQuestionaryModel(qm);
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
