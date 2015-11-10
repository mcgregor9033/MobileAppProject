package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VocabListSelectionActivity extends Activity {

    private Button getVocabListButton;
    public RadioGroup vocabListRadioGroup;
    private RadioButton adverbRadioButton, adjectivesRadioButton,
                        animalsRadioButton, countriesRadioButton,
                        occupationsRadioButton, greetingsRadioButton,
                        languagesRadioButton, pronounsRadioButton,
                        placesRadioButton,
                        verbsRadioButton,defaultRB;
    private String listChosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_list_selection);
        getVocabListButton= (Button) findViewById(R.id.getListButton);
        adverbRadioButton = (RadioButton) findViewById(R.id.adverb_radio_button);
        greetingsRadioButton = (RadioButton) findViewById(R.id.greetings_radio_button);
        adjectivesRadioButton = (RadioButton) findViewById(R.id.adjectives_radio_button);
        animalsRadioButton = (RadioButton) findViewById(R.id.animals_radio_button);
        countriesRadioButton = (RadioButton) findViewById(R.id.countries_radio_button);
        languagesRadioButton = (RadioButton) findViewById(R.id.languages_radio_button);
        occupationsRadioButton = (RadioButton) findViewById(R.id.occupations_radio_button);
        placesRadioButton = (RadioButton) findViewById(R.id.places_radio_button);
        pronounsRadioButton = (RadioButton) findViewById(R.id.pronouns_radio_button);
        verbsRadioButton = (RadioButton) findViewById(R.id.verbs_radio_button);
        vocabListRadioGroup = (RadioGroup) findViewById(R.id.vocab_lists_radio_button_group);
        vocabListRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.adjectives_radio_button:{
                        Toast.makeText(getApplicationContext(), "Adjectives selected",Toast.LENGTH_SHORT).show();
                        listChosen = "adjectives";
                        break;
                    }
                    case R.id.adverb_radio_button:{
                        Toast.makeText(getApplicationContext(), "Adverbs selected",Toast.LENGTH_SHORT).show();
                        listChosen = "adverbs";
                        break;
                    }
                    case R.id.animals_radio_button:{
                        Toast.makeText(getApplicationContext(), "Animals selected",Toast.LENGTH_SHORT).show();
                        listChosen = "animals";
                        break;
                    }
                    case R.id.greetings_radio_button:{
                        Toast.makeText(getApplicationContext(), "Greetings selected",Toast.LENGTH_SHORT).show();
                        listChosen = "greetings";
                        break;
                    }
                    case R.id.countries_radio_button:{
                        Toast.makeText(getApplicationContext(), "Countries selected",Toast.LENGTH_SHORT).show();
                        listChosen = "countries";
                        break;
                    }
                    case R.id.languages_radio_button:{
                        Toast.makeText(getApplicationContext(), "Languages selected",Toast.LENGTH_SHORT).show();
                        listChosen = "languages";
                        break;
                    }
                    case R.id.occupations_radio_button:{
                        Toast.makeText(getApplicationContext(), "Occupations selected",Toast.LENGTH_SHORT).show();
                        listChosen = "occupations";
                        break;
                    }
                    case R.id.places_radio_button:{
                        Toast.makeText(getApplicationContext(), "Places selected",Toast.LENGTH_SHORT).show();
                        listChosen = "places";
                        break;
                    }
                    case R.id.pronouns_radio_button:{
                        Toast.makeText(getApplicationContext(), "Pronouns selected",Toast.LENGTH_SHORT).show();
                        listChosen = "pronouns";
                        break;
                    }
                    case R.id.verbs_radio_button:{
                        Toast.makeText(getApplicationContext(), "Verbs selected",Toast.LENGTH_SHORT).show();
                        listChosen = "verbs";
                        break;
                    }
                }
            }
        });
        getVocabListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VocabListSelectionActivity.this, VocabActivity.class);
                intent.putExtra("choice",listChosen);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vocab_list_selection, menu);
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
