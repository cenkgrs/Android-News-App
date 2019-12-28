package com.example.haberuygulamas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="mystring";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_choose_newspaper);

        CardView card1 = (CardView) findViewById(R.id.spor_card);
        CardView card2 = (CardView) findViewById(R.id.eco_card);
        CardView card3 = (CardView) findViewById(R.id.science_card);
        CardView card4 = (CardView) findViewById(R.id.tech_card);
        CardView card5 = (CardView) findViewById(R.id.health_card);

        card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                intn.putExtra("id", "1");
                startActivity(intn);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                intn.putExtra("id", "2");

                startActivity(intn);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                intn.putExtra("id", "3");
                startActivity(intn);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                intn.putExtra("id", "4");
                startActivity(intn);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                intn.putExtra("id", "5");
                startActivity(intn);
            }
        });


    }

}
