package com.example.guillermorios.ingresoautos.model;

public class Persona {

    private String IDP;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Marca;
    private String Modelo;
    private String Patente;


    public Persona() {

    }

    public String getPatente() {
        return Patente;
    }

    public void setPatente(String patente) {
        this.Patente = patente;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        this.Modelo = modelo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getIDP() {
        return IDP;
    }

    public void setIDP(String IDP) {
        this.IDP = IDP;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    @Override
    public String toString() { return Patente;}

}
