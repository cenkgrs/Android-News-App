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

import java.util.ArrayList;

import static android.view.View.GONE;

public class CustomAdapter extends ArrayAdapter<news_item> {
    private Context context;
    private int layoutResourceId;;

    private ArrayList<news_item> haberList = new ArrayList<news_item>();

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<news_item> haberList) {
        super(context, layoutResourceId, haberList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.haberList = haberList;
    }

    public void setListData(ArrayList<news_item> haberList){
        this.haberList = haberList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        CustomAdapter.ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.news_item, parent, false);
            holder = new CustomAdapter.ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.title);
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

        if ((item.getDescription()).toString() != "null") {

            holder.descriptionTextView.setText(Html.fromHtml(item.getDescription()));

        } else {
            holder.descriptionTextView.setVisibility(GONE);

        }

        if ((item.getImage()).toString() != "null" && !TextUtils.isEmpty(item.getImage())) {

            //Picasso.with(context).load(item.getImage()).into(holder.imageView);


        } else {

            holder.imageView.setImageResource(R.drawable.background);
        }



        return row;

    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
        TextView descriptionTextView;

    }

}
