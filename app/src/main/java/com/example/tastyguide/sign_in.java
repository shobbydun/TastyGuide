package com.example.tastyguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in extends AppCompatActivity {
    ImageButton login;
    EditText email,password;
    TextView register;

    FirebaseAuth auth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);



        auth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.pass_edit_login);
        register = findViewById(R.id.signuptext);

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(sign_in.this, MainActivity.class));
            Toast.makeText(this, "Please Wait You Are Already A member", Toast.LENGTH_SHORT).show();
            finish();

    }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_in.this, sign_up.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
                progressBar.setVisibility(View.GONE);
            }

            private void loginUser() {

                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(sign_in.this, "Fill in your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userPassword)){
                    Toast.makeText(sign_in.this, "Your Password please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userPassword.length()<6){
                    Toast.makeText(sign_in.this, "Password must be higher than 6 words", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Login User
                auth.signInWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(sign_in.this, "Welcome; Login successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(sign_in.this, MainActivity.class));
                                    finish();
                                }else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(sign_in.this, "Error"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}