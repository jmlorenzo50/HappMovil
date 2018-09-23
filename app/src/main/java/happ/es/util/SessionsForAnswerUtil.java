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

    public static int numeroCuestionario(String id, HappService happService)  {

        ResponseModel response = happService.numeroCuestionario(id);
        int salida =  response.getNumeroCuestionario();
        return salida;
    }

    public static void test(String id, HappService happService, Activity activity) {
        boolean esUltimo = esUltimoCuestionario(id, happService);
        test(activity, esUltimo);
    }

    public static boolean esUltimoCuestionario(String id, HappService happService) {
        boolean salida = false;
        ResponseModel response = happService.getSessionsForAnswer(id);
        Intent intent = null;
        if (response.getTypeResponse() == TypeResponse.OK && response.getFirstSessionQuestionary() != null) {
            salida = false;
        } else {
            salida = true;
        }
        return salida;
    }

    public static void test(Activity activity, boolean esUltimo) {
        Intent intent = null;
        if (!esUltimo) {
            intent = new Intent(activity, QuestionaryActivity.class);
        } else {
            intent = new Intent(activity, PanelControlActivity.class);
        }
        activity.finish();
        activity.startActivity(intent);
    }

}
