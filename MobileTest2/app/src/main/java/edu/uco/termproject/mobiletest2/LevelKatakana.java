package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class LevelKatakana extends Activity {

    private static final String KEY_INDEX = "index";

    private ImageButton audio;
    private ImageButton next;
    private Button check;
    private ImageView img;
    private EditText enterText;

    private Katakana [] myKatakanaSet = new Katakana[] {
            new Katakana("a1","a"), new Katakana("i1","i"), new Katakana("u1","u"), new Katakana("e1","e"), new Katakana("o1","o"),
            new Katakana("ka1","ka"), new Katakana("ki1","ki"), new Katakana("ku1","ku"), new Katakana("ke1","ke"), new Katakana("ko1","ko"),
            new Katakana("sa1","sa"), new Katakana("si1","si"), new Katakana("su1","u"), new Katakana("se1","se"), new Katakana("so1","so"),
            new Katakana("ta1","ta"), new Katakana("chi1","chi"), new Katakana("tsu1","tsu"), new Katakana("te1","te"), new Katakana("to1","to"),
            new Katakana("na1","na"), new Katakana("ni1","ni"), new Katakana("nu1","nu"), new Katakana("ne1","ne"), new Katakana("no1","no"),
            new Katakana("ha1","ha"), new Katakana("hi1","hi"), new Katakana("fu1","fu"), new Katakana("he1","he"), new Katakana("ho1","ho"),
            new Katakana("ma1","ma"), new Katakana("mi1","mi"), new Katakana("mu1","mu"), new Katakana("me1","me"), new Katakana("mo1","mo"),
            new Katakana("ya1","ya"), new Katakana("yu1","yu"), new Katakana("yo1","yo"),
            new Katakana("ra1","ra"), new Katakana("ri1","ri"), new Katakana("ru1","ru"), new Katakana("re1","re"), new Katakana("ro1","ro"),
            new Katakana("wa1","wa"), new Katakana("wo1","o"), new Katakana("n1","n")
    };

    private int myCurrentIndex = 0;

    private void updateCharacter(){
        String character = myKatakanaSet[myCurrentIndex].getMyImgName();
        String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);
    }

    private void checkAnswer (String userEnterAnswer){
        String answer = myKatakanaSet[myCurrentIndex].getMyAnswer();
        int messageResId = 0;

        if(answer.equals(userEnterAnswer))
            messageResId = R.string.correct_toast;

        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_katakana);

        audio = (ImageButton) findViewById(R.id.btnAudio2);
        next = (ImageButton) findViewById(R.id.btnNext2);
        check = (Button) findViewById(R.id.btnCheck2);
        img = (ImageView) findViewById(R.id.imageView2);
        enterText = (EditText) findViewById(R.id.editText2);

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LevelKatakana.this,"Audio Button Clicked!", Toast.LENGTH_LONG).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCurrentIndex = (myCurrentIndex + 1) % myKatakanaSet.length;
                updateCharacter();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(enterText.getText().toString());
            }
        });

        if (savedInstanceState != null) {
            myCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateCharacter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_katakana, menu);
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
                Toast.makeText(LevelKatakana.this, R.string.guess_count, Toast.LENGTH_LONG).show();
                return true;
            case R.id.themes:
                Toast.makeText(LevelKatakana.this, R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
