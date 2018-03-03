package happ.es.happ;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import happ.es.types.NavValoracionDia;
import happ.es.util.ConstantesValoracionDia;

public class ValoracionDiaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private NavValoracionDia navegacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion_dia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        // Guardar datos
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

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void irConfiguracion() {
        Intent intent = new Intent(this, DeviceActivity.class);
        startActivity(intent);
    }

}
