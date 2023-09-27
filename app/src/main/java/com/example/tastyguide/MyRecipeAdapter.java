package com.example.tastyguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.ViewHolder> {



    ///int totalPrice = 0;
    Context context;
    List<MyRecipeModel> cartModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public MyRecipeAdapter(Context context, List<MyRecipeModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyRecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recipe_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecipeAdapter.ViewHolder holder, int position) {

        holder.name.setText(cartModelList.get(position).getFoodName());
        holder.desc.setText(cartModelList.get(position).getFoodDesc());
        holder.date.setText(cartModelList.get(position).getCurrentDate());
        holder.time.setText(cartModelList.get(position).getCurrentTime());
        holder.ing.setText(cartModelList.get(position).getFoodIng());

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToRecipe")
                        .document(cartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    cartModelList.remove(cartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });


    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,desc,ing,time,date;
        ImageView deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_desc);
            date = itemView.findViewById(R.id.current_time);
            time = itemView.findViewById(R.id.current_date);
            ing = itemView.findViewById(R.id.product_ing);
            deleteItem = itemView.findViewById(R.id.delete_cart);

        }
    }
}