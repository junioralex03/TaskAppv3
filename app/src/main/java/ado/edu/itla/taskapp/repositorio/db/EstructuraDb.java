package ado.edu.itla.taskapp.repositorio.db;

/**
 * Created by MESCyT on 16/6/2018.
 */

public class EstructuraDb {

    public static  final  String TABLA_CATEGORIA = "CREATE TABLE categoria (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
    public static final String TABLA_USUARIO = "CREATE TABLE usuario ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, email TEXT, contrasena TEXT, tipoUsuario TEXT)";
    public static final String TABLA_TAREA = "CREATE TABLE tarea (\n" +
            "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "    nombre TEXT NOT NULL,\n" +
            "    descripcion TEXT,\n" +
            "    fecha NUMERIC NOT NULL,\n" +
            "    fecha_completado NUMERIC,\n" +
            "    estado TEXT NOT NULL DEFAULT 'PENDIENTE',\n" +
            "    usuario_creador_id INTEGER NOT NULL,\n" +
            "    usuario_asignado_id INTEGER NOT NULL,\n" +
            "    categoria_id INTEGER NOT NULL)";






}
