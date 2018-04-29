package happ.es.components.questionary;

/**
 * Created by jorge on 13/10/17.
 */

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import happ.es.happ.QuestionaryActivity;
import happ.es.happ.R;
import happ.es.model.AnswerModel;
import happ.es.model.QuestionModel;
import happ.es.model.QuestionaryModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String ARG_TOTAL_SECTION_NUMBER = "total_section_number";

    private static final String ARG_PREGUNTA = "pregunta";
    private static final String ARG_DESCRIPCIONMARTA = "descriptionmarta";
    private static final String ARG_STATEMENT = "statement";

    private static QuestionaryModel questionary;

    private static QuestionaryActivity questionaryActivity;

    private QuestionModel question;

    private Integer pulsado;

    private List<TextView> btnAnswers;



    public PlaceholderFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, QuestionaryModel questionary, QuestionaryActivity questionaryActivity) {
        PlaceholderFragment.questionary = questionary;
        PlaceholderFragment.questionaryActivity = questionaryActivity;


        PlaceholderFragment fragment = new PlaceholderFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putInt(ARG_TOTAL_SECTION_NUMBER, questionary.getQuestions().size());

        QuestionModel q = (QuestionModel) questionary.getQuestions().toArray()[sectionNumber-1];
        args.putString(ARG_PREGUNTA, q.getStatement());
        args.putString(ARG_DESCRIPCIONMARTA, questionary.getDescription());
        args.putString(ARG_STATEMENT, questionary.getStatement());


        fragment.setArguments(args);
        fragment.setQuestion(q);


        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_questionary, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.paginador_cuestinario,
                                   getArguments().getInt(ARG_SECTION_NUMBER),
                                   getArguments().getInt(ARG_TOTAL_SECTION_NUMBER)));

        TextView textViewPregunta = (TextView) rootView.findViewById(R.id.pregunta);
        textViewPregunta.setText(getArguments().getString(ARG_PREGUNTA));

        TextView textViewStatement= (TextView) rootView.findViewById(R.id.statement);
        textViewStatement.setText(getArguments().getString(ARG_STATEMENT));

        TextView textViewDescripcion = (TextView) rootView.findViewById(R.id.descriptionmarta);
        textViewDescripcion.setText(getArguments().getString(ARG_DESCRIPCIONMARTA));

        // Inicializar los botones
        LinearLayout btnsContainer = (LinearLayout)rootView.findViewById(R.id.btnContainer);
        TextView btnAnswer = null;


        btnAnswers = new ArrayList<>();
        for (int i = 0; i < question.getAnswers().size(); i++){

            AnswerModel answer = question.getAnswers().get(i);

            btnAnswer = new TextView(rootView.getContext());

            btnAnswer.setText(answer.getText());
            btnAnswer.setTag(i);
            if (question.getAnswerSelected() != null && answer == question.getAnswerSelected()) {
                btnAnswer.setBackgroundResource(R.drawable.boton_largo_verde2);
            } else {
                btnAnswer.setBackgroundResource(R.drawable.boton_largo2_amarillo);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(1,1,1,1);
            params.gravity = Gravity.CENTER;
            btnAnswer.setLayoutParams(params);

            /*Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/sans-serif.ttf");
            btnAnswer.setTypeface(tf);*/
            btnAnswer.setTextColor(getResources().getColor(R.color.blanco));
            btnAnswer.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

            btnAnswer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            btnAnswer.setGravity(Gravity.CENTER);
            btnAnswer.setOnClickListener(new QuestionaryOnClickListener(btnAnswers, i, question, answer, questionaryActivity));
            btnAnswers.add(btnAnswer);
            btnsContainer.addView(btnAnswer);
        }

        return rootView;
    }

    public QuestionModel getQuestion() {
        return question;
    }

    public void setQuestion(QuestionModel question) {
        this.question = question;
    }
    public void setDescription(QuestionModel question) {
        this.question = question;
    }
}
