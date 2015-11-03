package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class LevelHiraganaMultipleGuess extends Activity {

    TextView question;
    ImageButton box00, box01, box10, box11;
    private int reference, max = 45, min = 0, count = 0, num1, num2, num3, num4;
    private Random rand;
    String answerWord, ans1, ans2, ans3, ans4;
    Switch help;
    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_hiragana_quiz);

        question = (TextView) findViewById(R.id.word);
        box00 = (ImageButton) findViewById(R.id.button00);
        box01 = (ImageButton) findViewById(R.id.button01);
        box10 = (ImageButton) findViewById(R.id.button10);
        box11 = (ImageButton) findViewById(R.id.button11);
        help = (Switch) findViewById(R.id.help);
        next = (Button) findViewById(R.id.feedback);

        Resources res = getResources();
        TypedArray pictures = res.obtainTypedArray(R.array.hiragana);
        TypedArray names = res.obtainTypedArray(R.array.names);

        rand = new Random();

            num1 = num2 = num3 = num4 = rand.nextInt((max - min) + 1) + min;
            while (num2 == num1)
                num2 = rand.nextInt((max - min) + 1) + min;
            while (num3 == num1 || num3 == num2)
                num3 = rand.nextInt((max - min) + 1) + min;
            while (num4 == num3 || num4 == num2 || num4 == num1)
                num4 = rand.nextInt((max - min) + 1) + min;

        ans1 = names.getString(num1);
        ans2 = names.getString(num2);
        ans3 = names.getString(num3);
        ans4 = names.getString(num4);

            reference = rand.nextInt((3 - min) + 1) + min;

            switch (reference) {
                case 0:
                    answerWord = names.getString(num1);
                    break;
                case 1:
                    answerWord = names.getString(num2);
                    break;
                case 2:
                    answerWord = names.getString(num3);
                    break;
                case 3:
                    answerWord = names.getString(num4);
                    break;
            }

        names.recycle();

        question.setText(answerWord);
        box00.setImageDrawable(pictures.getDrawable(num1));
        box01.setImageDrawable(pictures.getDrawable(num2));
        box10.setImageDrawable(pictures.getDrawable(num3));
        box11.setImageDrawable(pictures.getDrawable(num4));
        pictures.recycle();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelHiraganaMultipleGuess.this, LevelHiragana.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(intent);
            }
        });

        box00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans1.equals(answerWord)) {
                    //Toast.makeText(LevelOneQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
                    next.setVisibility(View.VISIBLE);
                    next.setClickable(true);
                } else
                    Toast.makeText(LevelHiraganaMultipleGuess.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
        });

        box01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans2.equals(answerWord)) {
                    //Toast.makeText(LevelOneQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
                    next.setVisibility(View.VISIBLE);
                    next.setClickable(true);
                }
                else
                    Toast.makeText(LevelHiraganaMultipleGuess.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
        });

        box10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans3.equals(answerWord)) {
                    //Toast.makeText(LevelOneQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
                    next.setVisibility(View.VISIBLE);
                    next.setClickable(true);
                }
                else
                    Toast.makeText(LevelHiraganaMultipleGuess.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
        });

        box11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans4.equals(answerWord)) {
                    //Toast.makeText(LevelOneQuiz.this, "Correct!", Toast.LENGTH_SHORT).show();
                    next.setVisibility(View.VISIBLE);
                    next.setClickable(true);
                }
                else
                    Toast.makeText(LevelHiraganaMultipleGuess.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
        });

        help.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //switch true
                if(isChecked) {
                    rand = new Random();

                    //get wrong answer
                    int temp = reference;
                    while (temp == reference ) {
                        temp = rand.nextInt((3 - min) + 1) + min;

                        //validate
                        if (temp == 0 && num1 == -1)
                            temp = reference;
                        else if (temp == 1 && num2 == -1)
                            temp = reference;
                        else if (temp == 2 && num3 == -1)
                            temp = reference;
                        else if (temp == 3 && num4 == -1)
                            temp = reference;
                        else if (count == 3)
                            temp = -1;

                    }

                    //set the wrong answer to null and invalidate
                    switch (temp) {
                        case 0:
                            box00.setImageDrawable(null);
                            num1 = -1;
                            count++;
                            break;
                        case 1:
                            box01.setImageDrawable(null);
                            num2 = -1;
                            count++;
                            break;
                        case 2:
                            box10.setImageDrawable(null);
                            num3 = -1;
                            count++;
                            break;
                        case 3:
                            box11.setImageDrawable(null);
                            num4 = -1;
                            count++;
                            break;
                        default:
                            System.out.println("error");
                    }

                    // reset switch
                    help.setChecked(false);
                }
                else {
                    Toast.makeText(LevelHiraganaMultipleGuess.this, "no help!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_hiragana_quiz, menu);
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
                Toast.makeText(LevelHiraganaMultipleGuess.this, R.string.guess_count, Toast.LENGTH_LONG).show();
                return true;
            case R.id.themes:
                Toast.makeText(LevelHiraganaMultipleGuess.this, R.string.theme, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
