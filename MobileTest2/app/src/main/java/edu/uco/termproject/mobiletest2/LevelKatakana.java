package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class LevelKatakana extends Activity {

    private static final String KEY_INDEX = "index";

    private ImageButton audio;
    private ImageButton next;
    private Button help;
    private Button check;
    private ImageView img;
    private EditText enterText;
    private TextView hint;
    private MediaPlayer mp;

    final Context context = this;
    private Button button;

    private Katakana [] myKatakanaSet = new Katakana[] {
            new Katakana("a1","a"), new Katakana("i1","i"), new Katakana("u1","u"), new Katakana("e1","e"), new Katakana("o1","o"),
            new Katakana("ka1","ka"), new Katakana("ki1","ki"), new Katakana("ku1","ku"), new Katakana("ke1","ke"), new Katakana("ko1","ko"),
            new Katakana("sa1","sa"), new Katakana("shi1","shi"), new Katakana("su1","u"), new Katakana("se1","se"), new Katakana("so1","so"),
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

        String uri2 = "@raw/" + character;
        int mpResource = getResources().getIdentifier(uri2, null, getPackageName());
        mp = MediaPlayer.create(this, mpResource);
    }

    private void checkAnswer (LevelKatakana levelKatakana, String userEnterAnswer){
        String answer = myKatakanaSet[myCurrentIndex].getMyAnswer();
        int messageResId = 0;

        if(answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelKatakana, LevelKatakanaMultipleGuess.class);
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
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_level_katakana);

        audio = (ImageButton) findViewById(R.id.btnAudio2);
        next = (ImageButton) findViewById(R.id.btnNext2);
        check = (Button) findViewById(R.id.btnCheck2);
        help = (Button) findViewById(R.id.btnQuiz2);
        img = (ImageView) findViewById(R.id.imageView2);
        enterText = (EditText) findViewById(R.id.editText2);
        hint = (TextView) findViewById(R.id.pic_hint2);
        button = (Button) findViewById(R.id.buttonAlert);


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

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Confused? Here's what to do!")
                        .setIcon(R.drawable.question)
                        .setCancelable(false)
                        .setMessage("Using the keyboard, select the proper english letters for the Katakana Character displayed.")
                        .setNegativeButton("Let's Go!!!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint.setText(myKatakanaSet[myCurrentIndex].getMyAnswer());
                hint.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                myCurrentIndex = rand.nextInt(myKatakanaSet.length);
                hint.setVisibility(View.INVISIBLE);
                updateCharacter();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelKatakana.this, enterText.getText().toString());
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
