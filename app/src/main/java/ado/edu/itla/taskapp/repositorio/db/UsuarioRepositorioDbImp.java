package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.ConexionDb;


public class UsuarioRepositorioDbImp implements UsuarioRepositorio {

    private ConexionDb conexionDb;
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_EMAIL = "email";
    private static final String CAMPO_CONTRASENA = "contrasena";
    private static final String CAMPO_TIPOUSUARIO = "tipoUsuario";
    private static final String TABLA_USUARIO = "usuario";

    public UsuarioRepositorioDbImp(Context context) {

        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Usuario usuario) {


        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_CONTRASENA, usuario.getContrasena());
        cv.put(CAMPO_TIPOUSUARIO, usuario.getTipoUsuario().name());

        SQLiteDatabase db = conexionDb.getWritableDatabase();
//        db.execSQL("CREATE TABLE usuario ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT, contrasena TEXT, tipoUsuario TEXT)");

        if (usuario.getId() != null && usuario.getId() > 0){
            int cantidad = db.update(TABLA_USUARIO, cv, "id = ?", new String[]{usuario.getId().toString()});
            db.close();
            return cantidad > 0;
        }

        else{
            Long id = db.insert(TABLA_USUARIO, null, cv);
            db.close();

            if (id.intValue() > 0){
                usuario.setId(id.intValue());
                return true;
            }
        }

        return false;
    }

    @Override
    public Usuario buscar(int id) {

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA , CAMPO_TIPOUSUARIO};
        String[] args = new String[]{id + ""};
        Cursor cr = db.query(TABLA_USUARIO, columnas, "id = ?", args, null, null,null );
        cr.moveToFirst();

        Usuario usuario = new Usuario();

        if(cr != null){

            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE) );
            String email = cr.getString(cr.getColumnIndex(CAMPO_EMAIL) );
            String contrasena = cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA) );
            String tipoUsuario = cr.getString(cr.getColumnIndex(CAMPO_TIPOUSUARIO) );
            usuario = new Usuario(id,nombre,email,contrasena,Usuario.TipoUsuario.valueOf(tipoUsuario));
        }

        return usuario;}

    @Override
    public Usuario buscar(String userName) {

        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA , CAMPO_TIPOUSUARIO};
        String[] args = new String[]{userName};
        Cursor cr = db.query(TABLA_USUARIO, columnas, CAMPO_EMAIL + " = ?", args , null, null, null);
        cr.moveToFirst();

        while (!cr.isAfterLast()) {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE) );
            String email = cr.getString(cr.getColumnIndex(CAMPO_EMAIL) );
            String contrasena = cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA) );
            String tipoUsuario = cr.getString(cr.getColumnIndex(CAMPO_TIPOUSUARIO) );


            usuarios.add( new Usuario(id, nombre, email,  contrasena, Usuario.TipoUsuario.valueOf(tipoUsuario)));
            cr.moveToNext();
        }
        cr.close();
        db.close();

        return (Usuario) usuarios;
    }

    @Override
    public List<Usuario> buscarTecnicos(String nombre) {
        return null;
    }
}