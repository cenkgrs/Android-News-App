package com.example.haberuygulamas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="mystring";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_choose_newspaper);

        CardView card1 = (CardView) findViewById(R.id.spor_card);
        CardView card2 = (CardView) findViewById(R.id.eco_card);
        CardView card3 = (CardView) findViewById(R.id.world_card);
        CardView card4 = (CardView) findViewById(R.id.tech_card);
        CardView card5 = (CardView) findViewById(R.id.cevre_card);
        CardView card6 = (CardView) findViewById(R.id.mgzn_card);

        card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                Bundle datas = new Bundle();
                datas.putString(EXTRA_MESSAGE, "En Son Spor Haberleri");

                intn.putExtras(datas);
                startActivity(intn);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                Bundle datas = new Bundle();
                datas.putString(EXTRA_MESSAGE, "En Son Ekonomi Haberleri");

                intn.putExtras(datas);
                startActivity(intn);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                Bundle datas = new Bundle();
                datas.putString(EXTRA_MESSAGE, "En Son Dünya Haberleri");

                intn.putExtras(datas);
                startActivity(intn);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                Bundle datas = new Bundle();
                datas.putString(EXTRA_MESSAGE, "En Son Teknoloji Haberleri");

                intn.putExtras(datas);
                startActivity(intn);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                Bundle datas = new Bundle();
                datas.putString(EXTRA_MESSAGE, "En Son Çevre Haberleri");

                intn.putExtras(datas);
                startActivity(intn);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                Bundle datas = new Bundle();
                datas.putString(EXTRA_MESSAGE, "En Son Magazin Haberleri");

                intn.putExtras(datas);
                startActivity(intn);
            }
        });


    }

    public void choose_card(){

    }

    public void fill(){

    }
}