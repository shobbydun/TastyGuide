package com.example.tastyguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyRecipes extends AppCompatActivity {


    RecyclerView recyclerView;
    MyRecipeAdapter myAdapter;
    List<MyRecipeModel> myRecipeModelList;
    ProgressDialog progressDialog;
    TextView recipeName,recipeIng,recipeDesc;
    FirebaseAuth auth;
    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_my_recipes);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();



        myRecipeModelList = new ArrayList<MyRecipeModel>();
        myAdapter = new MyRecipeAdapter(MyRecipes.this, myRecipeModelList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();
    }

    CollectionReference myCollection = FirebaseFirestore.getInstance().collection("MyRecipe");
    DocumentReference docRef = myCollection.document("OHHhPhRXo1bAM4ZvQsBo");

    private void EventChangeListener() {
        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToRecipe").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                String documentId = documentSnapshot.getId();

                                MyRecipeModel myModel = documentSnapshot.toObject(MyRecipeModel.class);

                                myModel.setDocumentId(documentId);

                                myRecipeModelList.add(myModel);
                                myAdapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        }
                    }
                });
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                        if(error != null){
//
//                            if (progressDialog.isShowing())
//                                progressDialog.dismiss();
//                            Log.e("FireStore Error!",error.getMessage());
//                            return;
//                        }
//                        for (DocumentChange dc : value.getDocumentChanges()){
//                            if (dc.getType() == DocumentChange.Type.ADDED){
//                                userArrayList.add(dc.getDocument().toObject(myModel.class));
//                            }
//
//                            myAdapter.notifyDataSetChanged();
//                            if (progressDialog.isShowing())
//                                progressDialog.dismiss();
//                        }
//
//                    }
//                });
    }


}


