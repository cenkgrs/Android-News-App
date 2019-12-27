package com.example.haberuygulamas;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.view.View.GONE;

public class CustomAdapter extends ArrayAdapter<news_item> {
    private Context context;
    private int layoutResourceId;;

    private ArrayList<news_item> haberList = new ArrayList<news_item>();

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<news_item> haberList) {
        super(context, layoutResourceId, haberList);
        System.out.println("Adapter created");
        this.layoutResourceId = layoutResourceId;
        System.out.println(layoutResourceId);
        this.context = context;
        System.out.println(context);
        this.haberList = haberList;
        System.out.println(haberList);
    }

    public void setListData(ArrayList<news_item> haberList){
        this.haberList = haberList;
        notifyDataSetChanged();
        System.out.println("Got here so far 1");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("Got here so far 2");
        View row = convertView;

        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.title);
            holder.authorTextView = (TextView) row.findViewById(R.id.source);
            holder.timeTextView = (TextView) row.findViewById(R.id.newsDate);
            holder.imageView = (ImageView) row.findViewById(R.id.img);
            holder.descriptionTextView = (TextView) row.findViewById(R.id.desc);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }



        news_item item = haberList.get(position);

        if (!TextUtils.isEmpty(Html.fromHtml(item.getTitle()))) {

            holder.titleTextView.setText(Html.fromHtml(item.getTitle()));

        } else {

            holder.titleTextView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(Html.fromHtml(item.getAuthor()))) {

            holder.authorTextView.setText(Html.fromHtml(item.getAuthor()));

        } else {

            holder.authorTextView.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(Html.fromHtml(item.getTime()))) {

            holder.timeTextView.setText(Html.fromHtml(item.getTime()));

        } else {

            holder.authorTextView.setVisibility(GONE);
        }


        if ((item.getDescription()).toString() != "null") {

            holder.descriptionTextView.setText(Html.fromHtml(item.getDescription()));

        } else {
            holder.descriptionTextView.setVisibility(GONE);

        }

        if ((item.getImage()).toString() != "null" && !TextUtils.isEmpty(item.getImage())) {

            Picasso.get().load(item.getImage()).into(holder.imageView);


        } else {

            holder.imageView.setImageResource(R.drawable.background);
        }



        return row;

    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView descriptionTextView;
        TextView authorTextView;
        TextView timeTextView;

    }

}
