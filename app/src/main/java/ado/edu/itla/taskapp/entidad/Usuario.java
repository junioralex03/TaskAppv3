package ado.edu.itla.taskapp.entidad;

import android.content.Intent;

import java.io.Serializable;


public class Usuario implements Serializable{

    public enum TipoUsuario{
        TECNICO, NORMAL
    }

    private Integer id;
    private String nombre;
    private String email;
    private String contrasena;

    private TipoUsuario tipoUsuario;

    public Usuario() {

    }

    public Usuario(Integer id, String nombre, String email, String contrasena, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public Usuario setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Usuario setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Usuario setContrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public Usuario setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        return this;
    }

    @Override
    public String toString() {

        return id+" - "+nombre;
    }
}