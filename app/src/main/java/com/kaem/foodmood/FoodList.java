package com.kaem.foodmood;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 28/12/2015.
 */
public class FoodList extends Food {

    public List<Food> getFood_list() {
        return food_list;
    }

    public void setFood_list(List<Food> food_list) {
        this.food_list = food_list;
    }

    private List <Food> food_list;

    public FoodList() {
        this.food_list = new ArrayList();
    }

    public FoodList(List <Food> listFood) {
        this.food_list = listFood;
    }

    int partition_increasing_order(double arr[], int left, int right) {
        int i = left, j = right;
        double tmp;
        Food tmpFood;
        double pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];tmpFood = this.food_list.get(i);
                arr[i] = arr[j];this.food_list.set(i, this.food_list.get(j));
                arr[j] = tmp;this.food_list.set(j,tmpFood);
                i++;
                j--;
            }
        }
        return i;
    }
    int partition_decreasing_order(double arr[], int left, int right) {
        int i = left, j = right;
        double tmp;
        Food tmpFood;
        double pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] > pivot)
                i++;
            while (arr[j] < pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];tmpFood = this.food_list.get(i);
                arr[i] = arr[j];this.food_list.set(i, this.food_list.get(j));
                arr[j] = tmp;this.food_list.set(j,tmpFood);
                i++;
                j--;
            }
        }
        return i;
    }

    void quickSort_decreasing_order(double arr[], int left, int right) {
        int index = partition_decreasing_order(arr, left, right);
        if (left < index - 1)
            quickSort_decreasing_order(arr, left, index - 1);
        if (index < right)
            quickSort_decreasing_order(arr, index, right);
    }

    void quickSort_increasing_order(double arr[], int left, int right) {
        int index = partition_increasing_order(arr, left, right);
        if (left < index - 1)
            quickSort_increasing_order(arr, left, index - 1);
        if (index < right)
            quickSort_increasing_order(arr, index, right);
    }

    public void sort_by(String carac) {
        double[] tsorted = new double[food_list.size()];

        switch (carac) {
            case "Energy":
                tsorted=this.get_kcal_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Protein":
                tsorted=this.get_protein_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Lipid":
                tsorted=this.get_lip_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Fiber":
                tsorted=this.get_fib_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Sugar":
                tsorted=this.get_sug_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Iron":
                tsorted=this.get_iron_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Magnesium":
                tsorted=this.get_mag_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Calcium":
                tsorted=this.get_cal_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Vit A":
                tsorted=this.get_vita_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Vit C":
                tsorted=this.get_vitc_in_double();quickSort_decreasing_order(tsorted, 0, this.food_list.size() - 1);
                break;
            case "Don't order":
                tsorted=this.get_id_in_int();quickSort_increasing_order(tsorted,0,this.food_list.size()-1);
                break;
            case "Best overall":
                sort_best_carac_overall();
            default:
                //Toast.makeText(getActivity(), (String) data.result,Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void sort_best_carac_overall(){
        double[] tsorted = new double[food_list.size()];

        for(int i=0;i<this.getFood_list().size();i++){
            tsorted[i] = //-  this.getFood_list().get(i).getKcal()/2000 +
                    this.getFood_list().get(i).getProtein()/50 +
 //                   this.getFood_list().get(i).getLip() +
 //                   this.getFood_list().get(i).getFib() +
                    this.getFood_list().get(i).getIron()/14 +
                    this.getFood_list().get(i).getSug()/90 +
                    this.getFood_list().get(i).getCal()/800 +
                    this.getFood_list().get(i).getMag()/375 +
                    //this.getFood_list().get(i).getVit_a() +
                    this.getFood_list().get(i).getVit_c()/80;
        }
        quickSort_decreasing_order(tsorted,0,this.food_list.size()-1);
    }

    public String[] get_name(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getName();
        }
        return res;
    }

    public void find_name(String food_name){
        String[] res = new String[this.food_list.size()];

        FoodList res_food_List = new FoodList();

        food_name ="(?i).*"+food_name+".*";

        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getName();
            if(res[i].matches(food_name)){
                res_food_List.add_to_list(this.food_list.get(i));
            }
        }
        this.food_list = res_food_List.food_list;
    }

    public void remove_by_name(String food_name){
        String[] res = new String[this.food_list.size()];

        FoodList res_food_List = new FoodList();

        food_name ="(?i).*"+food_name+".*";

        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getName();
            if(!res[i].matches(food_name)){
                res_food_List.add_to_list(this.food_list.get(i));
            }
        }
        this.food_list = res_food_List.food_list;
    }

    public String[] get_kcal_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            double d=this.food_list.get(i).getKcal();
            int dd = (int) d;
            res[i] = Integer.toString(dd);
        }
        return res;
    }

    public String[] get_lip_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getLip());
        }
        return res;
    }

    public String[] get_fib_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getFib());
        }
        return res;
    }

    public String[] get_sug_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getSug());
        }
        return res;
    }

    public String[] get_cal_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Integer.toString( (int) this.food_list.get(i).getCal());
        }
        return res;
    }

    public String[] get_iron_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getIron());
        }
        return res;
    }

    public String[] get_mag_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getMag());
        }
        return res;
    }
    public String[] get_vita_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Integer.toString((int) this.food_list.get(i).getVit_a());
        }
        return res;
    }

    public String[] get_vitc_in_String(){
        String[] res = new String[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = Double.toString(this.food_list.get(i).getVit_c());
        }
        return res;
    }

    public double[] get_kcal_in_double(){
        double[] res = new double[this.food_list.size()];
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

    public double[] get_id_in_int(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getId();
        }
        return res;
    }

    public double[] get_protein_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getProtein();
        }
        return res;
    }

    public double[] get_lip_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getLip();
        }
        return res;
    }
    public double[] get_fib_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getFib();
        }
        return res;
    }
    public double[] get_sug_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getSug();
        }
        return res;
    }
    public double[] get_cal_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getCal();
        }
        return res;
    }
    public double[] get_iron_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getIron();
        }
        return res;
    }
    public double[] get_mag_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getMag();
        }
        return res;
    }
    public double[] get_vita_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getVit_a();
        }
        return res;
    }
    public double[] get_vitc_in_double(){
        double[] res = new double[this.food_list.size()];
        for(int i=0; i<this.food_list.size() ; i++){
            res[i] = this.food_list.get(i).getVit_c();
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
        double max_carac = 0.0;
        for(Food temp_food : this.food_list){
            if(max_carac < temp_food.getCarac(carac)) {
                max_carac = temp_food.getCarac(carac);
                food = temp_food;
            }
        }
        return food;
    }

    public void add_to_list(Food food){
        this.food_list.add(food);
    }
}
