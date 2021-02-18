package com.example.doctorhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AmbAdapter extends BaseAdapter {
    private int[] img = {R.drawable.ambf,R.drawable.loc};
    Context context;
    ArrayList<AmbClass> arrayList;
    public AmbAdapter(Context context, ArrayList<AmbClass>arrayList){

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
        AmbClass ambClass = arrayList.get(i);
        tid.setText(ambClass.getName());
        tad.setText(ambClass.getAddress());
        imageView.setImageResource(img[0]);
        imageView2.setImageResource(img[1]);

        return view;
    }
}
