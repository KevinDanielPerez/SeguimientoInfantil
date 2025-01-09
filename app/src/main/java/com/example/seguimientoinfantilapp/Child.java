package com.example.seguimientoinfantilapp;

public class Child {
    private int id;
    private String nombre;
    private String fechaNacimiento; // Cambié "edad" a "fechaNacimiento"

    public Child(int id, String nombre, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {  // Cambié "getEdad" a "getFechaNacimiento"
        return fechaNacimiento;
    }
}
