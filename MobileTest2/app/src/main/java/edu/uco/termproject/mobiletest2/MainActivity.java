package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button gameButton, katakanaButton, hiraganaButton, kanjiButton,vocabButton;
    private ImageButton titleScreen;
    boolean kata, hira, kanji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleScreen = (ImageButton) findViewById(R.id.title_screen);
        gameButton = (Button) findViewById(R.id.game_button);
        katakanaButton = (Button) findViewById(R.id.katakana_button);
        hiraganaButton = (Button) findViewById(R.id.hiragana_button);
        kanjiButton = (Button) findViewById(R.id.kanji_button);
        vocabButton = (Button) findViewById(R.id.vocab_button);

        gameButton.setVisibility(View.INVISIBLE);
        hiraganaButton.setVisibility(View.INVISIBLE);
        katakanaButton.setVisibility(View.INVISIBLE);
        kanjiButton.setVisibility(View.INVISIBLE);

        kanjiButton.setEnabled(false);
        gameButton.setEnabled(false);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        //kata = preferences.getBoolean("kataUnlock", false);
        kata = getDefaults("kataUnlock", getApplicationContext());
        //hana = preferences.getBoolean("hanaUnlock", false);
        hira = getDefaults("hiraUnlock", getApplicationContext());
        //kanji = preferences.getBoolean("kanjiUnlock", false);
        kanji = getDefaults("kanjiUnlock", getApplicationContext());
        if (kata && hira) {
            kanjiButton.setEnabled(true);
        }
        else if (kanji) {
            kanjiButton.setEnabled(true);
            gameButton.setEnabled(true);
        }

/*
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("levelUnlock", 0);
        editor.apply();
*/
        titleScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameButton.setVisibility(View.VISIBLE);
                hiraganaButton.setVisibility(View.VISIBLE);
                katakanaButton.setVisibility(View.VISIBLE);
                kanjiButton.setVisibility(View.VISIBLE);
                titleScreen.setVisibility(View.INVISIBLE);
            }
        });


        hiraganaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransitionHiragana.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);

            }
        });
        katakanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransitionKatakana.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);

            }
        });
        kanjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransitionKanji.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);

            }
        });
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HangmanGame.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);

            }
        });
        vocabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VocabListSelectionActivity.class);
                startActivity(intent);
            }
        });
    }




    public static boolean getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
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
                Toast.makeText(MainActivity.this, R.string.guess_count, Toast.LENGTH_LONG).show();
                return true;
            case R.id.themes:
                Toast.makeText(MainActivity.this,R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
