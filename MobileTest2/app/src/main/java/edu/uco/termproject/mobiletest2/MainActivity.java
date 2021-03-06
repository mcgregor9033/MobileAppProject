package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class MainActivity extends Activity {

    private Button linksButton, gameButton, katakanaButton, hiraganaButton, kanjiButton, vocabButton, statsButton;
    private ImageButton titleScreen;

    final Context context = this;

    boolean kata, hira, kanji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        titleScreen = (ImageButton) findViewById(R.id.title_screen);
        gameButton = (Button) findViewById(R.id.game_button);
        katakanaButton = (Button) findViewById(R.id.katakana_button);
        hiraganaButton = (Button) findViewById(R.id.hiragana_button);
        kanjiButton = (Button) findViewById(R.id.kanji_button);
        vocabButton = (Button) findViewById(R.id.vocab_button);
        linksButton = (Button) findViewById(R.id.links_button);
        statsButton = (Button) findViewById(R.id.stats_button);

        gameButton.setVisibility(View.INVISIBLE);
        hiraganaButton.setVisibility(View.INVISIBLE);
        katakanaButton.setVisibility(View.INVISIBLE);
        kanjiButton.setVisibility(View.INVISIBLE);
        linksButton.setVisibility((View.INVISIBLE));

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
        if (kanji) {
            gameButton.setEnabled(true);
        }

        titleScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameButton.setVisibility(View.VISIBLE);
                hiraganaButton.setVisibility(View.VISIBLE);
                katakanaButton.setVisibility(View.VISIBLE);
                kanjiButton.setVisibility(View.VISIBLE);
                linksButton.setVisibility((View.VISIBLE));
                titleScreen.setVisibility(View.INVISIBLE);
            }
        });


        hiraganaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Challenge yourself")
                        .setIcon(R.drawable.question)
                        .setMessage(R.string.question_for_quiz)
                        .setCancelable(true)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(context, LevelHiraganaQuiz.class);
                                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(context, LevelHiragana.class);
                                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });
        katakanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Challenge yourself")
                        .setIcon(R.drawable.question)
                        .setMessage(R.string.question_for_quiz)
                        .setCancelable(true)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(context, LevelKatakanaQuiz.class);
                                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(context, LevelKatakana.class);
                                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });
        kanjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Challenge yourself")
                        .setIcon(R.drawable.question)
                        .setMessage(R.string.question_for_quiz).setCancelable(true)
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(context, LevelKanjiQuiz.class);
                                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(context, LevelKanji.class);
                                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

            }
        });
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HangmanGame.class);
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
        linksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JapaneseLanguageLinksActivity.class);
                startActivity(intent);
            }
        });
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatsActivity.class);
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