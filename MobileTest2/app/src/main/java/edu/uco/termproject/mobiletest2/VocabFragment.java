package edu.uco.termproject.mobiletest2;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VocabFragment extends ListFragment {
    private static final String TAG = "VocabFragment";
    private ListSelectionListener mListener = null;

    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        getListView().setItemChecked(pos, true);
        mListener.onListSelection(pos);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.word_item, VocabActivity. mVocabArray));
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        try {
            mListener = (ListSelectionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

}