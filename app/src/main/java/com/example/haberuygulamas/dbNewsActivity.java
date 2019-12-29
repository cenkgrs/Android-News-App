package com.example.haberuygulamas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class dbNewsActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adp;
    private ProgressBar newProgBar;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);


        Intent intn = getIntent();
        String category = intn.getStringExtra("id");
        String newspaper = intn.getStringExtra("newspaper");
        newProgBar = (ProgressBar) findViewById(R.id.progress_load_photo);
        Button doviz_btn = (Button) findViewById(R.id.doviz_btn);

        if(category.equals("1")){
            doviz_btn.setVisibility(View.GONE);
        }
        else if(category.equals("2")){

        }
        else if(category.equals("3")){
            doviz_btn.setVisibility(View.GONE);
        }
        else if(category.equals("4")){
            doviz_btn.setVisibility(View.GONE);
        }
        else if(category.equals("5")){
            doviz_btn.setVisibility(View.GONE);
        }
        else{
            doviz_btn.setVisibility(View.GONE);
        }

        this.listView = (ListView) findViewById(R.id.news_list_view);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        ArrayList<news_item> List = databaseAccess.getQuotes(newspaper);

        //List = databaseAccess.getQuotes(newspaper);
        System.out.println(List);
        databaseAccess.close();

        adp = new CustomAdapter(dbNewsActivity.this, R.layout.news_item, List);
        this.listView.setAdapter(adp);

        newProgBar.setVisibility(View.GONE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                news_item item =(news_item) parent.getItemAtPosition(position);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                Log.i("İtem bilgileri", item.getTitle());
                Intent item_intent = new Intent(dbNewsActivity.this, News_content.class);
                item_intent.putExtra("MyObject", item);
                try{
                    startActivity(item_intent);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
