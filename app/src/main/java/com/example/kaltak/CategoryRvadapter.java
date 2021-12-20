package com.example.kaltak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRvadapter extends RecyclerView.Adapter<CategoryRvadapter.Viewholder> {
    private ArrayList<CategoryRvModal> categoryRvModals;
    private Context context;
    private categoryclickinterface categoryclickinterface;

    public CategoryRvadapter(ArrayList<CategoryRvModal> categoryRvModals, Context context, CategoryRvadapter.categoryclickinterface categoryclickinterface) {
        this.categoryRvModals = categoryRvModals;
        this.context = context;
        this.categoryclickinterface = categoryclickinterface;
    }

    @NonNull
    @Override
    public CategoryRvadapter.Viewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_rv_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRvadapter.Viewholder holder, int position) {
        CategoryRvModal categoryRvModal = categoryRvModals.get(position);
        holder.categoryTV.setText(categoryRvModal.getCategory());
        Picasso.get().load(categoryRvModal.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryclickinterface.oncategoryclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRvModals.size();
    }
    public interface categoryclickinterface{
        void oncategoryclick(int position);
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        private ImageView categoryIV;
        private TextView categoryTV;

        public Viewholder(@NonNull  View itemView) {
            super(itemView);
            categoryIV = itemView.findViewById(R.id.idIVcategory);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
        }
    }
}
