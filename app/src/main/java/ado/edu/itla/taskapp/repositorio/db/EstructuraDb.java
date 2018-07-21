package ado.edu.itla.taskapp.repositorio.db;

/**
 * Created by MESCyT on 16/6/2018.
 */

public class EstructuraDb {

    public static  final  String TABLA_CATEGORIA = "CREATE TABLE categoria (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
    public static final String TABLA_USUARIO = "CREATE TABLE usuario ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, email TEXT, contrasena TEXT, tipoUsuario TEXT)";






}
