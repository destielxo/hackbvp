package com.example.casino;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener  {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Spinner spinner;
    private String[] SPINNERVALUES = {"GAME1","GAME2","GAME3","PROGRESS GRAPH","INFORMATION TAB"};
    private String SpinnerValue;
    private Button GOTO;
    private Intent intent;

   // private Button buttonLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){

            finish();

            startActivity(new Intent(this, MainActivity.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();


        TextView textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        textViewUserEmail.setText("Welcome "+user.getEmail());

        buttonLogout.setOnClickListener(this);

        spinner =(Spinner)findViewById(R.id.spinner);

        GOTO = (Button)findViewById(R.id.buttonok);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(StudentActivity.this, android.R.layout.simple_list_item_1, SPINNERVALUES);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                SpinnerValue = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        GOTO.setOnClickListener(this);
    }

            @Override
            public void onClick(View view) {
                if(view == buttonLogout){

                    firebaseAuth.signOut();
                    finish();
                    intent = new Intent(StudentActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
                else if(view == GOTO){

                switch(SpinnerValue){

                    case "GAME1":
                        intent = new Intent(StudentActivity.this, Crosswordrules.class);
                        startActivity(intent);
                        break;

                    case "GAME2":
                        intent = new Intent(StudentActivity.this, Jengarules.class);
                        startActivity(intent);
                        break;

                    case "GAME3":
                        intent = new Intent(StudentActivity.this, Spizrules.class);
                        startActivity(intent);
                        break;

                    case "PROGRESS GRAPH":
                        intent = new Intent(StudentActivity.this, Aptituderules.class);
                        startActivity(intent);
                        break;

                    case "INFORMATION TAB":
                        intent = new Intent(StudentActivity.this, infotab.class);
                        startActivity(intent);
                        break;


                } }
            }
        }



