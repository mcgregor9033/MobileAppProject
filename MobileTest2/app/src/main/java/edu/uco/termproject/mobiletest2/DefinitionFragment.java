package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class DefinitionFragment extends Fragment {

    private static final String TAG = "DefinitionsFragment";

    private TextView mKanaView = null;
    private TextView mRomajiView = null;
    private TextView mKanjiView = null;
    private int mCurrIdx = -1;
    private int mKanaArrLen;
    private int mRomajiArrLen;
    private int mKanjiArrLen;
    int getShownIndex() {
        return mCurrIdx;
    }

    void showIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mKanaArrLen)
            return;
        mCurrIdx = newIndex;
        mKanaView.setText("Kana: "+ VocabActivity.mKanaDefinitionsArray[mCurrIdx]);
        mRomajiView.setText("Romaji: "+ VocabActivity.mRomajiDefinitionsArray[mCurrIdx]);
        mKanjiView.setText("Kanji: "+ VocabActivity.mKanjiDefinitionsArray[mCurrIdx]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        return inflater.inflate(R.layout.fragment_definition,
                container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);

        mKanaView = (TextView) getActivity().findViewById(R.id.kanaView);
        mKanaArrLen = VocabActivity.mKanaDefinitionsArray.length;
        mRomajiView = (TextView) getActivity().findViewById(R.id.romajiView);

        mKanjiView = (TextView) getActivity().findViewById(R.id.kanjiView);

    }

}
