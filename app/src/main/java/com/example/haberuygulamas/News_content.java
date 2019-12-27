package com.example.haberuygulamas;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import static android.view.View.GONE;

public class News_content extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.navigation);
        setSupportActionBar(toolbar);

        Intent i = getIntent();

        news_item new_item = (news_item)i.getSerializableExtra("MyObject");

        //Başlık
        TextView titleTextView = (TextView) findViewById(R.id.content_title);
        if (!TextUtils.isEmpty(Html.fromHtml(new_item.getTitle())))
        {
            titleTextView.setText(Html.fromHtml(new_item.getTitle()));
        } else {
            titleTextView.setVisibility(GONE);
        }

        //İçerik
        TextView contentTextView = (TextView) findViewById(R.id.content_desc);
        if (!TextUtils.isEmpty(Html.fromHtml(new_item.getContent())))
        {
            contentTextView.setText(Html.fromHtml(new_item.getContent()));
        } else {
            contentTextView.setVisibility(GONE);
        }

        //Resim
        ImageView imageView = (ImageView) findViewById(R.id.content_image);
        if ((new_item.getImage()).toString() != "null" && !TextUtils.isEmpty(new_item.getImage())) {
            Picasso.get().load(new_item.getImage()).into(imageView);
        } else {
            imageView.setImageResource(R.drawable.background);
        }

        //Tarih
        TextView dateTextView = (TextView) findViewById(R.id.content_date);
        if (!TextUtils.isEmpty(Html.fromHtml(new_item.getTime())))
        {
            dateTextView.setText(Html.fromHtml(new_item.getTime()));
        } else {
            dateTextView.setVisibility(GONE);
        }

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }




}
