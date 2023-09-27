package com.example.tastyguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    Context context;
    ArrayList<myModel> userArrayList;

    public myAdapter(Context context, ArrayList<myModel> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public myAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.view_recipe_item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.myViewHolder holder, int position) {

        myModel user = userArrayList.get(position);

        holder.recipeName.setText(user.name);
        holder.recipeIng.setText(user.ing);
        holder.recipeDesc.setText(user.desc);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView recipeName,recipeIng,recipeDesc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.product_name);
            recipeIng = itemView.findViewById(R.id.product_ing);
            recipeDesc = itemView.findViewById(R.id.product_desc);

        }
    }
}
