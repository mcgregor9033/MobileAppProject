package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StatsActivity extends Activity {

    TextView stats;
    Button hiraButton, kataButton, kanButton;
    private ArrayList hiraganaScore, katakanaScore, kanjiScore;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_stats);

        stats = (TextView) findViewById(R.id.stats);
        hiraButton = (Button) findViewById(R.id.hiragana_stat_button);
        kataButton = (Button) findViewById(R.id.katakana_stat_button);
        kanButton = (Button) findViewById(R.id.kanji_stat_button);

        hiraganaScore = new ArrayList();
        katakanaScore = new ArrayList();
        kanjiScore = new ArrayList();

        String type;
        String s = "";

        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("StatsFile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[512];
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] history = s.split("#");
        for (String st : history) {
            char[] tempArray = {0, 0, 0};
            if (st.contains("Hir")) {
                st.getChars(4, st.length(), tempArray, 0);
                hiraganaScore.add(Float.parseFloat(String.valueOf(tempArray)));
            }
            else if (st.contains("Kat")) {
                st.getChars(4, st.length(), tempArray, 0);
                katakanaScore.add(Float.parseFloat(String.valueOf(tempArray)));
            }
            else if (st.contains("Kan")) {
                st.getChars(4, st.length(), tempArray, 0);
                kanjiScore.add(Float.parseFloat(String.valueOf(tempArray)));
            }
        }

        hiraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] tempList = new float[hiraganaScore.size()];
                for (int i = 0; i < hiraganaScore.size(); i++) {
                    tempList[i] = (float) hiraganaScore.get(i);
                }

                //float[] values = new float[] { 2.0f,1.5f, 2.5f, 1.0f , 3.0f };
                String[] verlabels = new String[]{"great", "ok", "bad"};
                String[] horlabels = new String[]{"oldest quiz", "recent quiz", "newest quiz"};
                GraphView graphView = new GraphView(StatsActivity.this, tempList, "GraphViewDemo", horlabels, verlabels, GraphView.LINE, "KanaPool Statistics");
                setContentView(graphView);
            }
        });

        kataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] tempList = new float[katakanaScore.size()];
                for (int i = 0; i < katakanaScore.size(); i++) {
                    tempList[i] = (float) katakanaScore.get(i);
                }

                String[] tempStringList = new String[katakanaScore.size()];
                for (int i = 0; i < katakanaScore.size(); i++) {
                    tempStringList[i] = Float.toString(tempList[i]);
                }

                //float[] values = new float[] { 2.0f,1.5f, 2.5f, 1.0f , 3.0f };
                String[] verlabels = new String[]{"great", "ok", "bad"};
                String[] horlabels = new String[]{"oldest quiz", "recent quiz", "newest quiz"};
                GraphView graphView = new GraphView(StatsActivity.this, tempList, "GraphViewDemo", horlabels, verlabels, GraphView.LINE, "Diction Statistics");
                setContentView(graphView);
            }
        });

        kanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] tempList = new float[kanjiScore.size()];
                for (int i = 0; i < kanjiScore.size(); i++) {
                    tempList[i] = (float) kanjiScore.get(i);
                }

                //float[] values = new float[] { 2.0f,1.5f, 2.5f, 1.0f , 3.0f };
                String[] verlabels = new String[]{"great", "ok", "bad"};
                String[] horlabels = new String[]{"oldest quiz", "recent quiz", "newest quiz"};
                GraphView graphView = new GraphView(StatsActivity.this, tempList, "GraphViewDemo", horlabels, verlabels, GraphView.LINE, "Kanji Statistics");
                setContentView(graphView);
            }
        });
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

