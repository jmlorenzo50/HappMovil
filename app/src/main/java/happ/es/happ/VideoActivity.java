package happ.es.happ;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
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

public class VideoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        VideoView myVideoView = (VideoView)findViewById(R.id.myvideoA);
        //myVideoView.setVideoURI(Uri.parse(SrcPath));
        myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.videoa));
        //myVideoView.setMediaController(new MediaController(this));

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


    public void continuar() {
        Intent intent = new Intent(this, VideoPostActivity.class);
        this.finish();
        startActivity(intent);
    }

}
