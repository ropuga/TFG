package com.example.romanpuga.tfg;



/**
 * Created by romanpuga on 20/12/17.
 */

public class Usuario {


    private String idUsuario;
    private String nombre;
    private String sexo;
    private String fechaN;

    //Constructor
    public Usuario(String idUsuario, String nombre, String sexo, String fechaN) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaN = fechaN;
    }

    //Getters
    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    //Setters
    public String getFechaN() {
        return fechaN;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }
}
