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
import android.widget.Toast;

import happ.es.model.DeviceModel;
import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.NavValoracionDia;
import happ.es.types.TypeGroup;
import happ.es.util.ConstantesValoracionDia;

import static happ.es.types.TypeGroup.A;

public class VideoPostActivity extends AppCompatActivity {

    private HappService happService;

    private String id;

    private DeviceModel deviceModel;

    private boolean segundaOportunidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.segundaOportunidad = false;

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

            if (A.name().equals(deviceModel.getGroup())) {
                myTextViewb.setVisibility(View.GONE);
            } else if (TypeGroup.B.name().equals(deviceModel.getGroup())) {
                myTextViewa.setVisibility(View.GONE);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void continuar(View view) {
        String NOportunidad= "Primera";

        //respuesta
        EditText myRespuestaVideo = (EditText)findViewById(R.id.respuestaGrupos);
        String videoAnswer = myRespuestaVideo.getText().toString();

        //valoracion video
        SeekBar myValoracionVideo = (SeekBar) findViewById(R.id.valoracionVideo);
        int videoValue =  myValoracionVideo.getProgress();

        //si la respuesta es correcta envio respuesta y voy  a la actividad
        if ((videoAnswer.matches(".*HIJ*.") && (TypeGroup.A.name().equals(deviceModel.getGroup())))
                ||((videoAnswer.matches(".*CARAMEL*.") && (TypeGroup.B.name().equals(deviceModel.getGroup())))))
        {

            Long videoValue2=new Long( videoValue);
            // Contestar pregunta y marcarla como vista
            happService.changeShowVideo(id, videoAnswer, videoValue2);

            // Ir a la actividad
            Intent intent = new Intent(this, ValoracionDiaActivity.class);
            intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.MENU.name());
            this.finish();
            startActivity(intent);
        } else {
            if (segundaOportunidad) {

                //en caso contrario nueva oportunidad
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Su respuesta no es correcta.Para seguir deberá ver el video de nuevo y contestar correctamente.", Toast.LENGTH_LONG);
                toast1.show();
                // a ver el video de nuevo

                Intent intent = new Intent(this, VideoPreActivity.class);
                this.finish();
                startActivity(intent);


            } else {
                segundaOportunidad = true;

                //en caso contrario nueva oportunidad
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Su respuesta no es correcta.Inténtelo de nuevo.", Toast.LENGTH_SHORT);
                toast1.show();
                //segunda oportunidad si es correcto paso si no es correcto vuelvo a ver el video
            }

        }











    }

}
