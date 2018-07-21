package ado.edu.itla.taskapp.repositorio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MESCyT on 16/6/2018.
 */

public class ConexionDb extends SQLiteOpenHelper{

    private static final String LOG_TAG = "ConexionDb";
    private static final String NOMBRE_DB = "taskapp.db";
    private static final int VERSION_DB = 1;

    public ConexionDb(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(LOG_TAG, "Creando la base de datos");

        db.execSQL(EstructuraDb.TABLA_CATEGORIA);
        db.execSQL(EstructuraDb.TABLA_USUARIO);
        db.execSQL(EstructuraDb.TABLA_TAREA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
