package com.kaem.foodmood;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Administrateur on 05/01/2016.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    OnSendFeelStatusListener mCallback;

    private Button buttonSendFeel = null;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    public interface OnSendFeelStatusListener {
        void OnSendFeelStatus();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSendFeelStatusListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        buttonSendFeel=(Button) rootView.findViewById(R.id.buttonSendFeel);

        buttonSendFeel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.OnSendFeelStatus();
            }
        });

        listView = (ListView) rootView.findViewById(R.id.sexList);
        String[] sexs = getResources().getStringArray(R.array.sex_array);
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_view_row,R.id.textViewList, sexs);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);



        return rootView;
    }
}
