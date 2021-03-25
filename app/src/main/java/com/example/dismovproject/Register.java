package com.example.dismovproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {
    EditText usernameRegisterField, emailRegisterField, passwordRegisterField, confirmPasswordRegisterField, editTextDate;
    Button buttonRegister;
    FirebaseFirestore fStore;

    //Data variables that I will use



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    //Fullscreen
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Username, passwords and email fields
        usernameRegisterField = findViewById(R.id.usernameRegister);
        emailRegisterField = findViewById(R.id.emailRegister);
        passwordRegisterField = findViewById(R.id.passwordRegister);
        confirmPasswordRegisterField = findViewById(R.id.confirmPasswordRegister);
        editTextDate = findViewById(R.id.editTextDate);

        //Register button
        buttonRegister = findViewById(R.id.buttonRegister);

        //Click listener of register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameRegisterField.getText().toString();
                String mail = emailRegisterField.getText().toString().trim();
                String psswd = passwordRegisterField.getText().toString().trim();
                String conpsswd = confirmPasswordRegisterField.getText().toString().trim();
                System.out.println(userName + " " + mail);
            }
        });

    }
}