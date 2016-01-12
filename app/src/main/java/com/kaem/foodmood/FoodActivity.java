package com.kaem.foodmood;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Quentin on 28/12/2015.
 */



public class FoodActivity extends Fragment  {

    private View rootView;

    private TextView textViewFoodList;
    private EditText editTextCol;
    private Button buttonSetColumn;

    private ListView listView;

    private String sort_carac;

    private FoodList myFoodList;



    private int col;

    private List<String[]> foodList;

    public String[] csv_to_array(){
        int nb_displayed = 100;
        String temp_textview = "";
        String [] list = new String[nb_displayed];
        for (int i=0 ; i<nb_displayed ; i++){
            list[i] = foodList.get(i)[1];
        }
        return list;
    }

    public String[] csv_to_array(int col){
        int nb_displayed = 100;
        String temp_textview = "";
        String [] list = new String[nb_displayed];
        for (int i=0 ; i<nb_displayed ; i++){
            list[i] = foodList.get(i)[col];
        }
        return list;
    }

    public String[] getFood_from_csv(int row){
        int nb_displayed = 100;
        String temp_textview = "";
        String [] list = new String[nb_displayed];
        for (int i=0 ; i<foodList.get(0).length ; i++){
            list[i] = foodList.get(row)[i];
        }
        return list;
    }

    public void populateListView(String [] nameList, String[] kcalList, String[] protList){

        int [] prgmImages={R.drawable.french_fries,R.drawable.banana,R.drawable.broccoli,R.drawable.apple};
        //String [] prgm={"Fries ","Banana ","Broccoli ","Apple "};

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(getActivity(), nameList, kcalList, protList,prgmImages));
    }

    public void addListenerTextViewFoodList(){

        textViewFoodList = (TextView) rootView.findViewById(R.id.textViewFoodList);
        buttonSetColumn = (Button) rootView.findViewById(R.id.buttonSetColumn);
        editTextCol = (EditText) rootView.findViewById(R.id.editTextCol);

        col = 1;

        int nb_displayed = 0;

        buttonSetColumn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sort_carac = editTextCol.getText().toString();
                Food bestFood = myFoodList.get_max_of(sort_carac);
                String bestFoodStr = bestFood.getName();
                textViewFoodList.setText(bestFoodStr);
            }
        });

        foodList = readCsv(getActivity());

        String temp_textview = "";
        for (int i=0 ; i<nb_displayed ; i++){
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

        Food banane = new Food(getFood_from_csv(0));
        Food banane2 = new Food(getFood_from_csv(1));
        Food banane3 = new Food(getFood_from_csv(2));
        Food banane4 = new Food(getFood_from_csv(3));


        myFoodList = new FoodList();
        myFoodList.add_to_list(banane);
        myFoodList.add_to_list(banane2);
        myFoodList.add_to_list(banane3);
        myFoodList.add_to_list(banane4);

//        String[] nameList = csv_to_array();
//        String[] kcalList = csv_to_array(3);
//        String[] protList = csv_to_array(4);

        String[] nameList = myFoodList.get_name();
        String[] kcalList = myFoodList.get_kcal_in_String();
        String[] protList = myFoodList.get_protein_in_String();

        myFoodList.find_name("butter");



        populateListView(nameList,kcalList,protList);

        return rootView;

    }

}

