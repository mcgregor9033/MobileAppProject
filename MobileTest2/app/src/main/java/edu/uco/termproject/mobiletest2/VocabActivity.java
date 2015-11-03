package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class VocabActivity extends Activity implements
        VocabFragment.ListSelectionListener {

    public static String[] mVocabArray;
    public static String[] mDefinitionsArray;

    private final DefinitionFragment mDefinitionFragment = new DefinitionFragment();
    private FragmentManager mFragmentManager;
    private FrameLayout mVocabFrameLayout, mDefinitionsFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "VocabActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVocabArray = getResources().getStringArray(R.array.Vocab);
        mDefinitionsArray = getResources().getStringArray(R.array.Definitions);

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

}