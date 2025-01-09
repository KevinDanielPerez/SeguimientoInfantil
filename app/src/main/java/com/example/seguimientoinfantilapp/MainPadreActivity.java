package com.example.seguimientoinfantilapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainPadreActivity extends AppCompatActivity {

    Button btnverhijo, btnveractividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Solo si lo necesitas
        setContentView(R.layout.activity_main_padre);

        // Inicialización de los botones
        btnverhijo = findViewById(R.id.btnveralumnos);
        btnveractividad = findViewById(R.id.btnveractividad);

        btnverhijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPadreActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        btnveractividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPadreActivity.this, ViewActivitiesActivity.class);
                startActivity(intent);
            }
        });
    }
}
