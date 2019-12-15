package com.example.haberuygulamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        String header = extras.getString(MainActivity.EXTRA_MESSAGE);

        setContentView(R.layout.news_layout);

        TextView txv = (TextView) findViewById(R.id.news_header);
        txv.setText(header);
    }
}
