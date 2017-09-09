package happ.es.happ;

import android.content.Intent;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.TypeResponse;
import happ.es.util.ConstantesActosBondad;
import happ.es.wrapper.HappResponseWrapper;

public class LogoActivity extends AppCompatActivity {

    private HappService happService;

    private ResponseModel response;

    private TextView textMensaje;

    private TextView btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        happService = new HappService();

        textMensaje = (TextView)findViewById(R.id.mensajes);
        btnContinuar = (TextView)findViewById(R.id.btnContinuar);
        btnContinuar.setVisibility(View.INVISIBLE);
        textMensaje.setVisibility(View.VISIBLE);

        response = happService.conectar(id);
        if (response.getTypeResponse() == TypeResponse.OK) {
            animarEntrada();
        } else {
            if (response == null || response.getError() == null) {
                textMensaje.setText("Conexión no válida");
            } else {
                textMensaje.setText(response.getError());
            }
        }

    }

    public void animarEntrada() {

            // Cargando out
            final AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setDuration(1500);
            fadeOut.setStartOffset(3000);
            fadeOut.setFillAfter(true);

            // Boton in
            final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
            fadeIn.setDuration(1500);
            fadeIn.setStartOffset(3000);
            fadeIn.setFillAfter(true);

            // Definir animación
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    textMensaje.setText("Bienvenido a");
                    btnContinuar.startAnimation(fadeIn);
                    textMensaje.startAnimation(fadeIn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            fadeIn.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            textMensaje.startAnimation(fadeOut);

    }

    public void continuar(View view) {
            Intent intent = new Intent(this, DeviceActivity.class);
            finish();
            startActivity(intent);
    }
}
