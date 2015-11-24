package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotebookActivity extends Activity {
    private RadioButton btnhira, btnkata, btnkanji;
    private Button btnget;
    private TextView txthira, txtkata, txtkanji;
    final Context context = this;
    final String hiraFile = "Hira";
    final String kataFile = "Kata";
    final String kanjiFile = "Kanji";

    public String readSavedData(String filename) {
        StringBuffer datax = new StringBuffer("");
        try {
            FileInputStream fIn = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader buffreader = new BufferedReader(isr);

            String readString = buffreader.readLine();
            while (readString != null) {
                datax.append(readString);
                datax.append("\n");
                readString = buffreader.readLine();
            }

            isr.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return datax.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_notebook);

        btnhira = (RadioButton) findViewById(R.id.hiragana_radio_button);
        btnkata = (RadioButton) findViewById(R.id.katakana_radio_button);
        btnkanji = (RadioButton) findViewById(R.id.kanji_radio_button);
        btnget = (Button) findViewById(R.id.btn_get_text);
        txthira = (TextView) findViewById(R.id.txtHira);
        txtkata = (TextView) findViewById(R.id.txtKata);
        txtkanji = (TextView) findViewById(R.id.txtKanji);

        txthira.setText(readSavedData(hiraFile));
        txtkata.setText(readSavedData(kataFile));
        txtkanji.setText(readSavedData(kanjiFile));

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnhira.isChecked()) {
                    txthira.setVisibility(View.VISIBLE);
                    txtkata.setVisibility(View.INVISIBLE);
                    txtkanji.setVisibility(View.INVISIBLE);
                }
                if (btnkata.isChecked()) {
                    txthira.setVisibility(View.INVISIBLE);
                    txtkata.setVisibility(View.VISIBLE);
                    txtkanji.setVisibility(View.INVISIBLE);
                }
                if(btnkanji.isChecked()) {
                    txthira.setVisibility(View.INVISIBLE);
                    txtkata.setVisibility(View.INVISIBLE);
                    txtkanji.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notebook, menu);
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
