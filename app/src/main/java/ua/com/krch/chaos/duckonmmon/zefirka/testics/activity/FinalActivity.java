package ua.com.krch.chaos.duckonmmon.zefirka.testics.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.R;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.application.App;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.constant.Constants;
import ua.com.krch.chaos.duckonmmon.zefirka.testics.entity.Test;

public class FinalActivity extends AppCompatActivity {


    @BindView(R.id.result)
    TextView textView;
    @BindView(R.id.finalImage)
    ImageView imageView;

    private Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        ButterKnife.bind(this);

        test = (Test) getIntent().getSerializableExtra(Constants.TEST);
        int result = getIntent().getIntExtra(Constants.RESULT,0);

        textView.setText("" + result);
        saveData(result);
        setImage(result);
    }

    private void saveData(int result) {
        int maxResult=App.getINSTANCE().getPreferences().getInt(test.getKey(), 0);
        if(maxResult<result)
        { SharedPreferences.Editor editor = App.getINSTANCE().getPreferences().edit();
        editor.putInt(test.getKey(), result);
        editor.apply();}
    }

    private void setImage(int result) {
        double resultImage = (double) result / (double) test.getMaxMark() * 100;
        if (resultImage < 21) {
            imageView.setImageResource(Constants.res020);
        } else if (resultImage < 41) {
            imageView.setImageResource(Constants.res2040);
        } else if (resultImage < 61) {
            imageView.setImageResource(Constants.res4060);
        } else if (resultImage < 81) {
            imageView.setImageResource(Constants.res6080);
        } else {
            imageView.setImageResource(R.drawable.genius);
        }
    }

}
