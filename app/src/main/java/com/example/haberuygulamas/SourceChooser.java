package com.example.haberuygulamas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SourceChooser  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.source_chooser);


        CardView card1 = (CardView) findViewById(R.id.news_api);
        CardView card2 = (CardView) findViewById(R.id.guardian);
        CardView card3 = (CardView) findViewById(R.id.bbc);
        CardView card4 = (CardView) findViewById(R.id.cnn);

        card1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(SourceChooser.this, MainActivity.class);
                intn.putExtra("id", "1");
                System.out.println("Clicked");
                startActivity(intn);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(SourceChooser.this, MainActivity.class);
                intn.putExtra("id", "2");

                startActivity(intn);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(SourceChooser.this, MainActivity.class);
                intn.putExtra("id", "3");
                startActivity(intn);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intn = new Intent(SourceChooser.this, MainActivity.class);
                intn.putExtra("id", "4");
                startActivity(intn);
            }
        });
    }
}
