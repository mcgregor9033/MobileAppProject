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

public class LevelHiragana extends Activity {
    private static final String KEY_INDEX = "index";

    private ImageButton audio;
    private ImageButton next;
    private Button help;
    private Button check;
    private Button addNote;
    private TextView img;
    private EditText enterText;
    private TextView hint;
    private MediaPlayer mp;
    private Button button;
    final String FILENAME = "Hira";
    private int falseCT = 0;

    final Context context = this;

    private Diction[] myDictionSet = new Diction[]{
            new Diction("a", "あ", "a"), new Diction("i", "い", "i"), new Diction("u", "う", "u"), new Diction("e", "え", "e"), new Diction("o", "お", "o"),
            new Diction("ka", "か", "ka"), new Diction("ki", "き", "ki"), new Diction("ku", "く", "ku"), new Diction("ke", "け", "ke"), new Diction("ko", "こ", "ko"),
            new Diction("sa", "さ", "sa"), new Diction("shi", "し", "shi"), new Diction("su", "す", "u"), new Diction("se", "せ", "se"), new Diction("so", "そ", "so"),
            new Diction("ta", "た", "ta"), new Diction("chi", "ち", "chi"), new Diction("tsu", "つ", "tsu"), new Diction("te", "て", "te"), new Diction("to", "と", "to"),
            new Diction("na", "な", "na"), new Diction("ni", "に", "ni"), new Diction("nu", "ぬ", "nu"), new Diction("ne", "ね", "ne"), new Diction("no", "の", "no"),
            new Diction("ha", "は", "ha"), new Diction("hi", "ひ", "hi"), new Diction("fu", "ふ", "fu"), new Diction("he", "へ", "he"), new Diction("ho", "ほ", "ho"),
            new Diction("ma", "ま", "ma"), new Diction("mi", "み", "mi"), new Diction("mu", "む", "mu"), new Diction("me", "め", "me"), new Diction("mo", "も", "mo"),
            new Diction("ya", "や", "ya"), new Diction("yu", "ゆ", "yu"), new Diction("yo", "よ ", "yo"),
            new Diction("ra", "ら", "ra"), new Diction("ri", "り", "ri"), new Diction("ru", "る", "ru"), new Diction("re", "れ", "re"), new Diction("ro", "ろ", "ro"),
            new Diction("wa", "わ", "wa"), new Diction("wo", "を", "o"), new Diction("n", "ん", "n")
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

    private void checkAnswer(LevelHiragana levelHiragana, String userEnterAnswer) {
        String answer = myDictionSet[myCurrentIndex].getMyAnswer();

        int messageResId = 0;

        if (answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelHiragana, LevelHiraganaMultipleGuess.class);
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
        setContentView(R.layout.activity_level_hiragana);

        audio = (ImageButton) findViewById(R.id.btnAudio);
        next = (ImageButton) findViewById(R.id.btnNext);
        check = (Button) findViewById(R.id.btnCheck);
        button = (Button) findViewById(R.id.buttonAlert);
        help = (Button) findViewById(R.id.btnQuiz);
        addNote = (Button) findViewById(R.id.btnAddNote);
        img = (TextView) findViewById(R.id.imageView);
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
                        .setMessage("Using the keyboard, select the proper english letters for the Hiragana Character displayed.")
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
                hint.setText(myDictionSet[myCurrentIndex].getMyImgName());
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
                checkAnswer(LevelHiragana.this, enterText.getText().toString());
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

                Toast.makeText(context, character + " was added to Notebook", Toast.LENGTH_SHORT).show();
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
