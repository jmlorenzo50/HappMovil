package happ.es.happ;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import happ.es.model.ResponseModel;
import happ.es.model.ValorationsLastWeekModel;
import happ.es.services.HappService;
import happ.es.types.NavValoracionDia;
import happ.es.types.ParamIntent;
import happ.es.util.ConstantesValoracionDia;
import happ.es.util.DateUtil;

public class ValoracionDiaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private NavValoracionDia navegacion;

    private HappService happService;

    private ValorationsLastWeekModel valorationsLastWeekModel;

    private LinearLayout day0Nodatafound, day0Datafound;
    private LinearLayout day1Nodatafound, day1Datafound;
    private LinearLayout day2Nodatafound, day2Datafound;
    private LinearLayout day3Nodatafound, day3Datafound;
    private LinearLayout day4Nodatafound, day4Datafound;
    private LinearLayout day5Nodatafound, day5Datafound;
    private LinearLayout day6Nodatafound, day6Datafound;
    private LinearLayout day0Salir;

    private LinearLayout imageVal1;
    private LinearLayout imageVal2;
    private LinearLayout imageVal3;
    private LinearLayout imageVal4;
    private LinearLayout imageVal5;
    private LinearLayout imageVal6;
    private LinearLayout imageVal7;
    private LinearLayout linea1;
    private LinearLayout linea2;
    private LinearLayout linea3;
    private LinearLayout linea4;
    private LinearLayout linea5;
    private LinearLayout linea6;
    private LinearLayout linea7;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion_dia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       //añadir aqui si ya ha valorado hoy poner un mensaje ya has hecho la actividad de hoy Salir

        
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // SERVICIO
        happService = new HappService();
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        // OBJETOS
        day0Nodatafound = (LinearLayout) findViewById(R.id.day0Nodatafound);
        day0Datafound = (LinearLayout) findViewById(R.id.day0Datafound);
        day1Nodatafound = (LinearLayout) findViewById(R.id.day1Nodatafound);
        day1Datafound = (LinearLayout) findViewById(R.id.day1Datafound);
        day2Nodatafound = (LinearLayout) findViewById(R.id.day2Nodatafound);
        day2Datafound = (LinearLayout) findViewById(R.id.day2Datafound);
        day3Nodatafound = (LinearLayout) findViewById(R.id.day3Nodatafound);
        day3Datafound = (LinearLayout) findViewById(R.id.day3Datafound);
        day4Nodatafound = (LinearLayout) findViewById(R.id.day4Nodatafound);
        day4Datafound = (LinearLayout) findViewById(R.id.day4Datafound);
        day5Nodatafound = (LinearLayout) findViewById(R.id.day5Nodatafound);
        day5Datafound = (LinearLayout) findViewById(R.id.day5Datafound);
        day6Nodatafound = (LinearLayout) findViewById(R.id.day6Nodatafound);
        day6Datafound = (LinearLayout) findViewById(R.id.day6Datafound);
        day0Salir = (LinearLayout) findViewById(R.id.day0Salir);

        imageVal1= (LinearLayout) findViewById(R.id.imageVal1);
        imageVal2= (LinearLayout) findViewById(R.id.imageVal2);
        imageVal3= (LinearLayout) findViewById(R.id.imageVal3);
        imageVal4= (LinearLayout) findViewById(R.id.imageVal4);
        imageVal5= (LinearLayout) findViewById(R.id.imageVal5);
        imageVal6= (LinearLayout) findViewById(R.id.imageVal6);
        imageVal7= (LinearLayout) findViewById(R.id.imageVal7);

        linea1= (LinearLayout) findViewById(R.id.linea1);
        linea2= (LinearLayout) findViewById(R.id.linea2);
        linea3= (LinearLayout) findViewById(R.id.linea3);
        linea4= (LinearLayout) findViewById(R.id.linea4);
        linea5= (LinearLayout) findViewById(R.id.linea5);
        linea6= (LinearLayout) findViewById(R.id.linea6);
        linea7= (LinearLayout) findViewById(R.id.linea7);


        // NAVEGACION
        Intent intent = getIntent();

        LinearLayout valoracionDiaMenu = (LinearLayout) findViewById(R.id.valoracion_dia_menu);
        LinearLayout estado_animico = (LinearLayout) findViewById(R.id.estado_animico);
        LinearLayout instrucciones = (LinearLayout) findViewById(R.id.instrucciones);
        LinearLayout realizarValoracion = (LinearLayout) findViewById(R.id.realizar_valoracion);

        valoracionDiaMenu.setVisibility(View.GONE);
        estado_animico.setVisibility(View.GONE);
        instrucciones.setVisibility(View.GONE);
        realizarValoracion.setVisibility(View.GONE);

        navegacion = NavValoracionDia.valueOf(intent.getStringExtra (ConstantesValoracionDia.NAVEGACION));
        if (navegacion == NavValoracionDia.MENU) {
            valoracionDiaMenu.setVisibility(View.VISIBLE);
            prepararListaValoraicones();
        } else if (navegacion == NavValoracionDia.ESTADO_ANIMICO) {
            estado_animico.setVisibility(View.VISIBLE);
        } else if (navegacion == NavValoracionDia.INSTRUCCIONES) {
            instrucciones.setVisibility(View.VISIBLE);
        } else if (navegacion == NavValoracionDia.ALTA) {
            realizarValoracion.setVisibility(View.VISIBLE);
        }
    }


    /*    public void irValoracionMenu(View view) {
        Intent intent = new Intent(this, ValoracionDiaActivity.class);
        intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.MENU.name());
        startActivity(intent);
    }*/

    public void irValoracionEstadoAnimico(View view) {
        Intent intent = new Intent(this, ValoracionDiaActivity.class);
        intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.ESTADO_ANIMICO.name());
        startActivity(intent);
    }

    public void irValoracionInstrucciones(View view) {
        SeekBar bienestar = (SeekBar) findViewById(R.id.bienestar);
        SeekBar malestar = (SeekBar) findViewById(R.id.malestar);

        int bienestar_value = bienestar.getProgress();
        int malestar_value = malestar.getProgress();

        happService.wellness(id, bienestar_value, malestar_value);

        Intent intent = new Intent(this, ValoracionDiaActivity.class);
        intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.INSTRUCCIONES.name());
        finish();
        startActivity(intent);
    }

    public void irValoracionDiaAlta(View view) {
        Intent intent = new Intent(this, ValoracionDiaActivity.class);
        intent.putExtra(ConstantesValoracionDia.NAVEGACION, NavValoracionDia.ALTA.name());
        finish();
        startActivity(intent);
    }

    public void darAlta(View view) {
        EditText valoracion_dia_texto1 = (EditText) findViewById(R.id.valoracion_dia_texto1);
        EditText valoracion_dia_texto2 = (EditText) findViewById(R.id.valoracion_dia_texto2);
        EditText valoracion_dia_texto3 = (EditText) findViewById(R.id.valoracion_dia_texto3);
        EditText valoracion_dia_texto4 = (EditText) findViewById(R.id.valoracion_dia_texto4);
        EditText valoracion_dia_texto5 = (EditText) findViewById(R.id.valoracion_dia_texto5);

        List listaValues = new ArrayList();
        listaValues.add(valoracion_dia_texto1.getText().toString());
        listaValues.add(valoracion_dia_texto2.getText().toString());
        listaValues.add(valoracion_dia_texto3.getText().toString());
        listaValues.add(valoracion_dia_texto4.getText().toString());
        listaValues.add(valoracion_dia_texto5.getText().toString());

        happService.valuationAdd(id, listaValues);

        finish();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actos_bondad_setting, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            irConfiguracion();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void irConfiguracion() {
        Intent intent = new Intent(this, DeviceActivity.class);
        intent.putExtra(ParamIntent.VALIDAR_EXISTEN_DATOS.name(), false);
        startActivity(intent);
    }
    public void salirApp(View view) {
    Intent intent = new Intent(this, MenuActivity.class);  // con este código vamos a la Activity principal
    finish();
    }




    public void mostrarAcercaDe(MenuItem item) {
        //AcercaDe.mostrarAcercaDe(this);
    }


    private void prepararListaValoraicones() {
        boolean[] visibles = new boolean[7];
        day0Salir.setVisibility(View.GONE);

        // DIA 0
        DateUtil dateUtil = new DateUtil();
        Timestamp now = dateUtil.now();

        TextView textDiaUno;
        textDiaUno = (TextView) findViewById(R.id.diaUno);
        String text="HOY: ";
        String date0 = DateFormat.format("dd-MM-yyyy", now).toString();
        textDiaUno.setText(text+date0);


        valorationsLastWeekModel = new ValorationsLastWeekModel(dateUtil.now());
        ResponseModel response = happService.getListValorationsLastWeek(id, dateUtil.getDay(now), dateUtil.getMonth(now) , dateUtil.getYear(now));
        valorationsLastWeekModel.put(ValorationsLastWeekModel.DAY0, response.getValorations());
        if (response.getValorations() != null && response.getValorations().size() > 0) {
            day0Nodatafound.setVisibility(View.GONE);
            day0Datafound.setVisibility(View.VISIBLE);

            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day0Texto0), 0);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day0Texto1), 1);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day0Texto2), 2);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day0Texto3), 3);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day0Texto4), 4);
            //botón salir si ya has realizado la valoración de hoy

            day0Salir.setVisibility(View.VISIBLE);



            visibles[0] = true;
        } else {
            day0Nodatafound.setVisibility(View.VISIBLE);
            day0Datafound.setVisibility(View.GONE);

            visibles[0] = false;
        }

        // DIA 1
        now = dateUtil.dayAddDay(now, -1);


        TextView textDiaDos;
        textDiaDos = (TextView) findViewById(R.id.diaDos);
        String date2 = DateFormat.format("dd-MM-yyyy", now).toString();
        textDiaDos.setText(date2);

        response = happService.getListValorationsLastWeek(id, dateUtil.getDay(now), dateUtil.getMonth(now) , dateUtil.getYear(now));
        valorationsLastWeekModel.put(ValorationsLastWeekModel.DAY1, response.getValorations());
        if (response.getValorations() != null && response.getValorations().size() > 0) {
            day1Nodatafound.setVisibility(View.GONE);
            day1Datafound.setVisibility(View.VISIBLE);

            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day1Texto0), 0);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day1Texto1), 1);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day1Texto2), 2);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day1Texto3), 3);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day1Texto4), 4);
            visibles[1] = true;
        } else {

            day1Nodatafound.setVisibility(View.VISIBLE);
            day1Datafound.setVisibility(View.GONE);
            visibles[1] = false;
        }
        // DIA 2
        now = dateUtil.dayAddDay(now, -1);
        TextView textDiaTres;
        textDiaTres = (TextView) findViewById(R.id.diaTres);
        String date3 = DateFormat.format("dd-MM-yyyy", now).toString();
        textDiaTres.setText(date3);


        response = happService.getListValorationsLastWeek(id, dateUtil.getDay(now), dateUtil.getMonth(now) , dateUtil.getYear(now));
        valorationsLastWeekModel.put(ValorationsLastWeekModel.DAY2, response.getValorations());
        if (response.getValorations() != null && response.getValorations().size() > 0) {
            day2Nodatafound.setVisibility(View.GONE);
            day2Datafound.setVisibility(View.VISIBLE);

            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day2Texto0), 0);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day2Texto1), 1);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day2Texto2), 2);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day2Texto3), 3);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day2Texto4), 4);
            visibles[2] = true;

        } else {
            day2Nodatafound.setVisibility(View.VISIBLE);
            day2Datafound.setVisibility(View.GONE);
            visibles[2] = false;
        }

        // DIA 3
        now = dateUtil.dayAddDay(now, -1);

        TextView textDiaCuatro;
        textDiaCuatro = (TextView) findViewById(R.id.diaCuatro);
        String date4 = DateFormat.format("dd-MM-yyyy", now).toString();
        textDiaCuatro.setText(date4);
        response = happService.getListValorationsLastWeek(id, dateUtil.getDay(now), dateUtil.getMonth(now) , dateUtil.getYear(now));
        valorationsLastWeekModel.put(ValorationsLastWeekModel.DAY3, response.getValorations());
        if (response.getValorations() != null && response.getValorations().size() > 0) {
            day3Nodatafound.setVisibility(View.GONE);
            day3Datafound.setVisibility(View.VISIBLE);

            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day3Texto0), 0);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day3Texto1), 1);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day3Texto2), 2);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day3Texto3), 3);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day3Texto4), 4);
            visibles[3] = true;

        } else {
            day3Nodatafound.setVisibility(View.VISIBLE);
            day3Datafound.setVisibility(View.GONE);
            visibles[3] = false;
        }


        // DIA 4
        now = dateUtil.dayAddDay(now, -1);
        TextView textDiaCinco;
        textDiaCinco = (TextView) findViewById(R.id.diaCinco);
        String date5 = DateFormat.format("dd-MM-yyyy", now).toString();
        textDiaCinco.setText(date5);
        response = happService.getListValorationsLastWeek(id, dateUtil.getDay(now), dateUtil.getMonth(now) , dateUtil.getYear(now));
        valorationsLastWeekModel.put(ValorationsLastWeekModel.DAY4, response.getValorations());
        if (response.getValorations() != null && response.getValorations().size() > 0) {
            day4Nodatafound.setVisibility(View.GONE);
            day4Datafound.setVisibility(View.VISIBLE);

            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day4Texto0), 0);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day4Texto1), 1);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day4Texto2), 2);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day4Texto3), 3);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day4Texto4), 4);
            visibles[4] = true;
        } else {
            day4Nodatafound.setVisibility(View.VISIBLE);
            day4Datafound.setVisibility(View.GONE);
            visibles[4] = false;
        }

        // DIA 5
        now = dateUtil.dayAddDay(now, -1);

        TextView textDiaSeis;
        textDiaSeis = (TextView) findViewById(R.id.diaSeis);
        String date6 = DateFormat.format("dd-MM-yyyy", now).toString();
        textDiaSeis.setText(date6);
        response = happService.getListValorationsLastWeek(id, dateUtil.getDay(now), dateUtil.getMonth(now) , dateUtil.getYear(now));
        valorationsLastWeekModel.put(ValorationsLastWeekModel.DAY5, response.getValorations());
        if (response.getValorations() != null && response.getValorations().size() > 0) {
            day5Nodatafound.setVisibility(View.GONE);
            day5Datafound.setVisibility(View.VISIBLE);

            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day5Texto0), 0);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day5Texto1), 1);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day5Texto2), 2);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day5Texto3), 3);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day5Texto4), 4);
            visibles[5] = true;
        } else {
            day5Nodatafound.setVisibility(View.VISIBLE);
            day5Datafound.setVisibility(View.GONE);
            visibles[5] = false;
        }

        // DIA 6
        now = dateUtil.dayAddDay(now, -1);
        TextView textDiaSiete;
        textDiaSiete = (TextView) findViewById(R.id.diaSiete);
        String date7 = DateFormat.format("dd-MM-yyyy", now).toString();
        textDiaSiete.setText(date7);
        response = happService.getListValorationsLastWeek(id, dateUtil.getDay(now), dateUtil.getMonth(now) , dateUtil.getYear(now));
        valorationsLastWeekModel.put(ValorationsLastWeekModel.DAY6, response.getValorations());
        if (response.getValorations() != null && response.getValorations().size() > 0) {
            day6Nodatafound.setVisibility(View.GONE);
            day6Datafound.setVisibility(View.VISIBLE);

            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day6Texto0), 0);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day6Texto1), 1);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day6Texto2), 2);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day6Texto3), 3);
            prepararValoracionPuntual(response, (TextView) findViewById(R.id.day6Texto4), 4);
            visibles[6] = true;
        } else {
            day6Nodatafound.setVisibility(View.VISIBLE);
            day6Datafound.setVisibility(View.GONE);
            visibles[6] = false;
        }


        // DESACTIVACIÓN NO VISIBLES


        boolean conDato = false;


        for (int vv = visibles.length - 1; vv >= 0; vv--) {
            if (!conDato && !visibles[vv]) {
                // OCULTAS
                if (vv==1) {
                    day1Nodatafound.setVisibility(View.GONE);
                    imageVal2.setVisibility(View.GONE);
                    linea2.setVisibility(View.GONE);
                }else if (vv==2) {
                    day2Nodatafound.setVisibility(View.GONE);
                    imageVal3.setVisibility(View.GONE);
                    linea3.setVisibility(View.GONE);
                }else if (vv==3) {
                    day3Nodatafound.setVisibility(View.GONE);
                    imageVal4.setVisibility(View.GONE);
                    linea4.setVisibility(View.GONE);
                }else if (vv==4) {
                    day4Nodatafound.setVisibility(View.GONE);
                    imageVal5.setVisibility(View.GONE);
                    linea5.setVisibility(View.GONE);
                }else if (vv==5) {
                    day5Nodatafound.setVisibility(View.GONE);
                    imageVal6.setVisibility(View.GONE);
                    linea6.setVisibility(View.GONE);
                }else if (vv==6) {
                    day6Nodatafound.setVisibility(View.GONE);
                    imageVal7.setVisibility(View.GONE);
                    linea7.setVisibility(View.GONE);
                }




            } else {
                conDato = true;
            }
        }

    }

    private void prepararValoracionPuntual(ResponseModel response, TextView textView, int n) {
        if (response.getValorations().size() > n) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(response.getValorations().get(n).getTextValoration());
        } else {
            textView.setVisibility(View.GONE);
        }
    }



}
