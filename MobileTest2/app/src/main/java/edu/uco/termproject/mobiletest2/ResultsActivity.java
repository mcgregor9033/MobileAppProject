package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class ResultsActivity extends Activity {

    TextView letter, pass, miss, score;
    Button okay;
    int passCount = 0, missCount = 0;
    double scoreCount = 0;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_results);

        letter = (TextView) findViewById(R.id.letter);
        pass = (TextView) findViewById(R.id.num_right);
        miss = (TextView) findViewById(R.id.num_wrong);
        score = (TextView) findViewById(R.id.num_score);
        okay = (Button) findViewById(R.id.okay);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);
            }
        });

        ArrayList results = new ArrayList();
        String type;

        Intent intent = getIntent();
        results = intent.getStringArrayListExtra("info");
        type = intent.getStringExtra("quiz");

        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).equals("correct"))
                passCount++;
            else
                missCount++;
        }

        scoreCount = (double)passCount / 5D;
        BigDecimal bd = new BigDecimal(scoreCount);
        bd = bd.round(new MathContext(2));
        double answer = bd.doubleValue() * 100;

        if (answer > 60)
            letter.setText("PASS");
        else
            letter.setText("FAIL");
        pass.setText(Integer.toString(passCount));
        miss.setText(Integer.toString(missCount));
        score.setText(Integer.toString((int)answer) + "/100");

        try {
            // try to read the file first
            String s="";
            try {
                FileInputStream fileIn=openFileInput("StatsFile.txt");
                InputStreamReader InputRead= new InputStreamReader(fileIn);

                char[] inputBuffer= new char[512];
                int charRead;

                while ((charRead=InputRead.read(inputBuffer))>0) {
                    // char to string conversion
                    String readstring=String.copyValueOf(inputBuffer,0,charRead);
                    s +=readstring;
                }
                InputRead.close();

            } catch (Exception e) {
                e.printStackTrace();
            }




            //write to file
            FileOutputStream fileout = openFileOutput("StatsFile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(s + type + "-" + Integer.toString((int)answer) + "#");
            outputWriter.close();

            //display file saved message
            //Toast.makeText(getBaseContext(), "File saved successfully!",
            //        Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.settings:
                return true;
            case R.id.guess_count:
                Intent intent = new Intent(context, NotebookActivity.class);
                startActivity(intent);
                return true;
            case R.id.themes:
                return true;
            case R.id.origin:
                ThemeUtils.changeToTheme(this, ThemeUtils.ORIGIN);
                return true;
            case R.id.blue:
                ThemeUtils.changeToTheme(this, ThemeUtils.BLUE);
                return true;
            case R.id.yellow:
                ThemeUtils.changeToTheme(this, ThemeUtils.YELLOW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
