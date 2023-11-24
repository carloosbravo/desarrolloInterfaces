package com.mcas2.firstdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.firestore.FirebaseFirestore;
import com.mcas2.firstdesign.database.User;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View alertPassword = LayoutInflater.from(this).inflate(R.layout.dialog_forgot_password, null);


    }

    public void showAlertviewContraseña(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recuperación de contraseña")
                .setMessage("Introduce tu email");

        EditText editTextRecuperarContra = new EditText(this);
        editTextRecuperarContra.setInputType(InputType.TYPE_CLASS_TEXT);
        editTextRecuperarContra.setHint("tuemail@example.com");
        builder.setView(editTextRecuperarContra);

        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción cuando se hace clic en el botón Aceptar
                Toast.makeText(Login.this, "Comprueba tu correo", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción cuando se hace clic en el botón Cancelar
                Toast.makeText(Login.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void openSignUp(View view){
            Intent nIntent = new Intent(Login.this,SignUp.class);
            startActivity(nIntent);
    }

    public void chechkLogIn(View view){
        EditText userLoginEdittext = findViewById(R.id.usernameInputSignUp);
        EditText passwordLoginEdittext = findViewById(R.id.passwordInputLogin);

        String usernameString = userLoginEdittext.getText().toString();
        String passwordString = passwordLoginEdittext.getText().toString();

        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        Map<String, User> users = new HashMap<>();
        firestoreDb.collection("EventUp").document();
    }
}