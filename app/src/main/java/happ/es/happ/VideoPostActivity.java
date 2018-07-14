package happ.es.happ;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import happ.es.model.DeviceModel;
import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.NavValoracionDia;
import happ.es.types.TypeGroup;
import happ.es.util.ConstantesValoracionDia;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static happ.es.happ.R.id.bienestar;

public class VideoPostActivity extends AppCompatActivity {

    private HappService happService;

    private String id;
    private DeviceModel deviceModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_post);
       //TEXTVIEW
        TextView myTextViewb = (TextView)findViewById(R.id.preguntaGrupoB);
        TextView myTextViewa = (TextView)findViewById(R.id.preguntaGrupoA);


        // SERVICIO
        happService = new HappService();
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        //Segun que grup osale un texto u otro

        ResponseModel device = happService.conectar(id);
        if (device != null && device.getDeviceModel() != null) {
            deviceModel = device.getDeviceModel();

            if (TypeGroup.A.name().equals(deviceModel.getGroup())) {
                myTextViewb.setVisibility(View.GONE);
            } else if (TypeGroup.B.name().equals(deviceModel.getGroup())) {
                myTextViewa.setVisibility(View.GONE);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void continuar(View view) {



        //respuesta
        EditText myRespuestaVideo = (EditText)findViewById(R.id.respuestaGrupos);
        String videoAnswer = myRespuestaVideo.getText().toString();

        //valoracion video
        SeekBar myValoracionVideo = (SeekBar) findViewById(R.id.valoracionVideo);
        int videoValue =  myValoracionVideo.getProgress();

        Long videoValue2=new Long( videoValue);
        // Contestar pregunta y marcarla como vista
        happService.changeShowVideo(id, videoAnswer, videoValue2);



        // Ir a la actividad
        Intent intent = new Intent(this, ValoracionDiaActivity.class);
        intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.MENU.name());
        this.finish();
        startActivity(intent);
    }

}
