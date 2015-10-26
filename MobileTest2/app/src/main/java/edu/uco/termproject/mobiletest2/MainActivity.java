package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button gameButton,katakanaButton,hiraganaButton,kanjiButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameButton = (Button) findViewById(R.id.game_button);
        katakanaButton = (Button) findViewById(R.id.katakana_button);
        hiraganaButton = (Button) findViewById(R.id.hiragana_button);
        kanjiButton = (Button) findViewById(R.id.kanji_button );

        hiraganaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, LevelOne.class);
               startActivity(intent);

            }
        });
        katakanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(MainActivity.this, LevelOne.class);
               // startActivity(intent);

            }
        });
        kanjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(MainActivity.this, LevelOne.class);
             //   startActivity(intent);

            }
        });
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, LevelOne.class);
               // startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
