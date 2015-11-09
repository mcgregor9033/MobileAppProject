package edu.uco.termproject.mobiletest2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class ResultsActivity extends Activity {

    TextView letter, pass, miss, score;
    int passCount = 0, missCount = 0;
    double scoreCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        letter = (TextView) findViewById(R.id.letter);
        pass = (TextView) findViewById(R.id.num_right);
        miss = (TextView) findViewById(R.id.num_wrong);
        score = (TextView) findViewById(R.id.num_score);

        ArrayList results = new ArrayList();

        Intent intent = getIntent();
        results = intent.getStringArrayListExtra("info");

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).equals("correct"))
                passCount++;
            else
                missCount++;
        }

        scoreCount = (double)passCount / 20D;
        BigDecimal bd = new BigDecimal(scoreCount);
        bd = bd.round(new MathContext(2));
        double answer = bd.doubleValue() * 1000;

        if (answer > 700)
            letter.setText("PASS");
        else
            letter.setText("FAIL");
        pass.setText(Integer.toString(passCount));
        miss.setText(Integer.toString(missCount));
        score.setText(Integer.toString((int)answer) + "/1000");
    }

}
