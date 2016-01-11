package com.kaem.foodmood;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Quentin on 28/12/2015.
 */



public class FoodActivity extends Fragment  {

    private View rootView;

    private TextView textViewFoodList;
    private EditText editTextCol;
    private Button buttonSetColumn;

    private int col;

    private List<String[]> foodList;

    public void addListenerTextViewFoodList(){

        textViewFoodList = (TextView) rootView.findViewById(R.id.textViewFoodList);
        buttonSetColumn = (Button) rootView.findViewById(R.id.buttonSetColumn);
        editTextCol = (EditText) rootView.findViewById(R.id.editTextCol);

        col = 1;

        buttonSetColumn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                col = Integer.parseInt(editTextCol.getText().toString());
                String temp_textview = "";
                for (int i=0 ; i<100 ; i++){
                    temp_textview +=foodList.get(i)[col]+" ";
                }
                textViewFoodList.setText(temp_textview);
            }
        });

        foodList = readCsv(getActivity());

        String temp_textview = "";
        for (int i=0 ; i<100 ; i++){
            temp_textview +=foodList.get(i)[col]+" ";
        }
        textViewFoodList.setText(temp_textview);
    }

    public final List<String[]> readCsv(Context context) {
        List<String[]> questionList = new ArrayList<String[]>();
        AssetManager assetManager = context.getAssets();

        try {
            InputStream csvStream = assetManager.open("food_data_csv.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CSVReader csvReader = new CSVReader(csvStreamReader);
            String[] line;

            // throw away the header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                questionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_food, container, false);

        addListenerTextViewFoodList();

        return rootView;


    }

}

