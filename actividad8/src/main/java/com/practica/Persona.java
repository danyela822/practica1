package com.practica;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private Integer edad;
    private String direccion;

    public Persona(String nombre, Integer edad, String direccion){
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{ NOMBRE: " + nombre + ", EDAD: " + edad + ", DIRECCION: " + direccion + "}";
    }
}
