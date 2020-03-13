package com.example.casino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;


    private FirebaseAuth firebaseAuth;


    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){

            finish();


            Intent intent3 = new Intent(LoginActivity.this, StudentActivity.class);
            startActivity(intent3);
            //startActivity(new Intent(getApplicationContext(), StudentActivity.class));
        }


        editTextEmail = (EditText) findViewById(R.id.userid1);
        editTextPassword = (EditText) findViewById(R.id.pass1);
        buttonSignIn = (Button) findViewById(R.id.signin);
        textViewSignup  = (TextView) findViewById(R.id.textviewsignup);

        progressDialog = new ProgressDialog(this);


        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }


    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }



        progressDialog.setMessage("Logging in Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            finish();

                            Intent intent1 = new Intent(LoginActivity.this, StudentActivity.class);
                            startActivity(intent1);


                           // startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();

            Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent2);
            //startActivity(new Intent(this, MainActivity.class));
        }
    }
}

