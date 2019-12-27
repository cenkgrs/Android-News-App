package com.example.haberuygulamas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

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

    String News_url;

    private ListView lv;
    private CustomAdapter adp;
    private ArrayList<news_item> haberList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String header = extras.getString(MainActivity.EXTRA_MESSAGE);
        TextView txv = (TextView) findViewById(R.id.news_header);
        txv.setText(header);

        News_url = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-11-27&sortBy=publishedAt&apiKey=399bcfeeb90d4b0684d30c177744c525";
        lv = (ListView) findViewById(R.id.news_list_view);


        haberList = new ArrayList<>();
        adp = new CustomAdapter(this, R.layout.news_item, haberList);
        lv.setAdapter(adp);


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
                String title = post.optString("title");
                Log.i("title", title);
                String image = post.optString("urlToImage");
                String description = post.optString("description");
                String url = post.optString("url");
                item = new news_item();
                item.setTitle(title);
                item.setImage(image);
                item.setUrl(url);
                item.setDescription(description);

                haberList.add(item);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
