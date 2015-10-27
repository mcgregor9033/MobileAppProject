package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LevelOneQuiz extends Activity {

    TextView question;
    ImageButton box00, box01, box10, box11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one_quiz);

        question = (TextView) findViewById(R.id.word);
        box00 = (ImageButton) findViewById(R.id.button00);
        box01 = (ImageButton) findViewById(R.id.button01);
        box10 = (ImageButton) findViewById(R.id.button10);
        box11 = (ImageButton) findViewById(R.id.button11);

        Resources res = getResources();
        TypedArray pictures = res.obtainTypedArray(R.array.pictures);
        Drawable drawable = pictures.getDrawable(0);

        TypedArray names = res.obtainTypedArray(R.array.names);
        String name = names.getString(0);

        question.setText(name);
        box00.setImageDrawable(drawable);
        box01.setImageDrawable(drawable);
        box10.setImageDrawable(drawable);
        box11.setImageDrawable(drawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_one_quiz, menu);
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
                Toast.makeText(LevelOneQuiz.this, R.string.guess_count, Toast.LENGTH_LONG).show();
                return true;
            case R.id.themes:
                Toast.makeText(LevelOneQuiz.this, R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
