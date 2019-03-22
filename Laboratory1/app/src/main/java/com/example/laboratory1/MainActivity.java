package com.example.laboratory1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    Converter converter = new Converter();
    int N = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] names = new String[N];
        for (int i = 0; i < N; i++) {
              names[i] = converter.getNumberInWords(i+1);
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(this, names));
    }

    static class MyAdapter extends BaseAdapter {

        Context context;
        ArrayList names;

        public static class ViewHolder {
            public TextView textView;
        }

        MyAdapter(Context context, String[] list) {
            this.context = context;
            names = new ArrayList<>();
            Collections.addAll(names, list);

        }

        @Override
        public int getCount() {
            return names.size();
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
}
