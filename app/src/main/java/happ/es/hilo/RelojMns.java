package happ.es.hilo;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.Toast;

import happ.es.happ.R;

/**
 * Created by marta on 2/10/18.
 */


public class RelojMns implements  Runnable {

    private Activity activity;

    public RelojMns(Activity activity) {
        this.activity = activity;
    }



    public void  run() {
       Toast toast = new Toast(activity);
        ImageView view = new ImageView(activity);
        view.setImageResource(R.drawable.reloj);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();


    }



}
