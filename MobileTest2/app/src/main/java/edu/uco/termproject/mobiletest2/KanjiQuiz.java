package edu.uco.termproject.mobiletest2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KanjiQuiz extends Activity {

    TextView number, question;
    ImageButton boxUpperLeft, boxUpperCenter, boxUpperRight, boxCenterLeft, boxCenterCenter, boxCenterRight, boxBottomLeft, boxBottomCenter, boxBottomRight;
    private int reference, max = 28, min = 0, num1, num2, num3, num4, num5, num6, num7, num8, num9, ansNum;
    private List que = new ArrayList();
    private Random rand;
    String answerWord, ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9;
    Button next;
    ArrayList answersReference = new ArrayList();
    ArrayList answers = new ArrayList();

    final Context context = this;
    private Button button;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_quiz);

        number = (TextView) findViewById(R.id.problem_number);
        question = (TextView) findViewById(R.id.word);
        boxUpperLeft = (ImageButton) findViewById(R.id.buttonUL);
        boxUpperCenter = (ImageButton) findViewById(R.id.buttonUC);
        boxUpperRight = (ImageButton) findViewById(R.id.buttonUR);
        boxCenterLeft = (ImageButton) findViewById(R.id.buttonCL);
        boxCenterCenter = (ImageButton) findViewById(R.id.buttonCC);
        boxCenterRight = (ImageButton) findViewById(R.id.buttonCR);
        boxBottomLeft = (ImageButton) findViewById(R.id.buttonBL);
        boxBottomCenter = (ImageButton) findViewById(R.id.buttonBC);
        boxBottomRight = (ImageButton) findViewById(R.id.buttonBR);
        next = (Button) findViewById(R.id.feedback);
        button = (Button) findViewById(R.id.buttonAlert);

        Resources res = getResources();
        TypedArray pictures = res.obtainTypedArray(R.array.kanji);
        TypedArray names = res.obtainTypedArray(R.array.words);

        Intent intent = getIntent();
        if (intent.hasExtra("info")) {
            answers = intent.getStringArrayListExtra("info");
            answersReference = intent.getStringArrayListExtra("reference_info");

            for (int i = 0; i < answersReference.size(); i++) {
                que.add(Integer.parseInt((String) answersReference.get(i)));
            }
        }

        if (!intent.hasExtra("info")) {
            number.setTextColor(Color.YELLOW);
            number.setTextSize(40);
            number.setText(R.string.quiz_count_default);
        }
        else {
            number.setTextColor(Color.YELLOW);
            number.setTextSize(40);
            number.setText((answers.size() + 1) + " of 5");
        }

        rand = new Random();

        num1 = num2 = num3 = num4 = num5 = num6 = num7 = num8 = num9 = rand.nextInt((max - min) + 1) + min;
        while (num2 == num1)
            num2 = rand.nextInt((max - min) + 1) + min;
        while (num3 == num1 || num3 == num2)
            num3 = rand.nextInt((max - min) + 1) + min;
        while (num4 == num3 || num4 == num2 || num4 == num1)
            num4 = rand.nextInt((max - min) + 1) + min;
        while (num5 == num4 || num5 == num3 || num5 == num2 || num5 == num1)
            num5 = rand.nextInt((max - min) + 1) + min;
        while (num6 == num5 || num6 == num4 || num6 == num3 || num6 == num2 || num6 == num1)
            num6 = rand.nextInt((max - min) + 1) + min;
        while (num7 == num6 || num7 == num5 || num7 == num4 || num7 == num3 || num7 == num2 || num7 == num1)
            num7 = rand.nextInt((max - min) + 1) + min;
        while (num8 == num7 || num8 == num6 || num8 == num5 || num8 == num4 || num8 == num3 || num8 == num2 || num8 == num1)
            num8 = rand.nextInt((max - min) + 1) + min;
        while (num9 == num8 || num9 == num7 || num9 == num6 || num9 == num5 || num9 == num4 || num9 == num3 || num9 == num2 || num9 == num1)
            num9 = rand.nextInt((max - min) + 1) + min;

        ans1 = names.getString(num1);
        ans2 = names.getString(num2);
        ans3 = names.getString(num3);
        ans4 = names.getString(num4);
        ans5 = names.getString(num5);
        ans6 = names.getString(num6);
        ans7 = names.getString(num7);
        ans8 = names.getString(num8);
        ans9 = names.getString(num9);

        boolean doubleCheck = true;
        while (doubleCheck) {
            doubleCheck = false;
            reference = rand.nextInt((8 - min) + 1) + min;

            switch (reference) {
                case 0:
                    answerWord = names.getString(num1);
                    ansNum = num1;
                    if (que.contains(num1))
                        doubleCheck = true;
                    break;
                case 1:
                    answerWord = names.getString(num2);
                    ansNum = num2;
                    if (que.contains(num2))
                        doubleCheck = true;
                    break;
                case 2:
                    answerWord = names.getString(num3);
                    ansNum = num3;
                    if (que.contains(num3))
                        doubleCheck = true;
                    break;
                case 3:
                    answerWord = names.getString(num4);
                    ansNum = num4;
                    if (que.contains(num4))
                        doubleCheck = true;
                    break;
                case 4:
                    answerWord = names.getString(num5);
                    ansNum = num5;
                    if (que.contains(num5))
                        doubleCheck = true;
                    break;
                case 5:
                    answerWord = names.getString(num6);
                    ansNum = num6;
                    if (que.contains(num6))
                        doubleCheck = true;
                    break;
                case 6:
                    answerWord = names.getString(num7);
                    ansNum = num7;
                    if (que.contains(num7))
                        doubleCheck = true;
                    break;
                case 7:
                    answerWord = names.getString(num8);
                    ansNum = num8;
                    if (que.contains(num8))
                        doubleCheck = true;
                    break;
                case 8:
                    answerWord = names.getString(num9);
                    ansNum = num9;
                    if (que.contains(num9))
                        doubleCheck = true;
                    break;
            }
        }
        names.recycle();

        question.setText(answerWord);
        boxUpperLeft.setImageDrawable(pictures.getDrawable(num1));
        boxUpperCenter.setImageDrawable(pictures.getDrawable(num2));
        boxUpperRight.setImageDrawable(pictures.getDrawable(num3));
        boxCenterLeft.setImageDrawable(pictures.getDrawable(num4));
        boxCenterCenter.setImageDrawable(pictures.getDrawable(num5));
        boxCenterRight.setImageDrawable(pictures.getDrawable(num6));
        boxBottomLeft.setImageDrawable(pictures.getDrawable(num7));
        boxBottomCenter.setImageDrawable(pictures.getDrawable(num8));
        boxBottomRight.setImageDrawable(pictures.getDrawable(num9));
        pictures.recycle();

        boxUpperLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans1.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxUpperCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans2.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxUpperRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans3.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxCenterLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans4.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxCenterCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans5.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxCenterRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans6.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans7.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxBottomCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans8.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
            }
        });

        boxBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans9.equals(answerWord)) {
                    setPass();
                } else
                    setFail();
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
                alertDialogBuilder.setMessage("Click the button below that matches the correct Kanji symbol.").setCancelable(false)
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
    }

    public static void setDefaults(String key, boolean value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setPass() {
        answers.add("correct");
        answersReference.add(Integer.toString(ansNum));
        if (answers.size() < 5) {
            Intent intent = new Intent(KanjiQuiz.this, KanjiQuiz.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
            intent.putStringArrayListExtra("info", answers);
            intent.putStringArrayListExtra("reference_info", answersReference);
            startActivity(intent);
        }
        else {

            int count = 0;
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i).toString().equals("correct"))
                    count++;
            }

            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            if (count > 3) {
                setDefaults("kanjiUnlock", true, getApplicationContext());
            }

            Intent intent = new Intent(KanjiQuiz.this, ResultsActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
            intent.putStringArrayListExtra("info", answers);
            intent.putStringArrayListExtra("reference_info", answersReference);
            startActivity(intent);
        }
    }

    public void setFail() {
        answers.add("incorrect");
        answersReference.add(Integer.toString(ansNum));
        if (answers.size() < 5) {
            Intent intent = new Intent(KanjiQuiz.this, KanjiQuiz.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
            intent.putStringArrayListExtra("info", answers);
            intent.putStringArrayListExtra("reference_info", answersReference);
            startActivity(intent);
        }
        else {

            int count = 0;
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i).toString().equals("correct"))
                    count++;
            }

            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            if (count > 3) {
                setDefaults("kanjiUnlock", true, getApplicationContext());
            }

            Intent intent = new Intent(KanjiQuiz.this, ResultsActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
            intent.putStringArrayListExtra("info", answers);
            intent.putStringArrayListExtra("reference_info", answersReference);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_kanji_quiz, menu);
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
                Toast.makeText(KanjiQuiz.this, R.string.guess_count, Toast.LENGTH_LONG).show();
                return true;
            case R.id.themes:
                Toast.makeText(KanjiQuiz.this, R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
