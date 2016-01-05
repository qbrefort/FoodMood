package com.kaem.foodmood;
import java.lang.String;

/**
 * Created by Quentin on 28/12/2015.
 */
public class Food {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setMagnesium(int magnesium) {
        this.magnesium = magnesium;
    }

    public void setFer(int fer) {
        this.fer = fer;
    }

    public void setVit_c(int vit_c) {
        this.vit_c = vit_c;
    }

    public void setVit_a(int vit_a) {
        this.vit_a = vit_a;
    }

    private int magnesium;
    private int fer;
    private int vit_c;
    private int vit_a;

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

    public Food(String name,int fer,int magnesium, int vit_a, int vit_c) {
        this.name = name;
        this.fer = fer;
        this.magnesium =magnesium;
        this.vit_a = vit_a;
        this.vit_c = vit_c;
    }

    public int getFer() {
        return fer;
    }

    public int getVit_c() {
        return vit_c;
    }

    public int getVit_a() {
        return vit_a;
    }

    public int getMagnesium() {
        return magnesium;
    }

    public String getName() {
        return name;
    }

    public int getCarac(String carac) {
        if (carac=="magnesium") {return magnesium;}
        if (carac=="fer") {return fer;}
        if (carac=="vit_a") {return vit_a;}
        if (carac=="vit_c") {return vit_c;}
        else return -1;
    }
}
