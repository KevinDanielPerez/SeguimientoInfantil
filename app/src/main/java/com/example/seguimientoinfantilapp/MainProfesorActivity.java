package com.example.seguimientoinfantilapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainProfesorActivity extends AppCompatActivity {

    Button btnverhijo, btnveractividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Solo si lo necesitas
        setContentView(R.layout.activity_main_profesor);

        // Inicialización de los botones
        btnverhijo = findViewById(R.id.btnveralumnos);  // Asegúrate de que exista un botón con este ID en el XML
        btnveractividad = findViewById(R.id.btnveractividad);  // Asegúrate de que exista un botón con este ID en el XML

        // Configuración del evento para el botón "Ver hijo/hijos"
        btnverhijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProfesorActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // Configuración del evento para el botón "Ver actividades"
        btnveractividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProfesorActivity.this, ViewActivitiesActivity.class);
                startActivity(intent);
            }
        });
    }
}
