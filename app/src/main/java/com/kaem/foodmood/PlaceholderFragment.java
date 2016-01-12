package com.kaem.foodmood;


import java.util.ArrayList;
import java.util.Random;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.opencsv.CSVReader;

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

    private Button buttonSendFeel;
    private Button buttonUpdateProfile;
    private TextView textViewWelcome;
    private TextView TextViewFoodPick;
    private TextView TextViewFoodPick1;
    private TextView TextViewFoodPick2;
    private TextView TextViewFoodPick3;
    private TextView TextViewProfile;
    private String sName = "";

    private CharSequence sex = "";
    private CharSequence name = "";
    private Double size = 0.0;
    private int age = 20;

    private RadioGroup RadioGroupSex;
    private RadioButton radioSexButton;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextSize;

    private String delicious_smiley = " \uD83D\uDE0B";

    private ProfileInfo mydb ;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */


    public void addListenerOnProfile(){

        mydb = new ProfileInfo(getActivity());
        TextViewProfile = (TextView) rootView.findViewById(R.id.TextViewProfile);

        buttonUpdateProfile = (Button) rootView.findViewById(R.id.buttonUpdateProfile);
        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb = new ProfileInfo(getActivity());
                CharSequence text1 = name;
                int duration1 = Toast.LENGTH_SHORT;
                Toast toast2 = Toast.makeText(getActivity(), text1, duration1);
                toast2.show();
                if (name.toString().equals("deleteALL")) {
                    mydb.deleteAllContacts();
                    mydb = new ProfileInfo(getActivity());
                    Context context = getActivity();
                    CharSequence text = "All profile have been deleted";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    if (name.toString().isEmpty() || sex.toString().isEmpty() || size.toString().isEmpty() || Integer.toString(age).isEmpty()) {
                        Context context = getActivity();
                        CharSequence text = "Please fill all required info";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        mydb.insertProfile(name.toString(), sex.toString(), size.toString(), Integer.toString(age));
                        ArrayList<String> rs = mydb.getAllContacts();
                        //String s1 = mydb.getData(1);
                        String stemp = "";
                        for (String s : rs) {
                            stemp += " " + s;
                        }
                        TextViewProfile.setText(stemp);
                    }
                }
            }
        });




    }

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
        for( int i = 0; i<food_emoji.length;i++){
            food_emoji[i] = EmojiParser.parseToUnicode(food_emoji[i]);
        }
        String[] food_emoji_selected = new String[3];

        Random rand = new Random();
        for(int i=0;i<3;i++){
            food_emoji_selected[i] = food_emoji[rand.nextInt(food_emoji.length)];
        }

        //TODO
        //create table of string to declare name of TextView
        //Call each by table_name[i]

        ArrayList<TextView> textViewArray = new ArrayList<>();
        ArrayList<Animator> animatorsArray = new ArrayList<>();

        GridLayout layout = (GridLayout) rootView.findViewById(R.id.gridLayoutFood);

        for(int i=0;i<50;i++){
            TextView tempTextView = new TextView(getActivity());
            textViewArray.add(i, tempTextView);
            textViewArray.get(i).setText(food_emoji[i]);
            textViewArray.get(i).setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            textViewArray.get(i).setGravity(Gravity.LEFT);
            textViewArray.get(i).setTypeface(TextViewFoodPick1.getTypeface());
            textViewArray.get(i).setTextAppearance(getActivity(), R.style.Base_TextAppearance_AppCompat);
            Animator tempfoodAnim1 = AnimatorInflater.loadAnimator(getActivity(),R.animator.fade_ioi);
            animatorsArray.add(i, tempfoodAnim1);
            animatorsArray.get(i).setTarget(textViewArray.get(i));
            animatorsArray.get(i).setStartDelay(i * 50);

            int ii = i;
            Animator tempfoodAnim2 = AnimatorInflater.loadAnimator(getActivity(),R.animator.pop_size_x);
            animatorsArray.add(ii, tempfoodAnim2);
            animatorsArray.get(ii).setTarget(textViewArray.get(i));
            animatorsArray.get(ii).setStartDelay(i * 50);

            int iii = i;
            Animator tempfoodAnim3 = AnimatorInflater.loadAnimator(getActivity(),R.animator.pop_size_y);
            animatorsArray.add(iii, tempfoodAnim3);
            animatorsArray.get(iii).setTarget(textViewArray.get(i));
            animatorsArray.get(iii).setStartDelay(i * 50);

            layout.addView(textViewArray.get(i));
            textViewArray.get(i).setAlpha(0);
            textViewArray.get(i).setAlpha(0);
            textViewArray.get(i).setScaleX(0);
            textViewArray.get(i).setScaleY(0);
        }
        AnimatorSet animsetEmoji = new AnimatorSet();
        animsetEmoji.playTogether(animatorsArray);
        animsetEmoji.start();

        AnimatorSet animsetEmoji2 = new AnimatorSet();
        animsetEmoji2.playTogether(animatorsArray);
        animsetEmoji2.start();

        AnimatorSet animsetEmoji3 = new AnimatorSet();
        animsetEmoji3.playTogether(animatorsArray);
        animsetEmoji3.start();
