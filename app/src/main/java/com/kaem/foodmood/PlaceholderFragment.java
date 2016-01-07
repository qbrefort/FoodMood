package com.kaem.foodmood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vdurmont.emoji.EmojiParser;

/**
 * Created by Administrateur on 05/01/2016.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    OnSendFeelStatusListener mCallback;

    private View rootView;

    private Button buttonSendFeel = null;
    private TextView textViewWelcome = null;
    private TextView TextViewFoodPick;
    private TextView TextViewFoodPick1;
    private TextView TextViewFoodPick2;
    private TextView TextViewFoodPick3;
    private String sName = "";

    private CharSequence sex = "";
    private CharSequence name = "";

    private RadioGroup RadioGroupSex;
    private RadioButton radioSexButton;
    private EditText editTextName;

    private String delicious_smiley = " \uD83D\uDE0B";


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    public void addListenerOnRadioGroup() {

        RadioGroupSex = (RadioGroup) rootView.findViewById(R.id.RadioGroupSex);

        RadioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // get selected radio button from radioGroup
                int selectedId = RadioGroupSex.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) rootView.findViewById(selectedId);
                sex = radioSexButton.getText();
            }

        });

    }

    public void addListenerOnTextViewFoodPick(){
        TextViewFoodPick = (TextView) rootView.findViewById(R.id.TextViewFoodPick);
        TextViewFoodPick1 = (TextView) rootView.findViewById(R.id.TextViewFoodPick1);
        TextViewFoodPick2 = (TextView) rootView.findViewById(R.id.TextViewFoodPick2);
        TextViewFoodPick3 = (TextView) rootView.findViewById(R.id.TextViewFoodPick3);

        Resources res = getResources();
        String[] food_emoji = res.getStringArray(R.array.food_array);
        String[] food_emoji_selected = new String[3];

        Random rand = new Random();
        int randTab[] = new int[3];
        for(int i=0;i<3;i++){
            randTab[i] = rand.nextInt((food_emoji.length+1));
            food_emoji_selected[i] = EmojiParser.parseToUnicode(food_emoji[randTab[i]]);

        }

        TextViewFoodPick1.append(food_emoji_selected[0]);
        TextViewFoodPick2.append(food_emoji_selected[1]);
        TextViewFoodPick3.append(food_emoji_selected[2]);


        Animator foodAnim1 = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation);
        foodAnim1.setTarget(TextViewFoodPick1);
        Animator foodAnim1out = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation_out);
        foodAnim1out.setTarget(TextViewFoodPick1);
        Animator foodAnim2 = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation);
        foodAnim2.setTarget(TextViewFoodPick2);
        Animator foodAnim2out = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation_out);
        foodAnim2out.setTarget(TextViewFoodPick2);
        Animator foodAnim3 = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation);
        foodAnim3.setTarget(TextViewFoodPick3);
        Animator foodAnim3out = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation_out);
        foodAnim3out.setTarget(TextViewFoodPick3);
        AnimatorSet s_in = new AnimatorSet();
        s_in.playTogether(foodAnim1, foodAnim2, foodAnim3);
        AnimatorSet s_out = new AnimatorSet();
        //s_out.playTogether(foodAnim1out, foodAnim2out, foodAnim3out);

        AnimatorSet s1 = new AnimatorSet();
        s1.playSequentially(foodAnim1,foodAnim1out);
        s1.start();

        AnimatorSet s2 = new AnimatorSet();
        s2.playSequentially(foodAnim2,foodAnim2out);
        s2.start();

        AnimatorSet s3 = new AnimatorSet();
        s3.playSequentially(foodAnim3,foodAnim3out);
        s3.start();






    }

    public void addListenerOnEditTextName(){

        editTextName = (EditText) rootView.findViewById(R.id.editTextName);

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                name = editTextName.getText();
                if (name=="")
                    textViewWelcome.setText("Welcome !"+name+delicious_smiley);
                else {
                    String str = "Welcome back " + name + " :yum:";
                    str = EmojiParser.parseToUnicode(str);
                    textViewWelcome.setText(str);
                }
            }
        });


    }



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
        rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        buttonSendFeel=(Button) rootView.findViewById(R.id.buttonSendFeel);

        buttonSendFeel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.OnSendFeelStatus();
            }
        });

        textViewWelcome=(TextView) rootView.findViewById(R.id.textViewWelcome);

        textViewWelcome.setText("Welcome" + sName + delicious_smiley);

        addListenerOnRadioGroup();
        addListenerOnEditTextName();
        addListenerOnTextViewFoodPick();





        return rootView;
    }
}
