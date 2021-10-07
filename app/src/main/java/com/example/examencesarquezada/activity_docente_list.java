package com.example.examencesarquezada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class activity_docente_list extends AppCompatActivity {
    Button agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_list);
        agregar = (Button) findViewById(R.id.ir_agregar);
        agregar.setOnClickListener(view -> {
            startActivity(new Intent(this,activity_docente_add.class ));
            finish();
        });
    }
}