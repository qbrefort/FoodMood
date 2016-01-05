package com.kaem.foodmood;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.ToggleButton;

/**
 * Created by Quentin on 28/12/2015.
 */

public class MoodActivity extends Fragment {

    private TextView tMood1 = null;
    private View rootView;

    private ToggleButton toggleButtonM1 = null;
    private Button buttonSendMoodStatus = null;

    OnSendMoodStatusListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_mood, container, false);

        tMood1=(TextView) rootView.findViewById(R.id.tMood1);
        toggleButtonM1 = (ToggleButton) rootView.findViewById(R.id.toggleButtonM1);
        buttonSendMoodStatus = (Button) rootView.findViewById(R.id.buttonSendMoodStatus);

        toggleButtonM1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {
                    // The toggle is disabled
                }
            }
        });

        buttonSendMoodStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.OnSendMoodStatus(2);
            }
        });





        //tMood1.setText("Text changed from code in mood activity");



        return rootView;
    }

    public interface OnSendMoodStatusListener {
        public void OnSendMoodStatus(int position);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnSendMoodStatusListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}

