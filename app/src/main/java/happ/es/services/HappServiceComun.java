package happ.es.services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import happ.es.types.Gender;
import happ.es.types.MaritalStatus;

/**
 * Created by jorge on 30/03/18.
 */

public class HappServiceComun {

    protected static final String URL_BASE = "http://192.168.1.41:8080";


    protected String search (String id) {
        StringBuffer salida = new StringBuffer();
        String peticion = URL_BASE + "/happ/device/search?id=" + id;
        return hacerPeticion(peticion);

    }

    protected String add (String id) {
        StringBuffer salida = new StringBuffer();
        String peticion = URL_BASE + "/happ/device/add?id=" + id;
        return hacerPeticion(peticion);
    }


    protected String update (String id, int age, Gender gender, MaritalStatus maritalStatus, String codeEducationLevel) {
        StringBuffer salida = new StringBuffer();
        String peticion = URL_BASE + "/happ/device/update?id=" + id
                + "&age=" + age
                + "&gender=" + gender.name()
                + "&maritalstatus=" + maritalStatus.name()
                + "&codeEducationLevel=" + codeEducationLevel;
        return hacerPeticion(peticion);
    }



    protected String hacerPeticion (String peticion) {
        StringBuffer salida = new StringBuffer();
        HttpURLConnection urlConnection = null;
        try {

            URL url = new URL(peticion);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = rd.readLine()) != null) {
                salida.append(line);
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return salida.toString();
    }

}
