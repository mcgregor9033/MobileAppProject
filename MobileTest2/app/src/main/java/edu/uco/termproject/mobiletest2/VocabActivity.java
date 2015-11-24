package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class VocabActivity extends Activity implements
        VocabFragment.ListSelectionListener {

    public static String[] mVocabArray;
    public static String[] mKanaDefinitionsArray;
    public static String[] mRomajiDefinitionsArray;
    public static String[] mKanjiDefinitionsArray;

    private final DefinitionFragment mDefinitionFragment = new DefinitionFragment();
    private FragmentManager mFragmentManager;
    private FrameLayout mVocabFrameLayout, mDefinitionsFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "VocabActivity";
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        Intent intent = getIntent();
        String selectionMade = intent.getStringExtra("choice");

        switch (selectionMade) {
            case "adjectives": {

                mVocabArray = getResources().getStringArray(R.array.AdjectivesVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.AdjectivesKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.AdjectivesRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.AdjectivesKanjiDefinitions);
                break;
            }

            case "adverbs": {
                mVocabArray = getResources().getStringArray(R.array.AdverbsVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.AdverbsKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.AdverbsRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.AdverbsKanjiDefinitions);
                break;
            }


            case "animals": {
                mVocabArray = getResources().getStringArray(R.array.AnimalsVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.AnimalsKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.AnimalsRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.AnimalsKanjiDefinitions);
                break;
            }
            case "greetings": {
                mVocabArray = getResources().getStringArray(R.array.GreetingsVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.GreetingsKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.GreetingsRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.GreetingsKanjiDefinitions);
                break;
            }
            case "countries": {
                mVocabArray = getResources().getStringArray(R.array.CountriesVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.CountriesKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.CountriesRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.CountriesKanjiDefinitions);
                break;

            }
            case "languages": {
                mVocabArray = getResources().getStringArray(R.array.LanguagesVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.LanguagesKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.LanguagesRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.LanguagesKanjiDefinitions);
                break;

            }
            case "occupations": {
                mVocabArray = getResources().getStringArray(R.array.OccupationsVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.OccupationsKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.OccupationsRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.OccupationsKanjiDefinitions);
                break;

            }
            case "places": {
                mVocabArray = getResources().getStringArray(R.array.PlacesVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.PlacesKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.PlacesRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.PlacesKanjiDefinitions);
                break;
            }
            case "pronouns": {
                mVocabArray = getResources().getStringArray(R.array.PronounsVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.PronounsKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.PronounsRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.PronounsKanjiDefinitions);
                break;
            }
            case "verbs": {
                mVocabArray = getResources().getStringArray(R.array.VerbsVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.VerbsKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.VerbsRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.VerbsKanjiDefinitions);
                break;
            }
            default: {
                mVocabArray = getResources().getStringArray(R.array.GreetingsVocab);
                mKanaDefinitionsArray = getResources().getStringArray(R.array.GreetingsKanaDefinitions);
                mRomajiDefinitionsArray = getResources().getStringArray(R.array.GreetingsRomajiDefinitions);
                mKanjiDefinitionsArray = getResources().getStringArray(R.array.GreetingsKanjiDefinitions);
                break;
            }
        }
        setContentView(R.layout.activity_vocab);

        mVocabFrameLayout = (FrameLayout) findViewById(R.id.vocab_fragment_container);
        mDefinitionsFrameLayout = (FrameLayout) findViewById(R.id.definition_fragment_container);

        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.vocab_fragment_container,
                new VocabFragment());
        fragmentTransaction.commit();

        mFragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });
    }

    private void setLayout() {
        if (!mDefinitionFragment.isAdded()) {
            mVocabFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT)); // width, height
            mDefinitionsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {
            mVocabFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f)); // width, height, weight
            mDefinitionsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }
    }

    @Override
    public void onListSelection(int index) {
        if (!mDefinitionFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.definition_fragment_container,
                    mDefinitionFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
        if (mDefinitionFragment.getShownIndex() != index) {
            mDefinitionFragment.showIndex(index);
        }
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