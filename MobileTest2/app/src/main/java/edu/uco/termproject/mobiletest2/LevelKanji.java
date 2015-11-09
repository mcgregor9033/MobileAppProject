package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

public class LevelKanji extends Activity {

    private static final String KEY_INDEX = "index";

    private ImageButton audio;
    private ImageButton next;
    private Button help;
    private Button check;
    private ImageView img;
    private EditText enterText;
    private TextView hint;

    final Context context = this;
    private Button button;

    private Kanji [] myKanjiSet = new Kanji[] {
            new Kanji("sea","うみ"), new Kanji("god","かみ"), new Kanji("moon","つき"),
            new Kanji("dream","ゆめ"), new Kanji("sky","そら")
    };

    private int myCurrentIndex = 0;

    private void updateCharacter(){
        String character = myKanjiSet[myCurrentIndex].getMyImgName();
        String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);
    }

    private void checkAnswer (LevelKanji levelKanji, String userEnterAnswer){
        String answer = myKanjiSet[myCurrentIndex].getMyAnswer();
        int messageResId = 0;

        if(answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelKanji, LevelKanjiMultipleGuess.class);
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
                Toast.makeText(LevelKanji.this,"Audio Button Clicked!", Toast.LENGTH_LONG).show();
            }
        });

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Confused?? Here's what to do");

                // set dialog message
                alertDialogBuilder.setMessage("Using the keyboard, select the proper english letters for the Kanji Character displayed.").setCancelable(false)
                        .setNegativeButton("Let's Go!!!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
                textView.setTextSize(32);
                Button btn1 = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                btn1.setTextSize(40);
                btn1.setTextColor(Color.YELLOW);
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
                myCurrentIndex = (myCurrentIndex + 1) % myKanjiSet.length;
                hint.setVisibility(View.INVISIBLE);
                updateCharacter();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelKanji.this, enterText.getText().toString());
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
                Toast.makeText(LevelKanji.this,R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
