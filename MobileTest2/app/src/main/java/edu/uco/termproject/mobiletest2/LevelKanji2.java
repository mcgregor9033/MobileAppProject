package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class LevelKanji2 extends Activity {
    private Button choice1, choice2,choice3,choice4;
    private ImageView img;

    private Kanji [] myKanjiSet = new Kanji[] {
            new Kanji("one","いち"), new Kanji("two","に"), new Kanji("three","さん"),
            new Kanji("four","し"), new Kanji("five","ご"),
            new Kanji("six","ろく"), new Kanji("seven","しち"), new Kanji("eight","はち"),
            new Kanji("nine","きゅう"), new Kanji("ten","じゅう"),
            new Kanji("yen","えん"), new Kanji("hundred","ひゃく"), new Kanji("thousand","せん"),
            new Kanji("tenthousand","まん"), new Kanji("what","なに"),
            new Kanji("sun","ひ"), new Kanji("moon","つき"), new Kanji("light","あか"),
            new Kanji("temple","てら"), new Kanji("time","じ"),
            new Kanji("fire","ひ"), new Kanji("water","みず"), new Kanji("tree","き"),
            new Kanji("money","かね"), new Kanji("soil","つち")/*,
            new Kanji("",""), new Kanji("",""), new Kanji("",""),
            new Kanji("",""), new Kanji("","")*/
    };

    private int myCurrentIndex;

    private void updateCharacter(){
        String character = myKanjiSet[myCurrentIndex].getMyImgName();
        String uri = "@drawable/" + character;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        img.setImageDrawable(res);

        Random rand = new Random();
        int tmp = rand.nextInt(myKanjiSet.length);
        choice1.setText(myKanjiSet[tmp].getMyImgName());
        tmp = rand.nextInt(myKanjiSet.length);
        choice2.setText(myKanjiSet[tmp].getMyImgName());
        tmp = rand.nextInt(myKanjiSet.length);
        choice3.setText(character);
        tmp = rand.nextInt(myKanjiSet.length);
        choice4.setText(myKanjiSet[tmp].getMyImgName());
    }

    private void checkAnswer (LevelKanji2 levelKanji2, String userEnterAnswer){
        String answer = myKanjiSet[myCurrentIndex].getMyImgName();
        int messageResId = 0;

        if(answer.equals(userEnterAnswer)) {
            messageResId = R.string.correct_toast;
            Intent intent = new Intent(levelKanji2, LevelKanji.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity (intent);
        }else
            messageResId = R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_kanji2);

        choice1 = (Button) findViewById(R.id.btnChoice1);
        choice2 = (Button) findViewById(R.id.btnChoice2);
        choice3 = (Button) findViewById(R.id.btnChoice3);
        choice4 = (Button) findViewById(R.id.btnChoice4);
        img = (ImageView) findViewById(R.id.imageView4);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
