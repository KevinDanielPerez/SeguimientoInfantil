package com.example.seguimientoinfantilapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewActivitiesActivity extends AppCompatActivity {

    TextView childNameLabel;
    EditText childIdInput; // EditText para que el usuario ingrese el ID del niño
    Button loadActivitiesButton, addActivityButton;
    RecyclerView activityList;
    ArrayList<ChildActivity> activityArrayList;
    ChildActivityAdapter adapter;
    String URL_GET_ACTIVITIES = "http://192.168.1.101/api/get_activities.php?childId=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activities);

        // Inicialización de vistas
        childNameLabel = findViewById(R.id.child_name_label);
        childIdInput = findViewById(R.id.child_id_input);
        loadActivitiesButton = findViewById(R.id.load_activities_button);
        addActivityButton = findViewById(R.id.add_activity_button);
        activityList = findViewById(R.id.activity_list);

        // Inicializando ArrayList y Adapter
        activityArrayList = new ArrayList<>();
        adapter = new ChildActivityAdapter(this, activityArrayList);

        // Configurando el RecyclerView
        activityList.setLayoutManager(new LinearLayoutManager(this));
        activityList.setAdapter(adapter);

        // Acción del botón para cargar actividades
        loadActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recuperar el valor del EditText (childId)
                String childIdStr = childIdInput.getText().toString();

                if (childIdStr.isEmpty()) {
                    Toast.makeText(ViewActivitiesActivity.this, "Por favor, introduce el ID del niño", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int childId = Integer.parseInt(childIdStr);
                    loadActivities(childId); // Llamar a la función para cargar las actividades

                    // Mostrar el nombre del niño (por ejemplo, podrías obtener el nombre de alguna otra fuente)
                    childNameLabel.setText("Actividades de: Niño " + childId);

                } catch (NumberFormatException e) {
                    Toast.makeText(ViewActivitiesActivity.this, "ID inválido. Debe ser un número.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Acción del botón para añadir actividad
        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad para añadir actividad
                Intent intent = new Intent(ViewActivitiesActivity.this, AddActivityActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadActivities(int childId) {
        // Construir la URL con el childId
        String url = URL_GET_ACTIVITIES + childId;
        Log.d("ViewActivitiesActivity", "URL: " + url);  // Verificar la URL en Logcat

        // Realizando la petición con Volley
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("ViewActivitiesActivity", "Response: " + response.toString());
                        activityArrayList.clear();
                        if (response.length() == 0) {
                            Toast.makeText(ViewActivitiesActivity.this, "No se encontraron actividades", Toast.LENGTH_SHORT).show();
                        }
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject activity = response.getJSONObject(i);
                                activityArrayList.add(new ChildActivity(
                                        activity.getInt("Id_actividad"),
                                        activity.getString("Descripcion"),
                                        activity.getString("Fecha")
                                ));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        // Notificar al adaptador que los datos han cambiado
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ViewActivitiesActivity", "Error de red: " + error.getMessage());
                Toast.makeText(ViewActivitiesActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

        // Enviar la petición
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
