package happ.es.happ;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import happ.es.model.EducationLevelModel;
import happ.es.model.ResponseModel;
import happ.es.services.HappService;
import happ.es.types.Gender;
import happ.es.types.MaritalStatus;
import happ.es.types.ParamIntent;
import happ.es.types.TypeResponse;
import happ.es.util.SessionsForAnswerUtil;

public class DeviceActivity extends AppCompatActivity {

    private HappService happService;

    private ImageView btnMan, btnWoman;

    private ImageView btnState1, btnState2, btnState3, btnState4;

    private Spinner cmbEducationLevel;

    private boolean selectedMan;

    private MaritalStatus maritalStatus;

    private EditText txtAge;

    private Integer age;

    private String id;

    private List<EducationLevelModel> elvs;

    private EducationLevelModel elv;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        // Declaración de componentes
        age = -1;
        selectedMan = true;

        // Inicialización de los componentes gráficos
        selectedMan = true;
        btnMan = (ImageView) findViewById(R.id.btnMan);
        btnWoman = (ImageView) findViewById(R.id.btnWoman);

        maritalStatus = MaritalStatus.SINGLE;
        btnState1= (ImageView) findViewById(R.id.btnState1);
        btnState2= (ImageView) findViewById(R.id.btnState2);
        btnState3= (ImageView) findViewById(R.id.btnState3);
        btnState4= (ImageView) findViewById(R.id.btnState4);

        cmbEducationLevel = (Spinner) findViewById(R.id.educationLevel);

        txtAge = (EditText)findViewById(R.id.age);

        // Inicialización de las variables lógicas
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        happService = new HappService();

        // Obtención del os niveles educativos para la recarga del combo
        ResponseModel response = happService.getAllEducationLevels(id);
        elvs = response.getEducationLevels();

        ArrayAdapter<EducationLevelModel> adapter = new ArrayAdapter<EducationLevelModel>(getApplicationContext(),
                android.R.layout.simple_spinner_item, elvs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbEducationLevel.setAdapter(adapter);

        // Operativa de validación de datos
        validarExistenDatos();
    }


    public void validarExistenDatos() {
        Bundle extras = this.getIntent().getExtras();
        if (extras.getBoolean(ParamIntent.VALIDAR_EXISTEN_DATOS.name())) {
            ResponseModel device = happService.conectar(id);

            if (device.getDeviceModel().getAge() > 0) {
                Intent intent = new Intent(this, PanelControlActivity.class);
                finish();
                startActivity(intent);
            }
        } else {
            ResponseModel device = happService.conectar(id);
            if (device != null && device.getDeviceModel() != null) {
                age =device.getDeviceModel().getAge();
                selectedMan = (device.getDeviceModel().getGender() == Gender.MAN);
                maritalStatus = device.getDeviceModel().getMartialStatus();

                String code =  device.getDeviceModel().getEducationLevelCode();
                elv = null;
                this.position = 0;
                if (elvs != null) {
                    for (int p = 0; p < elvs.size(); p++) {
                        EducationLevelModel educationLevel = elvs.get(p);
                        if (educationLevel.getCode().equals(code)) {
                            elv = educationLevel;
                            this.position = p;
                        }
                    }
                }

            }
            pintarEstado();
        }

    }

    public void pintarEstado() {

        if (age >0) {
            txtAge.setText("" + age);
        }

        if (selectedMan) {
            btnMan.setImageResource(R.drawable.happ_man_on);
            btnWoman.setImageResource(R.drawable.happ_woman_off);
        } else {
            btnMan.setImageResource(R.drawable.happ_man_off);
            btnWoman.setImageResource(R.drawable.happ_woman_on);
        }

        if (maritalStatus == MaritalStatus.SINGLE) {
            btnState1.setImageResource(R.drawable.estado_civil_1_on);
            btnState2.setImageResource(R.drawable.estado_civil_2_off);
            btnState3.setImageResource(R.drawable.estado_civil_3_off);
            btnState4.setImageResource(R.drawable.estado_civil_4_off);
        } else if (maritalStatus == MaritalStatus.MARRIED) {
            btnState1.setImageResource(R.drawable.estado_civil_1_off);
            btnState2.setImageResource(R.drawable.estado_civil_2_on);
            btnState3.setImageResource(R.drawable.estado_civil_3_off);
            btnState4.setImageResource(R.drawable.estado_civil_4_off);
        } else if (maritalStatus == MaritalStatus.DIVORCED) {
            btnState1.setImageResource(R.drawable.estado_civil_1_off);
            btnState2.setImageResource(R.drawable.estado_civil_2_off);
            btnState3.setImageResource(R.drawable.estado_civil_3_on);
            btnState4.setImageResource(R.drawable.estado_civil_4_off);

        } else if (maritalStatus == MaritalStatus.WIDOWED) {
            btnState1.setImageResource(R.drawable.estado_civil_1_off);
            btnState2.setImageResource(R.drawable.estado_civil_2_off);
            btnState3.setImageResource(R.drawable.estado_civil_3_off);
            btnState4.setImageResource(R.drawable.estado_civil_4_on);
        }


        if (elv != null) {
            cmbEducationLevel.setSelection(position);
        }

    }


    public void continuar(View view) {


        if (txtAge.getText().toString() != null && txtAge.getText().toString().trim().length() > 0) {
            age = Integer.parseInt(txtAge.getText().toString().trim());
            if (age < 20) {
                age = 20;
            } else if (age > 100) {
                age = 100;
            }
        } else {
            age = -1;
        }

        EducationLevelModel educationLevelModel = (EducationLevelModel)cmbEducationLevel.getSelectedItem();

        ResponseModel response = happService.actualizarDispositivo(id, age, selectedMan? Gender.MAN: Gender.WOMAN, maritalStatus, educationLevelModel.getCode());
        if (response.getTypeResponse() == TypeResponse.OK) {

            SessionsForAnswerUtil.test(id, happService, this);

            /*response = happService.getSessionsForAnswer(id);
            Intent intent = null;
            if (response.getTypeResponse() == TypeResponse.OK && response.getFirstSessionQuestionary() != null) {
                intent = new Intent(this, QuestionaryActivity.class);
            } else {
                intent = new Intent(this, PanelControlActivity.class);
            }
            finish();
            startActivity(intent);*/

        }

    }

    public void selectedMan(View view) {
        if (!selectedMan) {
            selectedMan = true;
            pintarEstado();        }
    }

    public void selectedWoman(View view) {
        if (selectedMan) {
            selectedMan = false;
            pintarEstado();        }
    }


    public void selectedState1(View view) {
        maritalStatus = MaritalStatus.SINGLE;
        pintarEstado();
    }

    public void selectedState2(View view) {
        maritalStatus = MaritalStatus.MARRIED;
        pintarEstado();
    }

    public void selectedState3(View view) {
        maritalStatus = MaritalStatus.DIVORCED;
        pintarEstado();
    }

    public void selectedState4(View view) {
        maritalStatus = MaritalStatus.WIDOWED;
        pintarEstado();
    }
}