//        layout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
//        layout.setOrientation(LinearLayout.HORIZONTAL);


        TextViewFoodPick1.append(food_emoji_selected[0]);
        TextViewFoodPick2.append(food_emoji_selected[1]);
        TextViewFoodPick3.append(food_emoji_selected[2]);


        Animator foodAnim1 = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation);
        foodAnim1.setTarget(TextViewFoodPick1);
        Animator foodAnim1out = AnimatorInflater.loadAnimator(getActivity(),R.animator.fade_ioi);
        foodAnim1out.setTarget(TextViewFoodPick1);
        Animator foodAnim2 = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation);
        foodAnim2.setTarget(TextViewFoodPick2);
        Animator foodAnim2out = AnimatorInflater.loadAnimator(getActivity(),R.animator.fade_ioi);
        foodAnim2out.setTarget(TextViewFoodPick2);
        Animator foodAnim3 = AnimatorInflater.loadAnimator(getActivity(),R.animator.my_rotation);
        foodAnim3.setTarget(TextViewFoodPick3);
        Animator foodAnim3out = AnimatorInflater.loadAnimator(getActivity(),R.animator.fade_ioi);
        foodAnim3out.setTarget(TextViewFoodPick3);

//        AnimatorSet s1 = new AnimatorSet();
//        s1.playTogether(foodAnim1, foodAnim1out);
//        s1.start();
//
//        AnimatorSet s2 = new AnimatorSet();
//        s2.playTogether(foodAnim2, foodAnim2out);
//        s2.setStartDelay(200);
//        s2.start();
        TextViewFoodPick1.setScaleY(0);
        TextViewFoodPick1.setScaleX(0);
        TextViewFoodPick2.setScaleX(0);
        TextViewFoodPick2.setScaleY(0);
        TextViewFoodPick3.setScaleX(0);
        TextViewFoodPick3.setScaleY(0);

        Animator foodAnim1pop = AnimatorInflater.loadAnimator(getActivity(),R.animator.pop_size);
        foodAnim1pop.setTarget(TextViewFoodPick1);
  //      foodAnim1pop.start();

        Animator foodAnim2pop = AnimatorInflater.loadAnimator(getActivity(),R.animator.pop_size);
        foodAnim2pop.setTarget(TextViewFoodPick2);
        foodAnim2pop.setStartDelay(200);
//        foodAnim2pop.start();



        Animator foodAnim3pop = AnimatorInflater.loadAnimator(getActivity(),R.animator.pop_size);
        foodAnim3pop.setTarget(TextViewFoodPick3);
        foodAnim3pop.setStartDelay(400);
//        foodAnim3pop.start();

//        AnimatorSet s3 = new AnimatorSet();
//        s3.play(foodAnim3pop);
//        s2.setStartDelay(400);
//        s3.start();






    }

    public void addListenerOnEditTextName(){

        editTextName = (EditText) rootView.findViewById(R.id.editTextName);
        editTextSize = (EditText) rootView.findViewById(R.id.editTextSize);
        editTextAge = (EditText) rootView.findViewById(R.id.editTextAge);



        editTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CharSequence sizes = editTextAge.getText();
                if(sizes != null && sizes.length() > 0) {
                    age = Integer.parseInt(sizes.toString());
                }
            }
        });

        editTextSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CharSequence sizes = editTextSize.getText();
                if(sizes != null && sizes.length() > 0) {
                    size = Double.parseDouble(sizes.toString());
                }
            }
        });

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

        addListenerOnRadioGroup();
        addListenerOnEditTextName();
        addListenerOnTextViewFoodPick();
        addListenerOnProfile();

        if(mydb.getLastProfile()!="")
            textViewWelcome.setText("Welcome back " + mydb.getLastProfile() +" "+ delicious_smiley);
        else
            textViewWelcome.setText("Welcome "+ delicious_smiley);




        return rootView;
    }
}
