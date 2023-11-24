package com.mcas2.firstdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mcas2.firstdesign.database.User;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    EditText userEditText;
    EditText emailEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userEditText = (EditText) findViewById(R.id.usernameInputSignUpET);
        emailEditText = (EditText) findViewById(R.id.emailInputSignUpET);
        passwordEditText = (EditText) findViewById(R.id.passwordInputSignUpET);

    }

    public void changeToLogin(View view){
        Intent nIntent = new Intent(SignUp.this,Login.class);
        startActivity(nIntent);
    }
    public void signUpNewUser(View v){

        String usernameString = userEditText.getText().toString();
        String emailString = emailEditText.getText().toString();
        String passwordString = passwordEditText.getText().toString();

        //FirebaseApp.initializeApp(this);
        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        Map<String, User> users = new HashMap<>();
        User u = new User();
        u.username = usernameString;
        u.email = emailString;
        u.password = passwordString;
        users.put(usernameString,u);

        firestoreDb.collection("Usuarios").document()
                .set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent toLobby = new Intent(SignUp.this, Looby.class);
                        toLobby.putExtra("username", usernameString);
                        startActivity(toLobby);
                    }
                });

    }
}