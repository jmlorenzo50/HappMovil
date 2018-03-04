package happ.es.model;


import java.sql.Timestamp;

/**
 * Created by jorge on 4/03/18.
 */

public class ValorationModel {

    private int order;

    private String textValoration;

    private Timestamp dateValoration;

    public Timestamp getDateValoration() {
        return dateValoration;
    }

    public void setDateValoration(Timestamp dateValoration) {
        this.dateValoration = dateValoration;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTextValoration() {
        return textValoration;
    }

    public void setTextValoration(String textValoration) {
        this.textValoration = textValoration;
    }
}
