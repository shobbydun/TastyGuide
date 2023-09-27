package com.example.tastyguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    EditText  NameIn, ingIn, DescIn;
    Button addToRecipe;
    ImageButton signOut;

    //FloatingActionButton add,logout,recipes;
    //boolean aBoolean = true;


    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_recipe_menu:
                startActivity(new Intent(getApplicationContext(), MyRecipes.class));
                return true;
            case R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), sign_in.class));
                Toast.makeText(MainActivity.this, "Log out successful!", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //signOut = findViewById(R.id.sign_out_img);

        NameIn = findViewById(R.id.name_in);
        ingIn = findViewById(R.id.ing_in);
        DescIn = findViewById(R.id.desc_in);
        addToRecipe = findViewById(R.id.saveR);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

//        add = findViewById(R.id.add_float);
//        recipes = findViewById(R.id.my_recipes_float);
//        logout = findViewById(R.id.log_out_float);

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (aBoolean){
//                    recipes.show();
//                    logout.show();
//                    aBoolean = false;
//                }else{
//                    recipes.hide();
//                    logout.hide();
//                    aBoolean = true;
//                }
//            }
//        });
//        recipes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),MyRecipes.class));
//            }
//        });

        addToRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> data = new HashMap<String,String>();
                data.put("ReciName",NameIn.getText().toString());
                data.put("ReciIng",ingIn.getText().toString());
                data.put("ReciDesc",DescIn.getText().toString());

                CollectionReference myCollection = FirebaseFirestore.getInstance().collection("MyRecipe");
                DocumentReference docRef = myCollection.document("OHHhPhRXo1bAM4ZvQsBo");

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                                .collection("AddToRecipe").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(MainActivity.this,"Recipe Saved",Toast.LENGTH_SHORT).show();
                                //finish();
                            }
                        });

//                docRef.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(MainActivity.this,"Recipe Saved successfully",Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(MainActivity.this,"Recipe Has not been saved.\n" +e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

//        signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(),sign_in.class));
//                Toast.makeText(MainActivity.this,"Log out successful!",Toast.LENGTH_SHORT).show();
//            }
//        });





    }

}