package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class HangmanGame extends Activity {
    /*game Logic data*/
    private final int MAX_GUESSES = 7;
    private ArrayList<Word> wordBank = new ArrayList<Word>();
    private Word currentWord;
    private String currentWordRomaji;
    private int currentWordLength;
    private int wordBankSize;
    private StringBuffer wordToBeGuessed = new StringBuffer();
    private int wrongGuesses;


    private boolean gameOver;
    private boolean guessed;


    private boolean choiceChecked;

//---------------------------------------------------------------
    /*UI data*/
    private ImageView currentHangmanImage;
    private TextView wTBGTextView;
    private EditText playerGuessEditText;
    private TextView playerGuessEditTextDebugging;
    private Button checkGuessButton, quitButton,playAgainButton;
//---------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);
        this.choiceChecked = false;
        wTBGTextView = (TextView) findViewById(R.id.word_to_be_guessed_text_view);
        playerGuessEditText = (EditText) findViewById(R.id.player_input_edit_text);
        playerGuessEditTextDebugging = (TextView) findViewById(R.id.word_to_be_guessed_text_view_debugging);
        checkGuessButton = (Button) findViewById(R.id.check_guess_button);
        quitButton = (Button) findViewById(R.id.quit_button);
        playAgainButton = (Button) findViewById(R.id.play_again_button);

        if (wordBank.size()==0)
        {setUpWordBank();}
        setUpNewGame();

        checkGuessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOver = checkPlayersGuess();
                if (gameOver) {
                    checkGuessButton.setVisibility(View.INVISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    quitButton.setVisibility(View.VISIBLE);
                    if (checkIfSolved()) {
                        Toast.makeText(getApplicationContext(), "You saved them.  You Win!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "You let them die.  You Lose!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgainButton.setVisibility(View.INVISIBLE);
                quitButton.setVisibility(View.INVISIBLE);
                setUpNewGame();
                checkGuessButton.setVisibility(View.VISIBLE);
            }
        });
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitButton.setVisibility(View.INVISIBLE);
                playAgainButton.setVisibility(View.INVISIBLE);
                finish();
            }
        });
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private boolean checkPlayersGuess() {
        String tempGuess = playerGuessEditText.getText().toString();
        char tempChar = tempGuess.charAt(0);
        if (this.currentWordRomaji.contains(tempGuess)) {
            editDisplayedToBeGuessed(tempChar);
            Toast.makeText(getApplicationContext(), tempGuess + " was within the word.", Toast.LENGTH_SHORT).show();
        } else if (!this.currentWordRomaji.contains(tempGuess)) {
            this.wrongGuesses = this.wrongGuesses + 1;
            Toast.makeText(getApplicationContext(), tempGuess + " is NOT within the word.", Toast.LENGTH_SHORT).show();
        }
        playerGuessEditText.setText("");
        if (this.wrongGuesses == MAX_GUESSES+this.wordToBeGuessed.length() || checkIfSolved())
        {
            return true;
        }
        return false;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private boolean checkIfSolved()
    {
        for (int i=0;i<this.wordToBeGuessed.length();i++)
        {
            if(this.wordToBeGuessed.charAt(i)=='_') return false;
        }
        return true;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void editDisplayedToBeGuessed(char guess)
    {
        for(int i=0;i<this.wordToBeGuessed.length();i++)
        {
            if (this.currentWordRomaji.charAt(i) == guess)
            {
                this.wordToBeGuessed.setCharAt(i, guess);
            }
        }
        wTBGTextView.setText(this.wordToBeGuessed);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setUpNewGame()
    {
        this.currentWordRomaji="";
        this.wordToBeGuessed.delete(0, this.wordToBeGuessed.length());
        this.wrongGuesses = 0;
        getWordBankSize();
        getCurrentWord();
        setCurrentWordLength();
        setUpDisplayedWordToBeGuessed();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setUpDisplayedWordToBeGuessed()
    {
        this.wordToBeGuessed.delete(0, this.currentWordLength);
        for(int i=0;i<this.currentWordLength;i++)
        {
            this.wordToBeGuessed.append('_');
        }
        playerGuessEditTextDebugging.setText(this.currentWordRomaji);
        wTBGTextView.setText(this.wordToBeGuessed);
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void getWordBankSize()
    {
        this.wordBankSize = wordBank.size();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setCurrentWordLength()
    {
        this.currentWordLength = this.currentWordRomaji.length();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void getCurrentWord()
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(wordBankSize);
        this.currentWord = wordBank.get(randomNum);
        this.currentWordRomaji = this.currentWord.getRomaji();
       // Toast.makeText(getApplicationContext(), currentWord.getRomaji()+" "+randomNum,Toast.LENGTH_SHORT).show();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setUpWordBank()
    {
        Word newWord = new Word("Japan","nihon","にほん","日本"); wordBank.add(newWord);
        newWord = new Word("dog","inu","いぬ","犬"); wordBank.add(newWord);
        newWord = new Word("crab","kani","かに","蟹"); wordBank.add(newWord);
        newWord = new Word("Doctor","isha","いしゃ","医者"); wordBank.add(newWord);
        newWord = new Word("Disneyland","Disneyland","ディズニーランド",""); wordBank.add(newWord);
        newWord = new Word("to drink","nomu","のむ","飲む"); wordBank.add(newWord);
        newWord = new Word("to eat","taberu","たべる","食べる"); wordBank.add(newWord);
        newWord = new Word("Please go and return.","itterashai","いってらしゃい",""); wordBank.add(newWord);
        newWord = new Word("University","daigaku","だいがく","大学"); wordBank.add(newWord);
    }
    //Android Overrode methods----------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ThemeUtils.onActivityCreateSetTheme(this);
        getMenuInflater().inflate(R.menu.menu_hangman_game, menu);

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
                Toast.makeText(HangmanGame.this, R.string.guess_count, Toast.LENGTH_LONG).show();
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

