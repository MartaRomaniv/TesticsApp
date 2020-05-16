package ua.com.krch.chaos.duckonmmon.zefirka.testics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.R;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.animation.ZoomOutPageTransformer;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.constant.Constants;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.Question;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.Test;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.fragment.ItemViewPagerFragment;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.service.QuestionProviderService;

public class TestActivity extends AppCompatActivity implements ItemViewPagerFragment.OnFragmentRadioButtonClickListener {

    @BindView(R.id.pager)
    ViewPager viewPager;

    private Test test;

    private ArrayList<String> answersList;
    private ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        test = (Test) getIntent().getSerializableExtra(Constants.TEST);

        initializeArrayList();

        initQuestions();

        setUpViewPager();
    }

    private void initQuestions() {
        for (int i = 0; i < test.getMaxMark(); i++){
            questions.add(QuestionProviderService.getQuestion(test.getQuestions().get(i), test.getRandomAnswers(), test.getRandomAnswersArray()));
        }
    }

    @Override
    public void OnClick(String answer, Integer position) {
        answersList.set(position, answer);
    }

    @Override
    public void OnFinishClick() {
        Intent intent = new Intent(this, FinalActivity.class);
        intent.putExtra(Constants.TEST,test);
        intent.putExtra(Constants.RESULT,findResult());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private void initializeArrayList(){
        answersList = new ArrayList<>();
        for (int i = 0; i < test.getMaxMark(); i++) {
            answersList.add("");
        }
    }

    private void setUpViewPager() {
        ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(screenSlidePagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private int findResult(){
        int result = 0;
        for (int i = 0; i < answersList.size(); i++){
            if (answersList.get(i).equals(getString(test.getQuestions().get(i).getCorrectAnswer()))){
                result++;
            }
        }

        return result;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ItemViewPagerFragment.newInstance(test, position,answersList.get(position),questions.get(position));
        }

        @Override
        public int getCount() {
            return test.getQuestions().size();
        }
    }
}
