package com.example.doctorhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HosAdapter extends BaseAdapter {

    private int[] img = {R.drawable.hos2,R.drawable.hos14,R.drawable.loc};
    Context context;
    ArrayList<HosClass> arrayList;
    public HosAdapter(Context context, ArrayList<HosClass>arrayList){

        this.context=context;
        this.arrayList=arrayList;


    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.hoslist,null);
        TextView tid=view.findViewById(R.id.title);
        TextView tad=view.findViewById(R.id.subtitle);
        ImageView imageView=view.findViewById(R.id.icon);
        ImageView imageView2=view.findViewById(R.id.icon2);
        HosClass hosClass = arrayList.get(i);
        tid.setText(hosClass.getName());
        tad.setText(hosClass.getAddress());
        imageView.setImageResource(img[0]);
        imageView2.setImageResource(img[2]);


        return view;
    }
}
