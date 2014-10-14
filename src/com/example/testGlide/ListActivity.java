package com.example.testGlide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity {
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.lv_main);
        NewArrayAdapter adapter = new NewArrayAdapter(this, R.layout.item_list_view);
        adapter.addAll(genRandomHtml(40));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private String[] genRandomHtml(int num) {
        String[] html = new String[num];
        for (int i = 0; i < num; i++) {
            html[i] = MyActivity.HTML[(int) Math.floor(Math.random() * MyActivity.HTML.length)];
        }

        return html;
    }

    private class NewArrayAdapter extends ArrayAdapter<String> {
        NewArrayAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, null);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.tv_text);
            textView.setText(Html.fromHtml(getItem(position), new URLImageParser(textView), null));

            return convertView;
        }
    }
}