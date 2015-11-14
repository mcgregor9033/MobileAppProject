package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class HangmanGame extends Activity {

    private ArrayList<Word> wordBank;
    private static String currentWord;

    private ImageView currentHangmanImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);

        setUpWordBank();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hangman_game, menu);
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
                Toast.makeText(HangmanGame.this, R.string.guess_count, Toast.LENGTH_LONG).show();
                return true;
            case R.id.themes:
                Toast.makeText(HangmanGame.this, R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setUpWordBank()
    {
         Word newWord = new Word("Japan","nihon","にほん","日本");
         wordBank.add(newWord);
        newWord = new Word("Japan","nihon","にほん","日本");
        newWord = new Word("dog","inu","いぬ","犬");
        newWord = new Word("crab","kani","かに","蟹");
        newWord = new Word("Doctor","isha","いしゃ","医者");
        newWord = new Word("Disneyland","Disneyland","ディズニーランド","");
        newWord = new Word("to drink","nomu","のむ","飲む");
        newWord = new Word("to eat","taberu","たべる","食べる");
        newWord = new Word("Please go and return.","itterashai","いってらしゃい","");
        newWord = new Word("University","daigaku","だいがく","大学");
    }


}

