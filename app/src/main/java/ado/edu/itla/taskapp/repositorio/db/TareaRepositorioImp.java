package ado.edu.itla.taskapp.repositorio.db;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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


    public TareaRepositorioImp(Context context) {

        conexionDb = new ConexionDb(context);
    }
    @Override
    public boolean guardar(Tarea tarea) {

        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, tarea.getNombre());
        cv.put(CAMPO_DESCRIPCION, tarea.getDescripcion());
        cv.put(CAMPO_FECHA, tarea.getFecha().toString());
      //  cv.put(CAMPO_ESTADO, tarea.setEstado.name());
        cv.put(CAMPO_FECHA_COMPLETADO, tarea.getFechaTerminado().toString());
        cv.put(CAMPO_USUARIO_CREADOR_ID, tarea.getUsuarioCreador().toString());
        cv.put(CAMPO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAsignado().toString());
        cv.put(CAMPO_CATEGORIA_ID, tarea.getCategoria().toString());
        cv.put(CAMPO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAsignado().toString());
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        Long id = db.insert(TABLA_TAREA,null, cv);

        db.close();

        if (id.intValue() > 0){
            tarea.setId(id.intValue());
            return true;
        }

        return false;

    }

    @Override
    public Categoria buscar(int id) {
        return null;
    }

    @Override
    public List<Tarea> buscarAsignadaA(Usuario uuuario) {
        return null;
    }

    @Override
    public List<Tarea> buscarCreadaPor(Usuario uuuario) {
        return null;
    }
}
