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

public class LevelKanji extends Activity {

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

    private Kanji [] myKanjiSet = new Kanji[] {
            new Kanji("one","いち"), new Kanji("two","に"), new Kanji("three","さん"),
            new Kanji("four","し"), new Kanji("five","ご"),
            new Kanji("six","ろく"), new Kanji("seven","しち"), new Kanji("eight","はち"),
            new Kanji("nine","きゅう"), new Kanji("ten","じゅう"),
            new Kanji("yen","えん"), new Kanji("hundred","ひゃく"), new Kanji("thousand","せん"),
            new Kanji("tenthousand","まん"), new Kanji("what","なに"),
            new Kanji("sun","ひ"), new Kanji("moon","つき"), new Kanji("light","あか"),
            new Kanji("temple","てら"), new Kanji("time","じ"),
            new Kanji("fire","ひ"), new Kanji("water","みず"), new Kanji("tree","き"),
            new Kanji("money","かね"), new Kanji("soil","つち")/*,
            new Kanji("",""), new Kanji("",""), new Kanji("",""),
            new Kanji("",""), new Kanji("","")*/
    };

    private int myCurrentIndex = 0;

    private void updateCharacter(){
        String character = myKanjiSet[myCurrentIndex].getMyImgName();

        String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);

        String uri2 = "@raw/" + character;
        int mpResource = getResources().getIdentifier(uri2, null, getPackageName());
        mp = MediaPlayer.create(this, mpResource);
    }

    private void checkAnswer (LevelKanji levelKanji, String userEnterAnswer, int myCurrentIndex){
        String answer = myKanjiSet[myCurrentIndex].getMyAnswer();
        int messageResId = 0;

        if(answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelKanji, LevelKanji2.class);
            intent.putExtra("myCurrentIndex", myCurrentIndex);
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
        setContentView(R.layout.activity_level_kanji);

        audio = (ImageButton) findViewById(R.id.btnAudio3);
        next = (ImageButton) findViewById(R.id.btnNext3);
        check = (Button) findViewById(R.id.btnCheck3);
        help = (Button) findViewById(R.id.btnQuiz3);
        img = (ImageView) findViewById(R.id.imageView3);
        enterText = (EditText) findViewById(R.id.editText3);
        hint = (TextView) findViewById(R.id.pic_hint3);
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
                        .setMessage("Using the keyboard, select the proper english letters for the Kanji Character displayed.")
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
                hint.setText(myKanjiSet[myCurrentIndex].getMyAnswer());
                hint.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                myCurrentIndex = rand.nextInt(myKanjiSet.length);
                hint.setVisibility(View.INVISIBLE);
                updateCharacter();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelKanji.this, enterText.getText().toString(), myCurrentIndex);
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
        getMenuInflater().inflate(R.menu.menu_level_kanji, menu);
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
                Toast.makeText(LevelKanji.this, R.string.guess_count, Toast.LENGTH_LONG).show();
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
