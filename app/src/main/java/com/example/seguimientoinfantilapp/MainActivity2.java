package com.example.seguimientoinfantilapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class MainActivity2 extends AppCompatActivity {

    Button addChildButton;
    RecyclerView childrenList;
    ArrayList<Child> childrenArrayList;
    ChildrenAdapter adapter;
    String URL_GET_CHILDREN = "http://192.168.1.101/api/get_children.php";  // Asegúrate de que esta URL sea accesible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addChildButton = findViewById(R.id.add_child_button);
        childrenList = findViewById(R.id.children_list);

        childrenArrayList = new ArrayList<>();
        adapter = new ChildrenAdapter(this, childrenArrayList);

        childrenList.setLayoutManager(new LinearLayoutManager(this));
        childrenList.setAdapter(adapter);

        addChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, AddChildActivity.class));
            }
        });

        loadChildren();
    }

    private void loadChildren() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_GET_CHILDREN, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        childrenArrayList.clear(); // Limpiar la lista antes de agregar nuevos elementos
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject child = response.getJSONObject(i);

                                // Corregir claves JSON
                                int id = Integer.parseInt(child.getString("Id_hijo"));  // Convertir de String a int
                                String nombre = child.getString("Nombre_hijo");
                                String fechaNacimiento = child.getString("Fecha_nacimiento");

                                // Añadir el niño a la lista
                                childrenArrayList.add(new Child(id, nombre, fechaNacimiento));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();  // Notificar al adaptador que la lista ha cambiado
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
