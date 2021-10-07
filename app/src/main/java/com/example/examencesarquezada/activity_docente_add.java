package com.example.examencesarquezada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examencesarquezada.model.docente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class activity_docente_add extends AppCompatActivity {
    EditText nom, cod, denei, tele, corre;
    ListView listardocentes;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button volver,agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_add);

        nom = findViewById(R.id.agre_nombre);
        cod = findViewById(R.id.agre_codigo);
        denei = findViewById(R.id.agre_dni);
        tele = findViewById(R.id.agre_telefono);
        corre = findViewById(R.id.agre_correo);

        listardocentes = findViewById(R.id.datosdocente);
        volver = (Button) findViewById(R.id.vol_agre);
        agregar = (Button) findViewById(R.id.agregar);
        volver.setOnClickListener(view ->{
                startActivity(new Intent(this, activity_docente_list.class));
        });
        iniciarfirebase();
        agregar.setOnClickListener(view ->{
            String nombre = nom.getText().toString();
            String codigo = cod.getText().toString();
            String dni = denei.getText().toString();
            String telefono = tele.getText().toString();
            String correo = corre.getText().toString();
        if(nombre.equals("")||codigo.equals("")||dni.equals("")||telefono.equals("")||correo.equals("")){
            vacios();
        }
        else {
            docente d = new docente();
            d.setId(UUID.randomUUID().toString());
            d.setNombre(nombre);
            d.setCodigo(codigo);
            d.setDni(dni);
            d.setTelefono(telefono);
            d.setCorreo(correo);
            databaseReference.child("docente").child(d.getId()).setValue(d);
            Toast.makeText(this, "Agregar", Toast.LENGTH_LONG).show();
            limpiar();

        }
        });
    }
    private void iniciarfirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch(item.getItemId()){
                case R.id.editardocente:{

                }
                case R.id.eliminardocente:{
                    Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
                    break;
                }
                default:break;
            }
            return true;

    }
    private void limpiar(){
        nom.setText("");
        cod.setText("");
        denei.setText("");
        tele.setText("");
        corre.setText("");
    }
    private void vacios(){
        String nombre = nom.getText().toString();
        String codigo = cod.getText().toString();
        String dni = denei.getText().toString();
        String telefono = tele.getText().toString();
        String correo = corre.getText().toString();
        if(nombre.equals("")){
            nom.setError("Parametro requerido");
        } else if(codigo.equals("")){
            cod.setError("Parametro requerido");
        } else if(dni.equals("")){
            denei.setError("Para metro requerido");
        } else if(telefono.equals("")){
            tele.setError("Parametro requerido");
        } else if(correo.equals("")){
            corre.setError("Parametro requerido");
        }
    }
}