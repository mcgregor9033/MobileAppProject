package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LevelKanji2 extends Activity {
    private Button choice1, choice2,choice3,choice4;
    private int num1, num2, num3, num4;
    private TextView img;
    final Context context = this;
    Random rand = new Random();

    private Diction [] myKanjiSet = new Diction[] {
            new Diction("one","一","いち"), new Diction("two","二","に"), new Diction("three","三","さん"),
            new Diction("four","四","し"), new Diction("five","五","ご"), new Diction("six","六","ろく"),
            new Diction("seven","七","しち"), new Diction("eight","八","はち"), new Diction("nine","九","きゅう"),
            new Diction("ten","十","じゅう"), new Diction("yen","円","えん"), new Diction("hundred","百","ひゃく"),
            new Diction("thousand","千","せん"),new Diction("tenthousand","万","まん"), new Diction("what","何","なに"),
            new Diction("sun","日","ひ"), new Diction("moon","月","つき"), new Diction("light","明","あか"),
            new Diction("temple","寺","てら"), new Diction("time","時","じ"), new Diction("fire","火","ひ"),
            new Diction("water","水","みず"), new Diction("tree","木","き"), new Diction("money","金","かね"), new Diction("soil","土","つち")
    };

    private int myCurrentIndex;

    private void updateCharacter(){
        String character = myKanjiSet[myCurrentIndex].getMyImgName();

        /*String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);*/
        img.setText(myKanjiSet[myCurrentIndex].getMyCharacter());
        img.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.img_font));

        num1 = num2 = num3 = num4 = rand.nextInt(myKanjiSet.length);
        while (num2 == num1)
            num2 = rand.nextInt(myKanjiSet.length);
        while (num3 == num1 || num3 == num2)
            num3 = rand.nextInt(myKanjiSet.length);
        while (num4 == num3 || num4 == num2 || num4 == num1)
            num4 = rand.nextInt(myKanjiSet.length);

        int reference = rand.nextInt(4);

        switch (reference) {
            case 0:
                num1 = myCurrentIndex;
                break;
            case 1:
                num2 = myCurrentIndex;
                break;
            case 2:
                num3 = myCurrentIndex;
                break;
            case 3:
                num4 = myCurrentIndex;
                break;
        }

        choice1.setText(myKanjiSet[num1].getMyImgName());
        choice2.setText(myKanjiSet[num2].getMyImgName());
        choice3.setText(myKanjiSet[num3].getMyImgName());
        choice4.setText(myKanjiSet[num4].getMyImgName());
    }

    private void checkAnswer (LevelKanji2 levelKanji2, String userEnterAnswer){
        String answer = myKanjiSet[myCurrentIndex].getMyImgName();
        int messageResId = 0;
        ThemeUtils.onActivityCreateSetTheme(this);

        if(answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelKanji2, LevelKanjiMultipleGuess.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity (intent);
        }else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_level_kanji2);

        choice1 = (Button) findViewById(R.id.btnChoice1);
        choice2 = (Button) findViewById(R.id.btnChoice2);
        choice3 = (Button) findViewById(R.id.btnChoice3);
        choice4 = (Button) findViewById(R.id.btnChoice4);
        img = (TextView) findViewById(R.id.imageView4);

        Intent intent = getIntent();
        myCurrentIndex = intent.getIntExtra("myCurrentIndex", 0);

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelKanji2.this, choice1.getText().toString());
            }
        });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelKanji2.this, choice2.getText().toString());
            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelKanji2.this, choice3.getText().toString());
            }
        });

        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(LevelKanji2.this, choice4.getText().toString());
            }
        });

        updateCharacter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_kanji2, menu);
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
