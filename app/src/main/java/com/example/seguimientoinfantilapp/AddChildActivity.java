package com.example.seguimientoinfantilapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddChildActivity extends AppCompatActivity {

    EditText childName, childAge, childBirthDate;  // Añadir campo para la fecha de nacimiento
    Button saveChildButton;
    String URL_ADD_CHILD = "http://192.168.1.101/api/add_child.php";  // URL del backend

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        // Inicializar campos
        childName = findViewById(R.id.child_name);
        childAge = findViewById(R.id.child_age);
        childBirthDate = findViewById(R.id.child_birthdate);  // Inicializar fecha de nacimiento
        saveChildButton = findViewById(R.id.save_child_button);

        // Evento de botón para guardar niño
        saveChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChild();
            }
        });
    }

    private void addChild() {
        String name = childName.getText().toString().trim();
        String age = childAge.getText().toString().trim();
        String birthDate = childBirthDate.getText().toString().trim();  // Obtener fecha de nacimiento

        // Validación
        if (name.isEmpty() || age.isEmpty() || birthDate.isEmpty()) {
            Toast.makeText(AddChildActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Realizar la solicitud POST
        StringRequest request = new StringRequest(Request.Method.POST, URL_ADD_CHILD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(AddChildActivity.this, "Niño agregado correctamente", Toast.LENGTH_SHORT).show();
                    finish();  // Cerrar la actividad
                } else {
                    Toast.makeText(AddChildActivity.this, "Error al agregar niño", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddChildActivity.this, "Error de conexión: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Enviar los parámetros al backend
                Map<String, String> params = new HashMap<>();
                params.put("nombre", name);
                params.put("edad", age);
                params.put("fecha_nacimiento", birthDate);  // Enviar fecha de nacimiento
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
