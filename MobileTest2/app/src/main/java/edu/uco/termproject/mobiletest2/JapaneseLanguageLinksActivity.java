package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class JapaneseLanguageLinksActivity extends Activity {
    private Button katakanaButton, hiraganaButton, kanjiButton, grammarButton, dictionaryButton;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_japanese_language_links);

        katakanaButton = (Button) findViewById(R.id.katakana_button);
        hiraganaButton = (Button) findViewById(R.id.hiragana_button);
        kanjiButton = (Button) findViewById(R.id.kanji_button);
        grammarButton = (Button) findViewById(R.id.grammar_button);
        dictionaryButton = (Button) findViewById(R.id.dictionary_button);



        hiraganaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uriIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://en.wikipedia.org/wiki/Hiragana"));
                startActivity(uriIntent);

            }
        });
        kanjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uriIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.manythings.org/kanji/"));
                startActivity(uriIntent);

            }
        });
        grammarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uriIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://thejapanesepage.com/grammar.htm/"));
                startActivity(uriIntent);

            }
        });
        dictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uriIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://jisho.org/"));
                startActivity(uriIntent);

            }
        });
        katakanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uriIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://en.wikipedia.org/wiki/Katakana"));
                startActivity(uriIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_japanese_language_links, menu);
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
