package happ.es.happ;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.Gender;
import happ.es.types.TypeResponse;

public class DeviceActivity extends AppCompatActivity {

    private HappService happService;

    private ImageView btnMan;

    private ImageView btnWoman;

    private boolean selectedMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        happService = new HappService();

        selectedMan = true;
        btnMan = (ImageView) findViewById(R.id.btnMan);
        btnWoman = (ImageView) findViewById(R.id.btnWoman);
    }


    public void continuar(View view) {
        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        ResponseModel response = happService.actualizarDispositivo(id, -1, selectedMan? Gender.MAN: Gender.WOMAN);

        if (response.getTypeResponse() == TypeResponse.OK) {
            Intent intent = new Intent(this, PanelControlActivity.class);
            finish();
            startActivity(intent);
        }

    }

    public void selectedMan(View view) {
        if (!selectedMan) {
            selectedMan = true;
            btnMan.setImageResource(R.drawable.happ_man_on);
            btnWoman.setImageResource(R.drawable.happ_woman_off);
        }
    }

    public void selectedWoman(View view) {
        if (selectedMan) {
            selectedMan = false;
            btnMan.setImageResource(R.drawable.happ_man_off);
            btnWoman.setImageResource(R.drawable.happ_woman_on);
        }
    }

}
