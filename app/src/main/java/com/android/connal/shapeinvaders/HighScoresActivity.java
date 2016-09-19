package com.android.connal.shapeinvaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoresActivity extends AppCompatActivity {

    final static int HIGH_SCORES_TO_SHOW = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        String names = "one, two, three, four, five, six, seven, eight, nine, ten";
        String scores = "11, 22, 33, 44, 55, 66, 77, 88, 99, 1010";
        String[] nameArray = names.split(", ");
        String[] scoreArray = scores.split(", ");
        int[] views = {R.id.hs1, R.id.hs2, R.id.hs3, R.id.hs4, R.id.hs5, R.id.hs6, R.id.hs7, R.id.hs8, R.id.hs9, R.id.hs10};
        for(int i = 0; i < HIGH_SCORES_TO_SHOW; i++){
            ((TextView) findViewById(views[i])).setText(String.valueOf(i+1)+". "+nameArray[i]+": "+scoreArray[i]);
        }
    }
}
