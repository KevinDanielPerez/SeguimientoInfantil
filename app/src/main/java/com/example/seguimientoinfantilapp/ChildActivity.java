package com.example.seguimientoinfantilapp;

public class ChildActivity {
    private int id;
    private String descripcion;
    private String fecha;

    public ChildActivity(int id, String descripcion, String fecha) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }
}
