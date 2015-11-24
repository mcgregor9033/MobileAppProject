package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class LevelKatakana extends Activity {

    private static final String KEY_INDEX = "index";

    private ImageButton audio;
    private ImageButton next;
    private Button help;
    private Button check;
    private Button button;
    private Button addNote;
    private TextView img;
    private EditText enterText;
    private TextView hint;
    private MediaPlayer mp;
    private int falseCT = 0;
    final String FILENAME = "Kata";

    final Context context = this;

    private Diction[] myDictionSet = new Diction[]{
            new Diction("a1", "ア", "a"), new Diction("i1", "イ", "i"), new Diction("u1", "ウ", "u"), new Diction("e1", "エ", "e"), new Diction("o1", "オ", "o"),
            new Diction("ka1", "カ", "ka"), new Diction("ki1", "キ", "ki"), new Diction("ku1", "ク", "ku"), new Diction("ke1", "ケ", "ke"), new Diction("ko1", "コ", "ko"),
            new Diction("sa1", "サ", "sa"), new Diction("shi1", "シ", "shi"), new Diction("su1", "ス", "u"), new Diction("se1", "セ", "se"), new Diction("so1", "ソ", "so"),
            new Diction("ta1", "タ", "ta"), new Diction("chi1", "チ", "chi"), new Diction("tsu1", "ツ", "tsu"), new Diction("te1", "テ", "te"), new Diction("to1", "ト", "to"),
            new Diction("na1", "ナ", "na"), new Diction("ni1", "ニ", "ni"), new Diction("nu1", "ヌ", "nu"), new Diction("ne1", "ネ", "ne"), new Diction("no1", "ノ", "no"),
            new Diction("ha1", "ハ", "ha"), new Diction("hi1", "ヒ", "hi"), new Diction("fu1", "フ", "fu"), new Diction("he1", "ヘ", "he"), new Diction("ho1", "ホ", "ho"),
            new Diction("ma1", "マ", "ma"), new Diction("mi1", "ミ", "mi"), new Diction("mu1", "ム", "mu"), new Diction("me1", "メ", "me"), new Diction("mo1", "モ", "mo"),
            new Diction("ya1", "ヤ", "ya"), new Diction("yu1", "ユ", "yu"), new Diction("yo1", "ヨ", "yo"),
            new Diction("ra1", "ラ", "ra"), new Diction("ri1", "リ", "ri"), new Diction("ru1", "ル", "ru"), new Diction("re1", "レ", "re"), new Diction("ro1", "ロ", "ro"),
            new Diction("wa1", "ワ", "wa"), new Diction("wo1", "ヲ", "o"), new Diction("n1", "ン", "n")
    };

    private int myCurrentIndex = 0;

    private void updateCharacter() {
        String character = myDictionSet[myCurrentIndex].getMyImgName();

        /*String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);*/
        img.setText(myDictionSet[myCurrentIndex].getMyCharacter());
        img.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.img_font));

        String uri2 = "@raw/" + character;
        int mpResource = getResources().getIdentifier(uri2, null, getPackageName());
        mp = MediaPlayer.create(this, mpResource);
    }

    private void checkAnswer(LevelKatakana levelKatakana, String userEnterAnswer) {
        String answer = myDictionSet[myCurrentIndex].getMyAnswer();
        int messageResId = 0;

        if (answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelKatakana, LevelKatakanaMultipleGuess.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
            startActivity(intent);
        } else {
            falseCT++;
            if (falseCT >= 3) addNote.setVisibility(View.VISIBLE);
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    public void writeData(String data) {
        try {
            FileOutputStream fOut = openFileOutput(FILENAME, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(data);
            osw.write("\n");
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        img = (TextView) findViewById(R.id.imageView2);
        enterText = (EditText) findViewById(R.id.editText2);
        hint = (TextView) findViewById(R.id.pic_hint2);
        button = (Button) findViewById(R.id.buttonAlert);
        addNote = (Button) findViewById(R.id.btnAddNote2);


        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        updateCharacter();
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                hint.setText(myDictionSet[myCurrentIndex].getMyAnswer());
                hint.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                myCurrentIndex = rand.nextInt(myDictionSet.length);
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

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                falseCT = 0;
                addNote.setVisibility(View.INVISIBLE);

                String character = myDictionSet[myCurrentIndex].getMyCharacter();
                String answer = myDictionSet[myCurrentIndex].getMyAnswer();
                String tmp = character + "  " + answer + "\n";

                writeData(tmp);

                Toast.makeText(context, myDictionSet[myCurrentIndex].getMyCharacter() +
                        " was added to Notebook", Toast.LENGTH_SHORT).show();
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
