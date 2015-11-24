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

public class LevelKanji extends Activity {

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
    final String FILENAME = "Kanji";

    final Context context = this;

    public Diction[] NotebookSet = new Diction[]{};

    private Diction[] myKanjiSet = new Diction[]{
            new Diction("one", "一", "いち"), new Diction("two", "二", "に"), new Diction("three", "三", "さん"),
            new Diction("four", "四", "し"), new Diction("five", "五", "ご"), new Diction("six", "六", "ろく"),
            new Diction("seven", "七", "しち"), new Diction("eight", "八", "はち"), new Diction("nine", "九", "きゅう"),
            new Diction("ten", "十", "じゅう"), new Diction("yen", "円", "えん"), new Diction("hundred", "百", "ひゃく"),
            new Diction("thousand", "千", "せん"), new Diction("tenthousand", "万", "まん"), new Diction("what", "何", "なに"),
            new Diction("sun", "日", "ひ"), new Diction("moon", "月", "つき"), new Diction("light", "明", "あか"),
            new Diction("temple", "寺", "てら"), new Diction("time", "時", "じ"), new Diction("fire", "火", "ひ"),
            new Diction("water", "水", "みず"), new Diction("tree", "木", "き"), new Diction("money", "金", "かね"), new Diction("soil", "土", "つち")
    };

    private int myCurrentIndex = 0;

    private void updateCharacter() {
        String character = myKanjiSet[myCurrentIndex].getMyImgName();

        /*String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);*/
        img.setText(myKanjiSet[myCurrentIndex].getMyCharacter());
        img.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.img_font));

        String uri2 = "@raw/" + character;
        int mpResource = getResources().getIdentifier(uri2, null, getPackageName());
        mp = MediaPlayer.create(this, mpResource);
    }

    private void checkAnswer(LevelKanji levelKanji, String userEnterAnswer, int myCurrentIndex) {
        String answer = myKanjiSet[myCurrentIndex].getMyAnswer();
        int messageResId = 0;

        if (answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelKanji, LevelKanji2.class);
            intent.putExtra("myCurrentIndex", myCurrentIndex);
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
        setContentView(R.layout.activity_level_kanji);

        audio = (ImageButton) findViewById(R.id.btnAudio3);
        next = (ImageButton) findViewById(R.id.btnNext3);
        check = (Button) findViewById(R.id.btnCheck3);
        help = (Button) findViewById(R.id.btnQuiz3);
        img = (TextView) findViewById(R.id.imageView3);
        enterText = (EditText) findViewById(R.id.editText3);
        hint = (TextView) findViewById(R.id.pic_hint3);
        button = (Button) findViewById(R.id.buttonAlert);
        addNote = (Button) findViewById(R.id.btnAddNote3);

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

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                falseCT = 0;
                addNote.setVisibility(View.INVISIBLE);

                String character = myKanjiSet[myCurrentIndex].getMyCharacter();
                String answer = myKanjiSet[myCurrentIndex].getMyAnswer();
                String tmp = character + "  " + answer + "\n";

                writeData(tmp);

                Toast.makeText(context, myKanjiSet[myCurrentIndex].getMyCharacter() +
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
