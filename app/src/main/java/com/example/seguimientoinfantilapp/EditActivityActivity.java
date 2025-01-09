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

public class EditActivityActivity extends AppCompatActivity {

    EditText editActivityDescription, editActivityDate;
    Button updateActivityButton;
    int activityId;
    String URL_UPDATE_ACTIVITY = "http://192.168.1.101/api/update_activity.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);

        editActivityDescription = findViewById(R.id.edit_activity_description);
        editActivityDate = findViewById(R.id.edit_activity_date);
        updateActivityButton = findViewById(R.id.update_activity_button);

        activityId = getIntent().getIntExtra("activityId", -1);
        String description = getIntent().getStringExtra("description");
        String date = getIntent().getStringExtra("date");

        editActivityDescription.setText(description);
        editActivityDate.setText(date);

        updateActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateActivity();
            }
        });
    }

    private void updateActivity() {
        String description = editActivityDescription.getText().toString().trim();
        String date = editActivityDate.getText().toString().trim();

        if (description.isEmpty() || date.isEmpty()) {
            Toast.makeText(EditActivityActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE_ACTIVITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(EditActivityActivity.this, "Actividad actualizada correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivityActivity.this, "Error al actualizar actividad", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditActivityActivity.this, "Error de conexión: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(activityId));
                params.put("descripcion", description);
                params.put("fecha", date);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
