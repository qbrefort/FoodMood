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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText editTextFindBestFood;
    private Button buttonFindBestFood;

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

    public void addListenerSpinner(){
        // Spinner element
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerSortList);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sort_carac = parent.getItemAtPosition(position).toString();

                FoodList bestFoodList = myFoodList;
                bestFoodList.sort_by(sort_carac);
                populateListView(bestFoodList);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Energy");
        categories.add("Protein");
        categories.add("Lipid");
        categories.add("Fiber");
        categories.add("Sugar");
        categories.add("Calcium");
        categories.add("Iron");
        categories.add("Magnesium");
        categories.add("Vit A");
        categories.add("Vit C");
        categories.add("Reset");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void populateListView(FoodList foodList){

        String[] nameList = foodList.get_name();
        String[] kcalList = foodList.get_kcal_in_String();
        String[] protList = foodList.get_protein_in_String();
        String[] lipList = foodList.get_lip_in_String();
        String[] fibList = foodList.get_fib_in_String();
        String[] sugList = foodList.get_sug_in_String();
        String[] calList = foodList.get_cal_in_String();
        String[] ironList = foodList.get_iron_in_String();
        String[] magList = foodList.get_mag_in_String();
        String[] vitaList = foodList.get_vita_in_String();
        String[] vitcList = foodList.get_vitc_in_String();

        int [] prgmImages = new int[1000];
        for(int i=0;i<100;i++){
            prgmImages[i] = R.drawable.french_fries;
        }
        //String [] prgm={"Fries ","Banana ","Broccoli ","Apple "};

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        listView.setAdapter(new CustomAdapter(getActivity(), nameList, kcalList, protList,lipList,fibList,sugList,calList,ironList,magList,vitaList,vitcList,prgmImages));
    }

    public void addListenerTextViewFoodList(){

        textViewFoodList = (TextView) rootView.findViewById(R.id.textViewFoodList);
        buttonFindBestFood = (Button) rootView.findViewById(R.id.buttonFindBestFood);
        editTextFindBestFood = (EditText) rootView.findViewById(R.id.editTextFindBestFood);

        col = 1;

        int nb_displayed = 0;

        buttonFindBestFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sort_carac = editTextFindBestFood.getText().toString();

                if(sort_carac.isEmpty()){
                    populateListView(myFoodList);
                    System.out.println("BLAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                }
                else{
                    FoodList bestFoodList = myFoodList;
                    bestFoodList.sort_by(sort_carac);
                    populateListView(bestFoodList);
                }

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

        for(int i=0;i<1000;i++){
            Food tfl  = new Food(getFood_from_csv(i));
            myFoodList.add_to_list(tfl);
        }

        myFoodList.add_to_list(banane);
        myFoodList.add_to_list(banane2);
        myFoodList.add_to_list(banane3);
        myFoodList.add_to_list(banane4);

//        String[] nameList = csv_to_array();
//        String[] kcalList = csv_to_array(3);
//        String[] protList = csv_to_array(4);



        myFoodList.find_name("butter");



        populateListView(myFoodList);
        addListenerSpinner();

        return rootView;

    }

}

