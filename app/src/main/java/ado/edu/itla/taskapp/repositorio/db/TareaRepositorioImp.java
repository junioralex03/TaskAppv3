package ado.edu.itla.taskapp.repositorio.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.accessibility.AccessibilityNodeInfo;

import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;

import static android.graphics.BlurMaskFilter.Blur.INNER;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.text.MessagePattern.ArgType.SELECT;

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

        List<Tarea> asignadas = new ArrayList();
        Usuario uc = new Usuario ();

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT t.*, uc AS nombrecreador FROM tarea  INNER JOIN usuario uc on (t.usuario_creador_id = uc.id) " +
                "WHERE t.usuario_asignado_id=?", null );

      uc.setNombre();
      Tarea t = new Tarea();
      t.setUsuarioAsignado(usuario);
      t.setUsuarioCreador(uc);


        return asignadas;
    }

    @Override
    public List<Tarea> buscarCreadaPor(Usuario uuuario) {

        
        return null;
    }
}
