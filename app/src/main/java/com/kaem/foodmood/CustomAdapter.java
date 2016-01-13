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
    String[] lipList ;
    String[] fibList ;
    String[] sugList ;
    String[] calList ;
    String[] ironList ;
    String[] magList ;
    String[] vitaList;
    String[] vitcList;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Activity mainActivity, String[] prgmNameList ,String[] prgmkcalList,String[] prgmprotList,String[] prgmlipList,String[] prgmfibList,String[] prgmsugList,String[] prgmcalList,String[] prgmironList,String[] prgmmagList,String[] prgmvitaList,String[] prgmvitcList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        nameFoodList=prgmNameList;
        kcalList=prgmkcalList;
        protList=prgmprotList;
        lipList=prgmlipList;
        fibList=prgmfibList;
        sugList=prgmsugList;
        calList=prgmcalList;
        ironList=prgmironList;
        magList=prgmmagList;
        vitaList=prgmvitaList;
        vitcList=prgmvitcList;
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
        TextView tvlip;
        TextView tvfib;
        TextView tvsug;
        TextView tvcal;
        TextView tviron;
        TextView tvmag;
        TextView tvvita;
        TextView tvvitc;

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
        holder.tvlip=(TextView) rowView.findViewById(R.id.textViewC3);
        holder.tvfib=(TextView) rowView.findViewById(R.id.textViewC4);
        holder.tvsug=(TextView) rowView.findViewById(R.id.textViewC5);
        holder.tvcal=(TextView) rowView.findViewById(R.id.textViewC6);
        holder.tviron=(TextView) rowView.findViewById(R.id.textViewC7);
        holder.tvmag=(TextView) rowView.findViewById(R.id.textViewC8);
        holder.tvvita=(TextView) rowView.findViewById(R.id.textViewC9);
        holder.tvvitc=(TextView) rowView.findViewById(R.id.textViewC10);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(nameFoodList[position]);
        holder.tvkcal.setText(kcalList[position]);
        holder.tvprot.setText(protList[position]);
        holder.tvlip.setText(lipList[position]);
        holder.tvfib.setText(fibList[position]);
        holder.tvsug.setText(sugList[position]);
        holder.tvcal.setText(calList[position]);
        holder.tviron.setText(ironList[position]);
        holder.tvmag.setText(magList[position]);
        holder.tvvita.setText(vitaList[position]);
        holder.tvvitc.setText(vitcList[position]);
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