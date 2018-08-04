package ado.edu.itla.taskapp.entidad;

import java.io.Serializable;

/**
 * Created by MESCyT on 9/6/2018.
 */

public class Categoria implements Serializable {

    private Integer id;
    private String nombre;

    public Categoria() {
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public Categoria setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
