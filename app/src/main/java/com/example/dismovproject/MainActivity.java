package com.example.dismovproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailField, PasswordField;
    TextView ENTER, Register;
    ImageButton infoButton, languageButton;
    private String userEmail = "";
    private  String password = "";
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,    //Fullscreen
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Username and password fields
        emailField = findViewById(R.id.emailFieldLogin);   //Mail field
        PasswordField = findViewById(R.id.PasswordSpace);   //Password field

        //Info and language buttons
        infoButton = findViewById(R.id.infobutton);
        languageButton = findViewById(R.id.languagebutton);

        //Enter and register text views
        ENTER = findViewById(R.id.buttonIngresar);  //Login button
        Register = findViewById(R.id.buttonRegister);

        fAuth = FirebaseAuth.getInstance();

        //Click listener of info and language buttons
        infoButton.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("Información");
            dialog.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam");
            dialog.setCancelable(true);
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        });

        //Click listener of register activity
        Register.setOnClickListener(v -> OpenRegister());

        ENTER.setOnClickListener(v -> {
            userEmail = emailField.getText().toString();
            password = PasswordField.getText().toString();
            if (!userEmail.isEmpty() && !password.isEmpty()) {
                MainActivity.this.loginUser();
            } else {
                Toast.makeText(MainActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OpenRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void loginUser(){
        fAuth.signInWithEmailAndPassword(userEmail, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Login correcto!", Toast.LENGTH_SHORT).show();
                OpenInicio();
                //finish();
            } else{
                Toast.makeText(MainActivity.this, "Comprueba los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OpenInicio() {
        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
    }

}