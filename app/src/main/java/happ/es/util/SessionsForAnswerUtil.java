package happ.es.util;

import android.app.Activity;
import android.content.Intent;

import happ.es.happ.PanelControlActivity;
import happ.es.happ.QuestionaryActivity;
import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.TypeResponse;

/**
 * Created by jorge on 1/11/17.
 */

public class SessionsForAnswerUtil {


    public static void test(String id, HappService happService, Activity activity) {
        ResponseModel response = happService.getSessionsForAnswer(id);
        Intent intent = null;
        if (response.getTypeResponse() == TypeResponse.OK && response.getFirstSessionQuestionary() != null) {
            intent = new Intent(activity, QuestionaryActivity.class);
        } else {
            intent = new Intent(activity, PanelControlActivity.class);
        }
        activity.finish();
        activity.startActivity(intent);
    }


}
