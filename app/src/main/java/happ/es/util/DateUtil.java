package happ.es.util;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


/**
 * The Class DateUtil.
 */
public class DateUtil {

    /**
     * Now.
     *
     * @return the timestamp
     */
    public Timestamp now() {
        return new Timestamp(new Date().getTime());
    }


    /**
     * Now add month.
     *
     * @param number the number
     * @return the timestamp
     */
    public Timestamp nowAddMonth(int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.MONTH, number);
        return new Timestamp(cal.getTime().getTime());
    }


    /**
     * Now add month.
     *
     * @param number the number
     * @return the timestamp
     */
    public Timestamp nowAddDay(int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now());
        cal.add(Calendar.DAY_OF_YEAR, number);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Get the last minut of the day
     * @param day
     * @param month
     * @param year
     * @return
     */
    public Timestamp getInstance(int day, int month, int year) {
        return Timestamp.valueOf(year + "-" + month + "-" + day + " 23:59:59.9");
    }


    /**
     * Add number days to day
     * @param valuation_date
     * @param number
     * @return
     */
    public Timestamp dayAddDay(Timestamp valuation_date, int number) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(valuation_date);
        cal.add(Calendar.DAY_OF_YEAR, number);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Obtiene el día
     * @param timestamp
     * @return
     */
    public int getDay(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Obtiene el mes
     * @param timestamp
     * @return
     */
    public int getMonth(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * Obtiene el año
     * @param timestamp
     * @return
     */
    public int getYear(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.YEAR);
    }


}
