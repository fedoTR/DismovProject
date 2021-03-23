package com.example.dismovproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText UsernameField, PasswordField;
    TextView ENTER, Register;
    ImageButton infoButton, languageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Username and password fields
        UsernameField = findViewById(R.id.UserNameSpace);
        PasswordField = findViewById(R.id.PasswordSpace);

        //Info and language buttons
        infoButton = findViewById(R.id.infobutton);
        languageButton = findViewById(R.id.languagebutton);

        //Enter and register text views
        ENTER = findViewById(R.id.buttonIngresar);
        Register = findViewById(R.id.buttonRegister);

        //Click listener of info and language buttons
        infoButton.setOnClickListener(v -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
            alertDialogBuilder.setTitle("Información");
            alertDialogBuilder.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam");
        });
    }
}