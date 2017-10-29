package happ.es.components.questionary;

/**
 * Created by jorge on 13/10/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import happ.es.happ.QuestionaryActivity;
import happ.es.model.QuestionaryModel;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private QuestionaryModel questionaryModel;

    private QuestionaryActivity questionaryActivity;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return questionaryModel.getDescription();
    }


    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, questionaryModel, questionaryActivity);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return questionaryModel.getQuestions().size();
    }



    public QuestionaryModel getQuestionaryModel() {
        return questionaryModel;
    }

    public void setQuestionaryModel(QuestionaryModel questionaryModel) {
        this.questionaryModel = questionaryModel;
    }

    public QuestionaryActivity getQuestionaryActivity() {
        return questionaryActivity;
    }

    public void setQuestionaryActivity(QuestionaryActivity questionaryActivity) {
        this.questionaryActivity = questionaryActivity;
    }
}
