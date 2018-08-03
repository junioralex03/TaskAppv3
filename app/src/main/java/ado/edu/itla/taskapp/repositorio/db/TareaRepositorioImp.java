package ado.edu.itla.taskapp.repositorio.db;

import android.animation.TypeConverter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;

public class TareaRepositorioImp implements TareaRepositorio{

    private ConexionDb conexionDb;
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_DESCRIPCION = "descripcion";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_FECHA_COMPLETADO = "fecha_completado";
    private static final String CAMPO_ESTADO = "estado";
    private static final String CAMPO_USUARIO_CREADOR_ID = "usuario_creador_id";
    private static final String CAMPO_USUARIO_ASIGNADO_ID = "usuario_asignado_id";
    private static final String CAMPO_CATEGORIA_ID = "categoria_id";
    private static final String TABLA_TAREA = "tarea";

    public  TareaRepositorioImp (Context context){
        conexionDb = new ConexionDb(context);
    }
    @Override
    public boolean guardar(Tarea tarea) {

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, tarea.getNombre());
        cv.put(CAMPO_DESCRIPCION, tarea.getDescripcion());
        if (tarea.getFecha() != null)
            cv.put(CAMPO_FECHA, tarea.getFecha().getTime());
        if(tarea.getFechaTerminado()!= null)
            cv.put(CAMPO_FECHA_COMPLETADO, tarea.getFechaTerminado().getTime());
        cv.put(CAMPO_ESTADO, tarea.getEstado().name());
        cv.put(CAMPO_USUARIO_CREADOR_ID, tarea.getUsuarioCreador().getId());
        cv.put(CAMPO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAsignado().getId());
        cv.put(CAMPO_CATEGORIA_ID, tarea.getCategoria().getId());

        SQLiteDatabase db = conexionDb.getWritableDatabase();

        if(tarea.getId() != null && tarea.getId() > 0){
            int cantidad = db.update(TABLA_TAREA,cv, "id = ?", new String[]{tarea.getId().toString()});
            db.close();
            return cantidad > 0;
        }
        else {
            Long id = db.insert(TABLA_TAREA, null, cv);
            db.close();

            if(id.intValue() > 0){
                tarea.setId(id.intValue());
                return true;
            }
        }

        return false;
    }

    @Override
    public Tarea buscar(int id) {
        return null;
    }

    @Override
    public List<Tarea> buscarAsignadaA(Usuario usuario) {
        List<Tarea> tareas = new ArrayList<>();


        String sql = "Select t.*, uc.nombre as nombre_usuario, c.nombre as nombre_cat from tarea t inner join usuario uc on (t.usuario_creador_id = uc.id) " +
                "inner join categoria c on (t.categoria_id = c.id) Where t.usuario_asignada_id=?";
        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] args = {usuario.getId().toString()};
        Cursor cr = db.rawQuery(sql, args);

        while (cr.moveToNext()){
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRIPCION));
            Long fecha = cr.getLong(cr.getColumnIndex(CAMPO_FECHA));
            String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));


            Usuario usuarioCreador = new Usuario();
            usuarioCreador.setId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID)));
            usuarioCreador.setNombre(cr.getString(cr.getColumnIndex("nombre_usuario")));

            Categoria categoria = new Categoria();
            categoria.setId(cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID)));
            categoria.setNombre(cr.getString(cr.getColumnIndex("nombre_cat")));

            Date fechaCreada = new Date(fecha);

            Tarea t = new Tarea();
            t.setId(id);
            t.setNombre(nombre);
            t.setDescripcion(descripcion);
            t.setFecha(fechaCreada);
            t.setEstado(Tarea.TareaEstado.valueOf(estado));
            t.setUsuarioCreador(usuarioCreador);
            t.setUsuarioAsignado(usuario);
            t.setCategoria(categoria);

            tareas.add(t);
        }

        cr.close();
        db.close();

        return tareas;
    }

 /*   @Override
    public List<Tarea> buscarAsignada(Usuario usuario) {
        List<Tarea> tareas = new ArrayList<>();


        String sql = "Select t.*, uc.nombre as nombre_usuario, c.nombre as nombre_cat from tarea t inner join usuario uc on (t.usuario_creador_id = uc.id) " +
                "inner join categoria c on (t.categoria_id = c.id) Where t.usuario_asignada_id=?";
        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] args = {usuario.getId().toString()};
        Cursor cr = db.rawQuery(sql, args);

        while (cr.moveToNext()){
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRIPCION));
            Long fecha = cr.getLong(cr.getColumnIndex(CAMPO_FECHA));
            String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));


            Usuario usuarioCreador = new Usuario();
            usuarioCreador.setId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID)));
            usuarioCreador.setNombre(cr.getString(cr.getColumnIndex("nombre_usuario")));

            Categoria categoria = new Categoria();
            categoria.setId(cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID)));
            categoria.setNombre(cr.getString(cr.getColumnIndex("nombre_cat")));

            Date fechaCreada = new Date(fecha);

            Tarea t = new Tarea();
            t.setId(id);
            t.setNombre(nombre);
            t.setDescripcion(descripcion);
            t.setFecha(fechaCreada);
            t.setEstado(Tarea.TareaEstado.valueOf(estado));
            t.setUsuarioCreador(usuarioCreador);
            t.setUsuarioAsignado(usuario);
            t.setCategoria(categoria);

            tareas.add(t);
        }

        cr.close();
        db.close();

        return tareas;
    }
*/
    @Override
    public List<Tarea> buscarCreadaPor(Usuario usuario) {

        List<Tarea> tareas = new ArrayList<>();


        String sql = "Select t.*, uc.nombre as nombre_usuario, c.nombre as nombre_cat from tarea t inner join usuario uc on (t.usuario_asignado_id = uc.id) " +
                "inner join categoria c on (t.categoria_id = c.id) Where t.usuario_creador_id = ? ";
        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] args = {usuario.getId().toString()};
        Cursor cr = db.rawQuery(sql, args);

        while (cr.moveToNext()){
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRIPCION));
            Long fecha = cr.getLong(cr.getColumnIndex(CAMPO_FECHA));
            String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));


            Usuario usuarioAsignado = new Usuario();
            usuarioAsignado.setId(cr.getInt(cr.getColumnIndex(CAMPO_USUARIO_ASIGNADO_ID)));
            usuarioAsignado.setNombre(cr.getString(cr.getColumnIndex("nombre_usuario")));

            Categoria categoria = new Categoria();
            categoria.setId(cr.getInt(cr.getColumnIndex(CAMPO_CATEGORIA_ID)));
            categoria.setNombre(cr.getString(cr.getColumnIndex("nombre_cat")));

            Date fechaCreada = new Date(fecha);

            Tarea t = new Tarea();
            t.setId(id);
            t.setNombre(nombre);
            t.setDescripcion(descripcion);
            t.setFecha(fechaCreada);
            t.setEstado(Tarea.TareaEstado.valueOf(estado));
            t.setUsuarioCreador(usuario);
            t.setUsuarioAsignado(usuarioAsignado);
            t.setCategoria(categoria);

            tareas.add(t);
        }

        cr.close();
        db.close();
        return tareas;
    }
}