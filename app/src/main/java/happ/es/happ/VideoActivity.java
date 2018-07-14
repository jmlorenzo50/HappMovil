package happ.es.happ;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.MediaController;
import android.widget.VideoView;

import happ.es.model.DeviceModel;
import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.TypeGroup;


public class VideoActivity extends AppCompatActivity {

    private HappService happService;

    private String id;

    private DeviceModel deviceModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        VideoView myVideoView = (VideoView)findViewById(R.id.myvideoA);
        //myVideoView.setVideoURI(Uri.parse(SrcPath));

        // SERVICIO
        happService = new HappService();
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        // Solo el grupo A y B tienen video
        ResponseModel device = happService.conectar(id);
        if (device != null && device.getDeviceModel() != null) {
            deviceModel = device.getDeviceModel();

            if (TypeGroup.A.name().equals(deviceModel.getGroup())) {
                myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoa));
            } else if (TypeGroup.B.name().equals(deviceModel.getGroup())) {
                myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videob));
            }
            //comentar esto para no ver el control
            myVideoView.setMediaController(new MediaController(this));

            myVideoView.setOnCompletionListener(
                    new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                        continuar();
                        }
                    }
            );


            myVideoView.requestFocus();
            myVideoView.start();


        }

    }


    public void continuar() {
        Intent intent = new Intent(this, VideoPostActivity.class);
        this.finish();
        startActivity(intent);
    }

}
