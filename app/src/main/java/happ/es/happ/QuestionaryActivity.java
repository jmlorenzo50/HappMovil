package happ.es.happ;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import happ.es.components.questionary.HappViewPager;
import happ.es.components.questionary.SectionsPagerAdapter;
import happ.es.model.QuestionaryModel;
import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.util.SessionsForAnswerUtil;

public class QuestionaryActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    // Servicio HAPP
    private HappService happService;

    // Android ID
    private String id;

    // SessionId
    private Long sessionId;

    // Control de paginación
    private HappViewPager viewPager;

    // Cuestionario
    private QuestionaryModel questionaryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionary);

        // Inicialización de las variables lógicas
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        happService = new HappService();

        // Preparación de datos
        ResponseModel response = happService.getSessionsForAnswer(id);

        sessionId = response.getFirstSessionQuestionary().getSessionId();
        questionaryModel = response.getQuestionaryModel();

        // Preparación ventana
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.setQuestionaryModel(questionaryModel);
        mSectionsPagerAdapter.setQuestionaryActivity(this);

        // Set up the ViewPager with the sections adapter.
        viewPager =  (HappViewPager)findViewById(R.id.container);
        viewPager.setPagingEnabled(false);
        viewPager.setNumberQuestions(questionaryModel.getQuestions().size());
        viewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questionary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void siguiente() {
        // Si es la última pregunta
        boolean lastItem = viewPager.nextPage();
        if (lastItem) {


//NO FUNCIONA EL YA QUE RECOGE EL ID DEL CUESTIONARIO ANTES DE TERMINAR EL SEND Y SE REPITEN LOS CUESTIONARIOS DOS VECES

           // int numeroCuestionario = SessionsForAnswerUtil.numeroCuestionario(id, happService);
           // if (numeroCuestionario != 8) {
             //   Thread thread =  new Thread (new Runnable() {
           //     public void run() {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.reloj);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            //    }});
              //  thread.start();
            happService.sendQuestinary(id, sessionId, questionaryModel);
                    SessionsForAnswerUtil.test(id, happService, this);
       // }else {
             ////   happService.sendQuestinary(id, sessionId, questionaryModel);
             //   SessionsForAnswerUtil.test(id, happService, this);}

        }
    }
}
