package com.example.kaltak;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Newsrvadapter extends RecyclerView.Adapter<Newsrvadapter.Viewholder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public Newsrvadapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Newsrvadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Newsrvadapter.Viewholder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.titleTV.setText(articles.getTitle());
        holder.subtitleTV.setText(articles.getDescription());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NewsdetailActivity.class);
                intent.putExtra("title",articles.getTitle());
                intent.putExtra("content",articles.getContent());
                intent.putExtra("desc",articles.getDescription());
                intent.putExtra("image",articles.getUrlToImage());
                intent.putExtra("url",articles.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView titleTV,subtitleTV;
        private ImageView newsIV;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVnewsheading);
            subtitleTV = itemView.findViewById(R.id.idTVSubHeading);
            newsIV = itemView.findViewById(R.id.idIvNews);
        }
    }
}
