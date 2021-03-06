package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HangmanGame extends Activity {
    /*game Logic data*/
    private final int MAX_GUESSES = 7;
    private ArrayList<Word> wordBank = new ArrayList<Word>();
    private Word currentWord;
    private String currentWordRomaji;
    private String currentWordJapanese;
    private int currentWordLength;
    private int wordBankSize;
    private StringBuffer wordToBeGuessed = new StringBuffer();
    private StringBuffer lettersGuessed = new StringBuffer();
    private int wrongGuesses;
    final Context context =this;

    private boolean gameOver;
    private boolean guessed;
    private boolean choiceChecked;

//---------------------------------------------------------------
    /*UI data*/
    private TextView wordToBeGuessedJapanese;
    private TextView wTBGTextView;
    private EditText playerGuessEditText;
    private TextView playerGuessEditTextDebugging;
    private TextView lettersGuessedTextView;
    private Button checkGuessButton, quitButton,playAgainButton;
    private ImageButton hangmanImage;
    private Switch help;
//---------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_hangman_game);
        this.choiceChecked = false;
        wordToBeGuessedJapanese = (TextView) findViewById(R.id.word_to_be_guessed_japanese);
        hangmanImage = (ImageButton) findViewById(R.id.hangman_pic_temp);
        wTBGTextView = (TextView) findViewById(R.id.word_to_be_guessed_text_view);
        playerGuessEditText = (EditText) findViewById(R.id.player_input_edit_text);
        playerGuessEditTextDebugging = (TextView) findViewById(R.id.word_to_be_guessed_text_view_debugging);
        lettersGuessedTextView = (TextView) findViewById(R.id.letters_guessed_text_view);
        checkGuessButton = (Button) findViewById(R.id.check_guess_button);
        quitButton = (Button) findViewById(R.id.quit_button);
        playAgainButton = (Button) findViewById(R.id.play_again_button);
        help=(Switch)findViewById(R.id.help);

        if (wordBank.size() == 0) {
            setUpWordBank();
        }
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
                        hangmanImage.setImageResource(R.drawable.lowresgameoverscreen);
                        Toast.makeText(getApplicationContext(), "You saved them.  You Win!", Toast.LENGTH_LONG).show();
                    } else {
                        hangmanImage.setImageResource(R.drawable.lowresgameoverscreen);
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

        help.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //switch true
                if (isChecked) {
                    playerGuessEditTextDebugging.setVisibility(View.VISIBLE);


                } else {
                    playerGuessEditTextDebugging.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private boolean checkPlayersGuess() {
        String tempGuess = playerGuessEditText.getText().toString();
        boolean realGuess;

        if (tempGuess.equals("")) {realGuess = false;} else {realGuess = true;}

        if (realGuess == true)
        {
            lettersGuessedTextView.setText(lettersGuessed);
            char tempChar = tempGuess.charAt(0);
            if (this.currentWordRomaji.contains(tempGuess)) {
                editDisplayedToBeGuessed(tempChar);
                editLettersGuessed(tempChar);
                lettersGuessedTextView.setText("Letters guessed: " + this.lettersGuessed);
                Toast.makeText(getApplicationContext(), tempGuess + " was within the word.", Toast.LENGTH_SHORT).show();
            } else if (!this.currentWordRomaji.contains(tempGuess)) {
                this.wrongGuesses = this.wrongGuesses + 1;
                setCorrectImage();
                editLettersGuessed(tempChar);
                lettersGuessedTextView.setText("Letters guessed: " + this.lettersGuessed);
                Toast.makeText(getApplicationContext(), tempGuess + " is NOT within the word.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            this.wrongGuesses = this.wrongGuesses + 1;
            setCorrectImage();
            Toast.makeText(getApplicationContext(),"You must enter a letter.", Toast.LENGTH_SHORT).show();
        }
        playerGuessEditText.setText("");
        if (this.wrongGuesses == MAX_GUESSES || checkIfSolved())
        {
            return true;
        }
        return false;
    }
    //------------------------------------------------------------------------------------------------------------
    private void editLettersGuessed(char guess)
    {
        String tempString;
        boolean inTheList = false;
        for (int i = 0; i < lettersGuessed.length();i++)
        {

            if (lettersGuessed.charAt(i) == guess)
            {
               inTheList = true;
            }
        }
        if (inTheList == false)
        {
            lettersGuessed.append(guess);
        }
        tempString = lettersGuessed.toString();
        char[] array = tempString.replaceAll("\\s+", "").toLowerCase().toCharArray();
        Arrays.sort(array);
        lettersGuessed.delete(0, lettersGuessed.length());
        lettersGuessed.append(array);

    }
    //------------------------------------------------------------------------------------------------------------
    private void setCorrectImage()
    {
        switch (this.wrongGuesses)
        {
            case 1:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang1);
                break;
            }
            case 2:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang2);
                break;
            }
            case 3:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang3);
                break;
            }
            case 4:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang4);
                break;
            }
            case 5:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang5);
                break;
            }
            case 6:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang6);
                break;
            }
            case 7:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang7);
                break;
            }
            default:
            {
                this.hangmanImage.setImageResource(R.drawable.lowreshang0);
                break;
            }

        }
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
        this.hangmanImage.setImageResource(R.drawable.lowreshang0);
        this.currentWordRomaji="";
        this.lettersGuessed.delete(0,lettersGuessed.length());
        this.wordToBeGuessed.delete(0, this.wordToBeGuessed.length());
        lettersGuessedTextView.setText("Letters guessed: " + this.lettersGuessed);
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
        wordToBeGuessedJapanese.setText(this.currentWordJapanese);
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
        this.currentWordJapanese = getCorrectJapaneseString();
       // Toast.makeText(getApplicationContext(), currentWord.getRomaji()+" "+randomNum,Toast.LENGTH_SHORT).show();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private String getCorrectJapaneseString()
    {
       if(!this.currentWord.getKanji().equals(""))
       {
           return this.currentWord.getKanji();
       }
        else if(!this.currentWord.getKana().equals(""))
       {
           return this.currentWord.getKana();
       }
       else
       {
           return this.currentWord.getRomaji();
       }
    }
   //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setUpWordBank()
    {
        Word newWord = new Word("Japan","nihon","にほん","日本"); wordBank.add(newWord);
        newWord = new Word("dog","inu","いぬ","犬"); wordBank.add(newWord);
        newWord = new Word("crab","kani","かに","蟹"); wordBank.add(newWord);
        newWord = new Word("Doctor","isha","いしゃ","医者"); wordBank.add(newWord);
        newWord = new Word("Disneyland","disneyland","ディズニーランド",""); wordBank.add(newWord);
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

