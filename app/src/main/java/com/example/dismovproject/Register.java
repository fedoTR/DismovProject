package com.example.dismovproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText usernameRegisterField, emailRegisterField, passwordRegisterField, confirmPasswordRegisterField, editTextDate;
    Button buttonRegister;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

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

        //Firebase instances
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //Click listener of register button
        buttonRegister.setOnClickListener(v -> {
            String userName = usernameRegisterField.getText().toString();
            String mail = emailRegisterField.getText().toString().trim();
            String psswd = passwordRegisterField.getText().toString().trim();
            String conpsswd = confirmPasswordRegisterField.getText().toString().trim();
            System.out.println(userName + " " + mail);

            if (TextUtils.isEmpty(userName)){
                usernameRegisterField.setError("Ingresa un nombre de usuario!");
                return;
            }

            if(TextUtils.isEmpty(mail)){
                emailRegisterField.setError("Ingresa un correo!");
                return;
            }

            if (TextUtils.isEmpty(psswd)){
                passwordRegisterField.setError("Ingresa una contraseña!");
                return;
            }

            if (TextUtils.isEmpty(conpsswd)){
                confirmPasswordRegisterField.setError("Confirma tu contraseña!");
                return;
            }

            if(psswd.length() < 6){
                passwordRegisterField.setError("La constraseña debe ser de al menos 6 caracteres!");
                return;
            }

            if (!psswd.equals(conpsswd)){
                confirmPasswordRegisterField.setError("Las contraseñas no coinciden!");
                return;
            }

            //Register the user with FIrebase
            fAuth.createUserWithEmailAndPassword(mail, psswd).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Usuario registrado!", Toast.LENGTH_SHORT).show();
                    userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("fullname", userName);
                    user.put("email", mail);

                    documentReference.set(user).addOnSuccessListener(Void -> Log.d(TAG, "User profile created for " + userName)).addOnFailureListener(e -> Log.d(TAG, "Failure" + e.toString()));
                } else{
                    Toast.makeText(Register.this, "Error " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}