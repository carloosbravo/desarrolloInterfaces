package com.mcas2.firstdesign;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    public void openSignUp(View view) {
        Intent nIntent = new Intent(Login.this, SignUp.class);
        startActivity(nIntent);
    }

    public void checkLogIn(View view) {

       EditText userLoginEditText = (EditText) findViewById(R.id.usernameInputLoginET);
       EditText passwordLoginEditText = (EditText) findViewById(R.id.passwordInputLoginET);


        String usernameString = userLoginEditText.getText().toString();
        String passwordString = passwordLoginEditText.getText().toString();

        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Referencia al documento del usuario por su nombre de usuario
        db.collection("Usuarios").document(usernameString)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // El documento existe, puedes acceder al campo "username"
                                String usernameFromDatabase = document.getString("username");

                                // Comparar el usernameString con el usernameFromDatabase
                                if (usernameString.equals(usernameFromDatabase)) {
                                    // Los nombres de usuario coinciden
                                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                    Intent nIntent = new Intent(Login.this, Home.class);
                                    startActivity(nIntent);
                                } else {
                                    // Los nombres de usuario no coinciden
                                    Toast.makeText(Login.this, "El usuario o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // El documento no existe
                                Toast.makeText(Login.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Error al obtener el documento
                            Toast.makeText(Login.this, "Error al encontrar el documento", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
