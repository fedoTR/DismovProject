package com.example.dismovproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Información");
                dialog.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam");
                dialog.setCancelable(true);
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });

        //Click listener of register activity
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenRegister();
            }
        });
    }

    public void OpenRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}