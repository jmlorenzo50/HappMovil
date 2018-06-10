package happ.es.happ;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.VideoView;

import happ.es.model.DeviceModel;
import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.NavValoracionDia;
import happ.es.types.TypeGroup;
import happ.es.util.ConstantesValoracionDia;

public class VideoPreActivity extends AppCompatActivity {

    private HappService happService;

    private String id;

    private DeviceModel deviceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_pre);

        // SERVICIO
        happService = new HappService();
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        // Solo el grupo A y B tienen video
        ResponseModel device = happService.conectar(id);
        if (device != null && device.getDeviceModel() != null) {
            deviceModel = device.getDeviceModel();

            if (!TypeGroup.A.name().equals(deviceModel.getGroup()) &&
                    !TypeGroup.B.name().equals(deviceModel.getGroup())
                    ) {
                irValoracionDia();
            } else {
                if (ConstantesValoracionDia.YES.equals(deviceModel.getVideoView())) {
                    irValoracionDia();
                }
            }

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void irValoracionDia() {
        Intent intent = new Intent(this, ValoracionDiaActivity.class);
        intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.MENU.name());
        this.finish();
        startActivity(intent);
    }


    public void continuar(View view) {
        this.finish();
        Intent intent = new Intent(this, VideoPostActivity.class);
        startActivity(intent);
    }


}
