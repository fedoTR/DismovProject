package com.example.dismovproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

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
        loadLocale();

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

        languageButton.setOnClickListener(v -> showChangeLanguageDialog());

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

    //Change language menu dialog
    public void showChangeLanguageDialog() {
        final String[] listItems = {"English","Español"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Escoge un idioma");
        mBuilder.setSingleChoiceItems(listItems, -1, (dialogInterface, i) -> {
            if(i == 0){
                setLocale("en");
                recreate();
            }else if(i == 1){
                setLocale("es");
                recreate();
            }
            dialogInterface.dismiss();
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    //This set the locales, language
    @SuppressWarnings("deprecation")
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    //This loads the locales, language
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

    //This opens the Register.class
    public void OpenRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    //This allows the user to Sign In
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

    //This opens NavigationDrawer.class
    private void OpenInicio() {
        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
    }

}