package happ.es.happ;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import happ.es.services.HappService;
import happ.es.types.NavValoracionDia;
import happ.es.util.ConstantesValoracionDia;

public class VideoPostActivity extends AppCompatActivity {

    private HappService happService;

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_post);

        // SERVICIO
        happService = new HappService();
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void continuar(View view) {
        // Contestar pregunta y marcarla como vista
        happService.changeShowVideo(id);

        // Ir a la actividad
        Intent intent = new Intent(this, ValoracionDiaActivity.class);
        intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.MENU.name());
        this.finish();
        startActivity(intent);
    }

}
