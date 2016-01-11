package com.kaem.foodmood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 28/12/2015.
 */
public class FoodList extends Food {

    private List <Food> Food_List;

    public FoodList() {
        this.Food_List = new ArrayList<>();
    }

    public void sort_by(){
        //TODO
    }

    public Food get_a_food_by_char(String carac){
        Food best_food = new Food();
        double best_carac=-1;
        for(Food temp_food : this.Food_List){
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
        for(Food temp_food : this.Food_List){
            if (temp_food.getCarac(carac1) + temp_food.getCarac(carac2) > best_carac){
                best_carac = temp_food.getCarac(carac1) + temp_food.getCarac(carac2);
                best_food = temp_food;
            }

        }
        return best_food;

    }

    public void add_to_list(Food food){
        this.Food_List.add(food);
    }
}
