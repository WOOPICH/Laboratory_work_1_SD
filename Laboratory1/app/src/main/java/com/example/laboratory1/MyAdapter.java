package com.example.laboratory1;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList names;
    private static final int N = 1000000;
    private static Converter converter = new Converter();
    private static String[] list;

    public static class ViewHolder {
        public TextView textView;
    }

    static {
        list = new String[N];
        for (int i = 0; i < N; i++) {
            list[i] = converter.getNumberInWords(N-i);
        }
    }


    MyAdapter(Context context) {
        this.context = context;

        names = new ArrayList<>();
        Collections.addAll(names, list);

    }

    @Override
    public int getCount() {
        return N;
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String str = (String) getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView)convertView.findViewById(R.id.element_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.textView.setText(str);

        if ((position & 1) == 1) {
            convertView.setBackgroundColor(Color.rgb(170, 170, 170));
        } else {
            convertView.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        return convertView;
    }
}
