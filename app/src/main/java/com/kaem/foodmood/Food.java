package com.kaem.foodmood;
import java.lang.String;
import java.util.List;

/**
 * Created by Quentin on 28/12/2015.
 */
public class Food {

    private double kcal;
    private double protein;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public double getLip() {
        return lip;
    }

    public void setLip(double lip) {
        this.lip = lip;
    }

    private double lip;

    public double getFib() {
        return fib;
    }

    public void setFib(double fib) {
        this.fib = fib;
    }

    private double fib;

    public double getSug() {
        return sug;
    }

    public void setSug(double sug) {
        this.sug = sug;
    }

    private double sug;

    public double getCal() {
        return cal;
    }

    public void setCal(double cal) {
        this.cal = cal;
    }

    private double cal;

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    private double mag;
    private double iron;
    private double vit_c;
    private double vit_a;

    private String name;

    public void setName(String name) {
        this.name = name;
    }


    public void setIron(double iron) {
        this.iron = iron;
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
        this.id = 0;
        this.kcal = 0;
        this.sug = 0;
        this.lip = 0;
        this.fib = 0;
        this.cal = 0;
        this.cal = 0;
        this.iron = 0;
        this.mag = 0;
        this.vit_a = 0;
        this.vit_c = 0;
    }
    public Food(String name) {
        this.name = name;
        this.iron = 0;
        this.mag = 0;
        this.vit_a = 0;
        this.vit_c = 0;
    }

    public Food(String[] list_input){
        this.setId(Integer.parseInt(list_input[0]));
        this.setName(list_input[1]);
        this.setKcal(Integer.parseInt(list_input[3]));
        this.setProtein(Double.parseDouble(list_input[4]));
        this.setLip(Double.parseDouble(list_input[5]));
        this.setFib(Double.parseDouble(list_input[8]));
        this.setSug(Double.parseDouble(list_input[9]));
        this.setSug(Double.parseDouble(list_input[9]));
        this.setCal(Double.parseDouble(list_input[10]));
        this.setIron(Double.parseDouble(list_input[11]));
        this.setMag(Double.parseDouble(list_input[12]));
        this.setVit_a(Double.parseDouble(list_input[32]));
        this.setVit_c(Double.parseDouble(list_input[20]));
    }


    public double getIron() {
        return iron;
    }

    public double getVit_c() {
        return vit_c;
    }

    public double getVit_a() {
        return vit_a;
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
        if (carac.equals("mag")) {return this.mag;}
        if (carac.equals("protein")) {return this.protein;}
        if (carac.equals("kcal")) {;return this.kcal;}
        if (carac.equals("iron")) {return this.iron;}
        if (carac.equals("iron")) {return this.iron;}
        if (carac.equals("vit_a")) {return this.vit_a;}
        if (carac.equals("vit_c")) {return this.vit_c;}
        else return -1;
    }
}
