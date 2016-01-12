package com.kaem.foodmood;

/**
 * Created by qbr on 12/01/16.
 */
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter extends BaseAdapter{
    String [] nameFoodList;
    String [] kcalList;
    String [] protList;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Activity mainActivity, String[] prgmNameList ,String[] prgmkcalList,String[] prgmprotList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        nameFoodList=prgmNameList;
        kcalList=prgmkcalList;
        protList=prgmprotList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return nameFoodList.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        TextView tvkcal;
        TextView tvprot;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.food_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.tvkcal=(TextView) rowView.findViewById(R.id.textViewC1);
        holder.tvprot=(TextView) rowView.findViewById(R.id.textViewC2);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(nameFoodList[position]);
        holder.tvkcal.setText(kcalList[position]);
        holder.tvprot.setText(protList[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+nameFoodList[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}