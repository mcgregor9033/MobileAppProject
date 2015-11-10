package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LevelHiragana extends Activity {
    private static final String KEY_INDEX = "index";

    private ImageButton audio;
    private ImageButton next;
    private Button help;
    private Button check;
    private ImageView img;
    private EditText enterText;
    private TextView hint;
    private MediaPlayer mp;

    private Hiragana [] myHiraganaSet = new Hiragana[] {
            new Hiragana("a"), new Hiragana("i"), new Hiragana("u"), new Hiragana("e"), new Hiragana("o"),
            new Hiragana("ka"), new Hiragana("ki"), new Hiragana("ku"), new Hiragana("ke"), new Hiragana("ko"),
            new Hiragana("sa"), new Hiragana("shi"), new Hiragana("su"), new Hiragana("se"), new Hiragana("so"),
            new Hiragana("ta"), new Hiragana("chi"), new Hiragana("tsu"), new Hiragana("te"), new Hiragana("to"),
            new Hiragana("na"), new Hiragana("ni"), new Hiragana("nu"), new Hiragana("ne"), new Hiragana("no"),
            new Hiragana("ha"), new Hiragana("hi"), new Hiragana("fu"), new Hiragana("he"), new Hiragana("ho"),
            new Hiragana("ma"), new Hiragana("mi"), new Hiragana("mu"), new Hiragana("me"), new Hiragana("mo"),
            new Hiragana("ya"), new Hiragana("yu"), new Hiragana("yo"),
            new Hiragana("ra"), new Hiragana("ri"), new Hiragana("ru"), new Hiragana("re"), new Hiragana("ro"),
            new Hiragana("wa"), new Hiragana("wo"), new Hiragana("n")
    };

    private int myCurrentIndex = 0;

    private void updateCharacter(){
        String character = myHiraganaSet[myCurrentIndex].getMyImgName();

        String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);

        String uri2 = "@raw/" + character;
        int mpResource = getResources().getIdentifier(uri2, null, getPackageName());
        mp = MediaPlayer.create(this, mpResource);
    }

    private void checkAnswer(LevelHiragana levelHiragana, String userEnterAnswer){
        String answer = myHiraganaSet[myCurrentIndex].getMyAnswer();

        int messageResId = 0;

        if(answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelHiragana, LevelHiraganaMultipleGuess.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
            startActivity (intent);
        }


        else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_hiragana);

        audio = (ImageButton) findViewById(R.id.btnAudio);
        next = (ImageButton) findViewById(R.id.btnNext);
        check = (Button) findViewById(R.id.btnCheck);
        help = (Button) findViewById(R.id.btnQuiz);
        img = (ImageView) findViewById(R.id.imageView);
        enterText = (EditText) findViewById(R.id.editText);
        hint = (TextView) findViewById(R.id.pic_hint);
        mp = MediaPlayer.create(this, R.raw.a);

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        updateCharacter();
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint.setText(myHiraganaSet[myCurrentIndex].getMyImgName());
                hint.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                myCurrentIndex = rand.nextInt(myHiraganaSet.length);
                hint.setVisibility(View.INVISIBLE);
                updateCharacter();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelHiragana.this, enterText.getText().toString());
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
        getMenuInflater().inflate(R.menu.menu_level_hiragana, menu);
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
                Toast.makeText(LevelHiragana.this, R.string.guess_count, Toast.LENGTH_LONG).show();
                return true;
            case R.id.themes:
                Toast.makeText(LevelHiragana.this, R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
