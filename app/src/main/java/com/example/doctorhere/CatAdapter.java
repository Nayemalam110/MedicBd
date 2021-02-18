package com.example.doctorhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CatAdapter extends BaseAdapter {


    Context context;
    ArrayList<CatClass> arrayList;


    public CatAdapter(Context context, ArrayList<CatClass>arrayList){

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
        view=inflater.inflate(R.layout.list_item,null);
        TextView tid=view.findViewById(R.id.listitemid);
        CatClass catClass = arrayList.get(i);

        tid.setText(catClass.getName());

        return view;
    }
}
