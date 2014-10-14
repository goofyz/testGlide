package com.example.testGlide;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {
    public static final String[] HTML = new String[]{
            "Gif1: <img src='http://www.hkepc.com/forum/images/smilies/default/icon_haha.gif'> <br>",
            "Gif2: <img src='http://i.imgur.com/NeBsSy7.gif'> <br>",
            "Static gif: <img src='http://www.hkepc.com/forum/images/smilies/default/icon77.gif'> <br>"
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((Button) findViewById(R.id.btn_1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        TextView mainTextView = (TextView) findViewById(R.id.tv_main);
        URLImageParser urlImageParser = new URLImageParser(mainTextView);

        mainTextView.setText(Html.fromHtml("Some text. <br> " +
//				"Gif1: <img src='http://www.hkepc.com/forum/images/smilies/default/icon_haha.gif'> <br>" +
//				"Gif2: <img src='http://i.imgur.com/NeBsSy7.gif'> <br>" +
                "Static gif: <img src='http://www.hkepc.com/forum/images/smilies/default/icon77.gif'> <br>" +
                "end", urlImageParser, null));
    }
}
