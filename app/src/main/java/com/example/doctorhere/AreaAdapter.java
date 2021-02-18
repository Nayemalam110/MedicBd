package com.example.doctorhere;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AreaAdapter extends BaseAdapter {

    Context context;
    ArrayList<AreaClass> arrayList;


    public AreaAdapter(Context context, ArrayList<AreaClass>arrayList){

        this.context=context;
        this.arrayList=arrayList;
        //Toast.makeText(context.getApplicationContext(),"AreaAdapter working",Toast.LENGTH_LONG).show();

    }


    @Override
    public int getCount() {
        return this.arrayList.size();
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
        AreaClass areaClass = arrayList.get(i);
        // TextView tname=view.findViewById(R.id.listitem2id);
        //StudenClass studenClass = arrayList.get(i);
        tid.setText(areaClass.getName());
        //tname.setText(studenClass.getName());


        return view;
    }
}