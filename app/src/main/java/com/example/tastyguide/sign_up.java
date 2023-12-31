package com.example.tastyguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity {

    ImageButton signup;
    EditText name,email,password;
    TextView signin;


    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(this);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        signin = findViewById(R.id.signintext);
        name = findViewById(R.id.editname);
        email = findViewById(R.id.email_reg);
        password = findViewById(R.id.pass_edit);
        signup = findViewById(R.id.signup_btn);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up.this, sign_in.class));

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();

                progressBar.setVisibility(View.VISIBLE);
            }

            private void createUser() {
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(sign_up.this, "We need your name please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(sign_up.this, "Fill in your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userPassword)){
                    Toast.makeText(sign_up.this, "Your Password please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userPassword.length()<6){
                    Toast.makeText(sign_up.this, "Password must be higher than 6 words", Toast.LENGTH_SHORT).show();
                    return;
                }
//Creating the user
                auth.createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    UserModel user = new UserModel(userName,userEmail,userPassword);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Admin").child(id).setValue(user);
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(sign_up.this, "Registration Success Welcome🎉", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(sign_up.this, MainActivity.class));
                                    finish();

                                }else{
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(sign_up.this, "Error" +task.getException(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    public void  goToLogin(View view){
        startActivity(new Intent(sign_up.this,sign_in.class));
    }

}