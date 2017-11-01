package happ.es.components.questionary;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import java.lang.reflect.Field;
/**
 * Created by jorge on 29/10/17.
 */

public class HappViewPager extends ViewPager {

    private boolean enabled;

    private int numberQuestions;

    private int pregunta;

    public int getNumberQuestions() {
        return numberQuestions;
    }

    public void setNumberQuestions(int numberQuestions) {
        this.numberQuestions = numberQuestions;
    }

    public HappViewPager(Context context) {
        super(context);
        this.enabled = true;
        pregunta = 0;
    }

    public HappViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
        pregunta = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean nextPage() {
        if (pregunta < numberQuestions) {
            pregunta++;
            this.setCurrentItem(pregunta);
        }
        return (pregunta >= numberQuestions);
    }
}
