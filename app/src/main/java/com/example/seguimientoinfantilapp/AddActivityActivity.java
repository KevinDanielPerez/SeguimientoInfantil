package com.example.seguimientoinfantilapp;

import android.os.Bundle;
import android.util.Log;
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

public class AddActivityActivity extends AppCompatActivity {

    EditText activityDescription, activityDate, activityUserId, activityChildId;
    Button saveActivityButton;
    String URL_ADD_ACTIVITY = "http://192.168.1.101/api/add_activity.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        // Inicialización de los EditText
        activityDescription = findViewById(R.id.activity_description);
        activityDate = findViewById(R.id.activity_date);
        activityUserId = findViewById(R.id.activity_user_id);
        activityChildId = findViewById(R.id.activity_child_id);
        saveActivityButton = findViewById(R.id.save_activity_button);

        saveActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActivity();
            }
        });
    }

    private void addActivity() {
        // Obtener los valores de los campos
        String description = activityDescription.getText().toString().trim();
        String date = activityDate.getText().toString().trim();
        String userId = activityUserId.getText().toString().trim();
        String childId = activityChildId.getText().toString().trim();

        // Validar que todos los campos estén completos
        if (description.isEmpty() || date.isEmpty() || userId.isEmpty() || childId.isEmpty()) {
            Toast.makeText(AddActivityActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear la solicitud POST con Volley
        StringRequest request = new StringRequest(Request.Method.POST, URL_ADD_ACTIVITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddActivityResponse", "Respuesta del servidor: " + response);
                if (response.equals("success")) {
                    Toast.makeText(AddActivityActivity.this, "Actividad agregada correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // Manejar los posibles errores
                    if (response.contains("El usuario no existe")) {
                        Toast.makeText(AddActivityActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    } else if (response.contains("El hijo no existe")) {
                        Toast.makeText(AddActivityActivity.this, "Hijo no encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddActivityActivity.this, "Error al agregar actividad", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage = "Error de conexión: " + error.getMessage();
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    errorMessage = new String(error.networkResponse.data);
                }
                Toast.makeText(AddActivityActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("descripcion", description);
                params.put("fecha", date);
                params.put("id_usuario", userId);  // Enviar el id_usuario desde el campo
                params.put("id_hijo", childId);    // Enviar el id_hijo desde el campo
                return params;
            }
        };

        // Crear la cola de solicitudes y añadir la solicitud
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
