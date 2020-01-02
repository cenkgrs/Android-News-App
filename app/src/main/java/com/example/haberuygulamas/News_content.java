package com.example.haberuygulamas;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import static android.view.View.GONE;

public class News_content extends AppCompatActivity {

    private Button urlButton;
    private Button shareButton;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.navigation);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });




        Intent i = getIntent();

        final news_item new_item = (news_item)i.getSerializableExtra("MyObject");

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
        if (!TextUtils.isEmpty(new_item.getImage())) {
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

        urlButton = (Button) findViewById(R.id.content_url);
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(new_item.getUrl()));

                startActivity(browserIntent);
            }
        });

        shareButton = (Button) findViewById(R.id.share_url);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                Uri link = Uri.parse(new_item.getUrl());
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Important News ");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(" "+ link));
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Share the News"));


            }
        });

    }





    public void showNotification(){
        String title = getResources().getString(R.string.notification_title);
        String content = getResources().getString(R.string.notification_content);

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel =
                    new NotificationChannel("my_channel", "News App",
                            NotificationManager.IMPORTANCE_LOW);
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);

            mNotificationManager.createNotificationChannel(notificationChannel);



        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.app_logo_1_foreground)
                .setContentTitle("Haberlerim")
                .setContentText("Yeni gelen haberleri incelediniz mi ?");

        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.mipmap.app_logo_1_foreground)
                .setChannelId("my_channel")
                .build();

        Intent notificationIntent = new Intent(this,NewsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent, 0);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        builder.setContentIntent(contentIntent);

        manager.notify(0,notification);
    }

    @Override
    protected void onDestroy () {
        super .onDestroy(); ;
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.O) {
            showNotification();
        }
    }






}
