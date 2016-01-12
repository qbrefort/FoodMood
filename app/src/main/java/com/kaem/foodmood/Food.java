package com.kaem.foodmood;
import java.lang.String;
import java.util.List;

/**
 * Created by Quentin on 28/12/2015.
 */
public class Food {

    private double magnesium;
    private double kcal;
    private double protein;
    private double fer;
    private double vit_c;
    private double vit_a;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public void setFer(double fer) {
        this.fer = fer;
    }

    public void setVit_c(double vit_c) {
        this.vit_c = vit_c;
    }

    public void setVit_a(double vit_a) {
        this.vit_a = vit_a;
    }




    public void setProtein(double protein) {
        this.protein = protein;
    }



    public Food() {
        this.name = "FoodName";
        this.fer = 0;
        this.magnesium = 0;
        this.vit_a = 0;
        this.vit_c = 0;
    }
    public Food(String name) {
        this.name = name;
        this.fer = 0;
        this.magnesium = 0;
        this.vit_a = 0;
        this.vit_c = 0;
    }

    public Food(List<String[]> list_input){
        for(String[] temp : list_input) {
            this.setName(temp[1]);
            this.setProtein(Double.parseDouble(temp[4]));
            this.setFer(Double.parseDouble(temp[17]));
            this.setKcal(Double.parseDouble(temp[3]));
            this.setMagnesium(Integer.parseInt(temp[12]));
            this.setVit_a(Double.parseDouble(temp[32]));
            this.setVit_c(Double.parseDouble(temp[20]));
        }
    }
    public Food(String[] list_input){
        this.setName(list_input[1]);
        this.setKcal(Double.parseDouble(list_input[3]));
        this.setProtein(Double.parseDouble(list_input[4]));
        this.setFer(Double.parseDouble(list_input[17]));
        this.setMagnesium(Integer.parseInt(list_input[12]));
        this.setVit_a(Double.parseDouble(list_input[32]));
        this.setVit_c(Double.parseDouble(list_input[20]));
    }


    public Food(String name,double fer,double magnesium, double vit_a, double vit_c) {
        this.name = name;
        this.fer = fer;
        this.magnesium =magnesium;
        this.vit_a = vit_a;
        this.vit_c = vit_c;
    }

    public double getFer() {
        return fer;
    }

    public double getVit_c() {
        return vit_c;
    }

    public double getVit_a() {
        return vit_a;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public double getProtein() {return protein;}

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }

    public double getCarac(String carac) {
        if (carac.equals("magnesium")) {return this.magnesium;}
        if (carac.equals("protein")) {return this.protein;}
        if (carac.equals("kcal")) {System.out.println("CALLLLLLLLLLLLLLLLLL");return this.kcal;}
        if (carac.equals("fer")) {return this.fer;}
        if (carac.equals("fer")) {return this.fer;}
        if (carac.equals("vit_a")) {return this.vit_a;}
        if (carac.equals("vit_c")) {return this.vit_c;}
        else return -1;
    }
}
