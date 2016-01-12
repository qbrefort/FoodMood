package com.kaem.foodmood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 28/12/2015.
 */
public class FoodList extends Food {

    private List <Food> food_list;

    public FoodList() {
        this.food_list = new ArrayList();
    }

    public FoodList(List <Food> listFood) {
        this.food_list = listFood;
    }

    public void sort_by(){
        //TODO
    }

    public String[] get_name(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getName();
        }
        return res;
    }
    public String[] get_kcal_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getKcal());
        }
        return res;
    }

    public Double[] get_kcal_in_Double(){
        Double[] res = new Double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getKcal();
        }
        return res;
    }

    public String[] get_protein_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getProtein());
        }
        return res;
    }

    public Double[] get_protein_in_Double(){
        Double[] res = new Double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getProtein();
        }
        return res;
    }



    public Food get_a_food_by_char(String carac){
        Food best_food = new Food();
        double best_carac=-1;



        for(Food temp_food : this.food_list){
                if (temp_food.getCarac(carac)> best_carac){
                    best_carac = temp_food.getCarac(carac);
                    best_food = temp_food;
                }

        }
        return best_food;

    }

    public Food get_a_food_by_char(String carac1,String carac2){
        Food best_food = new Food();
        double best_carac=-1;
        for(Food temp_food : this.food_list){
            if (temp_food.getCarac(carac1) + temp_food.getCarac(carac2) > best_carac){
                best_carac = temp_food.getCarac(carac1) + temp_food.getCarac(carac2);
                best_food = temp_food;
            }

        }
        return best_food;
    }

    public Food get_max_of(String carac){
        Food food = new Food();
        Double max_carac = 0.0;
        for(Food temp_food : this.food_list){
            if(max_carac < temp_food.getCarac(carac)) {
                max_carac = temp_food.getCarac(carac);
                food = temp_food;
                System.out.println("ICIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
            }
            else
                System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOON");
        }
        return food;
    }

    public void add_to_list(Food food){
        this.food_list.add(food);
    }
}
