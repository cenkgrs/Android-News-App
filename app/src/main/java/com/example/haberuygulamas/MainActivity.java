package com.example.haberuygulamas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="mystring";

    String newspaper = "";
    String activity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_choose_newspaper);

        Intent intent = getIntent();
        String newspaper_id = intent.getStringExtra("id");
        System.out.println("newspaper_id:"+ newspaper_id);

        if(newspaper_id.equals("1")){
            System.out.println("NEWS API");
            activity = "NewsActivity";
            newspaper = "newsapi";
        }
        else if ( newspaper_id.equals("2")){
            System.out.println("guardian ");
             newspaper = "Guardian";
             activity = "dbNewsActivity";
        }
        else if ( newspaper_id.equals("3")){
            System.out.println("bbc");
            newspaper = "bbc";
        }
        else if ( newspaper_id.equals("4")){
            System.out.println("cnn");
            newspaper = "cnn";
        }
        else{
            System.out.println("asd");
            newspaper = "guardian";
        }

        CardView card1 = (CardView) findViewById(R.id.spor_card);
        CardView card2 = (CardView) findViewById(R.id.eco_card);
        CardView card3 = (CardView) findViewById(R.id.science_card);
        CardView card4 = (CardView) findViewById(R.id.tech_card);
        CardView card5 = (CardView) findViewById(R.id.health_card);

        card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( activity == "NewsActivity"){
                    System.out.println("Will open news activity");
                    Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                    intn.putExtra("id", "1");
                    startActivity(intn);
                }
                else {
                    Intent intn = new Intent(MainActivity.this, dbNewsActivity.class);
                    intn.putExtra("id", "1");
                    intn.putExtra("newspaper", newspaper);
                    startActivity(intn);
                }
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( activity == "NewsActivity"){
                    Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                    intn.putExtra("id", "2");
                    startActivity(intn);
                }
                else {
                    Intent intn = new Intent(MainActivity.this, dbNewsActivity.class);
                    intn.putExtra("id", "2");
                    intn.putExtra("newspaper", newspaper);
                    startActivity(intn);
                }
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( activity == "NewsActivity"){
                    Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                    intn.putExtra("id", "3");
                    startActivity(intn);
                }
                else {
                    Intent intn = new Intent(MainActivity.this, dbNewsActivity.class);
                    intn.putExtra("id", "3");
                    intn.putExtra("newspaper", newspaper);
                    startActivity(intn);
                }
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( activity == "NewsActivity"){
                    Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                    intn.putExtra("id", "4");
                    startActivity(intn);
                }
                else {
                    Intent intn = new Intent(MainActivity.this, dbNewsActivity.class);
                    intn.putExtra("id", "4");
                    intn.putExtra("newspaper", newspaper);
                    startActivity(intn);
                }
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if( activity == "NewsActivity"){
                    Intent intn = new Intent(MainActivity.this, NewsActivity.class);
                    intn.putExtra("id", "5");
                    startActivity(intn);
                }
                else {
                    Intent intn = new Intent(MainActivity.this, dbNewsActivity.class);
                    intn.putExtra("id", "5");
                    intn.putExtra("newspaper", newspaper);
                    startActivity(intn);
                }
            }
        });


    }

}
