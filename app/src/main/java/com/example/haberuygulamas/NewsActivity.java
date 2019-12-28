package com.example.haberuygulamas;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private ListView lv;
    private CustomAdapter adp;
    private ArrayList<news_item> haberList;
    private String News_url;
    private ProgressBar newProgBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);



        Intent intent = getIntent();
        String header = intent.getStringExtra("id");
        System.out.println("Header::" +header);
        newProgBar = (ProgressBar) findViewById(R.id.progress_load_photo);
        if(header.equals("1")){
            System.out.println("Sports news");
            News_url = "https://newsapi.org/v2/top-headlines?country=tr&category=sports&apiKey=399bcfeeb90d4b0684d30c177744c525";
        }
        else if(header.equals("2")){
            News_url = "https://newsapi.org/v2/top-headlines?country=tr&category=business&apiKey=399bcfeeb90d4b0684d30c177744c525";
        }
        else if(header.equals("3")){
            News_url = "https://newsapi.org/v2/top-headlines?country=tr&category=science&apiKey=399bcfeeb90d4b0684d30c177744c525";
        }
        else if(header.equals("4")){
            News_url = "https://newsapi.org/v2/top-headlines?country=tr&category=technology&apiKey=399bcfeeb90d4b0684d30c177744c525";
        }
        else if(header.equals("5")){
            News_url = "https://newsapi.org/v2/top-headlines?country=tr&category=health&apiKey=399bcfeeb90d4b0684d30c177744c525";
        }
        else{
            System.out.println("Default news");
            News_url = "https://newsapi.org/v2/top-headlines?country=tr&apiKey=399bcfeeb90d4b0684d30c177744c525";
        }
        //TextView txv = (TextView) findViewById(R.id.news_header);
        //txv.setText(header);

        //News_url = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-11-27&sortBy=publishedAt&apiKey=399bcfeeb90d4b0684d30c177744c525";

        lv = (ListView) findViewById(R.id.news_list_view);


        haberList = new ArrayList<>();



        new AsyncHttpTask().execute(News_url);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL (urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                String response = streamToString(urlConnection.getInputStream());
                parseResult(response);
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // Download complete. Let us update UI

            newProgBar.setVisibility(View.GONE);
        }

    }

    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferesReader = new BufferedReader(new InputStreamReader(stream));
        String data;
        String result = "";

        while ((data = bufferesReader.readLine()) !=null ){

            result += data;
        }
        if(null != stream) {

            stream.close();
        }

        return result;
    }

    private void parseResult(String result){
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("articles");

            news_item item;
            for (int i = 0; i < posts.length(); i++){
                JSONObject post = posts.optJSONObject(i);
                /*JSONArray sources = post.optJSONArray("sources");
                JSONObject source = sources.optJSONObject(0);*/
                String title = post.optString("title");
                String image = post.optString("urlToImage");
                String description = post.optString("description");
                String url = post.optString("url");
                String author = post.optString("author");
                String time = post.optString("publishedAt");
                String content = post.optString("content");

                time = time.substring(0, 10);
                Log.i("Zamanlaması", time);
                //String source_name = source.optString("name");
                item = new news_item();
                item.setTitle(title);
                item.setImage(image);
                item.setUrl(url);
                item.setDescription(description);
                item.setAuthor(author);
                item.setTime(time);
                item.setContent(content);
                Log.i("HABER DESCTPİNTİONU", author);
                //Log.i("SOURCE", source_name);
                haberList.add(item);
                //System.out.println("Data is:" + item.getTitle());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                adp = new CustomAdapter(NewsActivity.this, R.layout.news_item, haberList);
                lv.setAdapter(adp);
                // Stuff that updates the UI

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                news_item item =(news_item) parent.getItemAtPosition(position);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                Log.i("İtem bilgileri", item.getTitle());
                Intent item_intent = new Intent(NewsActivity.this, News_content.class);
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
