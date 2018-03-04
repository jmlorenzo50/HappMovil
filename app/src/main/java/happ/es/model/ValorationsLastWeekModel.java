package happ.es.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jorge on 4/03/18.
 */

public class ValorationsLastWeekModel {

    public final static String DAY0 = "DAY0";

    public final static String DAY1 = "DAY1";

    public final static String DAY2 = "DAY2";

    public final static String DAY3 = "DAY3";

    public final static String DAY4 = "DAY4";

    public final static String DAY5 = "DAY5";

    public final static String DAY6 = "DAY6";

    private Timestamp firstDay;

    private HashMap<String, List<ValorationModel>> valorations;

    public ValorationsLastWeekModel(Timestamp firstDay) {
        this.firstDay = firstDay;
        valorations = new HashMap();
    }

    public void put(String day, List<ValorationModel> valorationsDay) {
        valorations.put(day, valorationsDay);
    }

    public List<ValorationModel> get(String day) {
        List<ValorationModel> salida = null;
        if (valorations.containsKey(day)) {
            salida = valorations.get(day);
        }
        return salida;
    }

    public Timestamp getFirstDay() {
        return firstDay;
    }
}
