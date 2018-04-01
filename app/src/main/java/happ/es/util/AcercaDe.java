package happ.es.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by jorge on 1/04/18.
 */

public class AcercaDe {


    public static void mostrarAcercaDe(final Activity activity) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity.getApplication().getApplicationContext());

        // set title
        alertDialogBuilder.setTitle("Acerca de ...");

        // set dialog message
        alertDialogBuilder
                .setMessage("Aplicación desarrollada por Marta Gil López y Jorge Morales Lorenzo")
                .setCancelable(false)
                .setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        activity.finish();
                    }
                });
                /*.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
                */

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }


}
