package happ.es.components.questionary;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import happ.es.happ.QuestionaryActivity;
import happ.es.happ.R;
import happ.es.model.AnswerModel;
import happ.es.model.QuestionModel;


/**
 * Created by jorge on 22/10/17.
 */

public class QuestionaryOnClickListener implements View.OnClickListener {

    private List<TextView> btnAnswers;

    private int activar;

    private QuestionModel question;

    private AnswerModel answer;

    private QuestionaryActivity questionaryActivity;

    public QuestionaryOnClickListener(List<TextView> btnAnswers, int activar, QuestionModel question, AnswerModel answer, QuestionaryActivity questionaryActivity) {
        this.btnAnswers = btnAnswers;
        this.activar = activar;
        this.question = question;
        this.answer = answer;
        this.questionaryActivity = questionaryActivity;
    }

    @Override
    public void onClick(View view) {
      /** for (int i=0; i<btnAnswers.size(); i++) {
            btnAnswers.get(i).setBackgroundResource(R.drawable.boton_largo2_amarillo);
        }**/
        btnAnswers.get(activar).setBackgroundResource(R.drawable.boton_largo_verde2);
        question.setAnswerSelected(answer);
        questionaryActivity.siguiente();
    }
}
