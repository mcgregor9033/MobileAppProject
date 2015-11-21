package edu.uco.termproject.mobiletest2;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StatsActivity extends Activity {

    TextView stats;
    Button hiraButton, kataButton, kanButton;
    private ArrayList hiraganaScore, katakanaScore, kanjiScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                GraphView graphView = new GraphView(StatsActivity.this, tempList, "GraphViewDemo", horlabels, verlabels, GraphView.LINE, "Hiragana Statistics");
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
                GraphView graphView = new GraphView(StatsActivity.this, tempList, "GraphViewDemo", horlabels, verlabels, GraphView.LINE, "Katakana Statistics");
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

}

