package com.example.examencesarquezada;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText txtcorreo, txtcontra;
    Button  login;
    FirebaseAuth firebaseAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtcorreo = (EditText) findViewById(R.id.log_correo);
        txtcontra = (EditText) findViewById(R.id.log_pass);
        login = (Button) findViewById(R.id.ingresar);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        login.setOnClickListener(view -> {
            String mail = txtcorreo.getText().toString();
            String pass = txtcontra.getText().toString();
            if(mail == null || mail.equals("")){
                txtcorreo.setError("Required");
            }else if( pass == null || pass.equals("")){
                txtcontra.setError("Required");
            }else {
                if(mail.matches(emailPattern) || mail.matches(emailPattern2)){
                    firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(
                                    this,
                                    "Bienvenido!",
                                    Toast.LENGTH_SHORT
                            ).show();
                            startActivity(new Intent(this, activity_docente_add.class));
                            finish();
                        }else{
                            Toast.makeText(
                                    this,
                                    "Credenciales incorrectas",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    });
                }else{
                    Toast.makeText(
                            this,
                            "Correo inválido",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}