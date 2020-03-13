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

//import butterknife.ButterKnife;
//import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int SPLASH_TIME_OUT = 4000;

  //  @InjectView(R.id.input_email) EditText  editTextEmail;
  //@InjectView(R.id.input_password) EditText editTextPassword;
   //@InjectView(R.id.btn_login) Button buttonSignup;
   //@InjectView(R.id.link_signup) TextView textViewSignin;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private TextView textViewSignin;


    private ProgressDialog progressDialog;



    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ButterKnife.inject(this);


        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){

            finish();

           Intent intent4 = new Intent(MainActivity.this, StudentActivity.class);
           startActivity(intent4);

            //startActivity(new Intent(getApplicationContext(), StudentActivity.class));
        }


        editTextEmail = (EditText) findViewById(R.id.userid);
        editTextPassword = (EditText) findViewById(R.id.pass);
        textViewSignin = (TextView) findViewById(R.id.signuptext);

        buttonSignup = (Button) findViewById(R.id.signup);

        progressDialog = new ProgressDialog(this);


        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){


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



        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            finish();

                            Intent intent5 = new Intent(MainActivity.this, StudentActivity.class);
                            startActivity(intent5);
                            //startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                        }else{

                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignin){

            Intent intent6 = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent6);


            //startActivity(new Intent(this, LoginActivity.class));
        }


    }
}


