package ado.edu.itla.taskapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.ConexionDb;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class TareaActivity extends AppCompatActivity {
    private ConexionDb conexionDb;

    public TareaActivity(Context context) {

        conexionDb = new ConexionDb(context);
    }

    private CategoriaRepositorio categoriaRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private List<Categoria> categorias;
    private List<Usuario> usuarios;

    Spinner spnCategorias, spnUsuarioTecnico;
    ArrayList<String> listaCategoria, listaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_asignar);

        categoriaRepositorio = new CategoriaRepositorioDbImp(this);
        usuarioRepositorio = new UsuarioRepositorioDbImp(this);

        //Obtiene y muestra la Lista de Categorias
        categorias = categoriaRepositorio.buscar(null);
        spnCategorias = findViewById(R.id.spnCategoria);
        obtenerListaCategorias();
        ArrayAdapter<CharSequence> adaptadorCategoria = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaCategoria);
        spnCategorias.setAdapter(adaptadorCategoria);

        //Obtiene la Categoria seleccionada
        spnCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    Categoria categoriaSeleccionada = categorias.get(position-1);
                    Log.i("Test", categoriaSeleccionada.getId().toString() + " - " + categoriaSeleccionada.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        usuarios = usuarioRepositorio.buscarTecnicos(null);
        spnUsuarioTecnico = findViewById(R.id.spnUsuarioTecnico);
        obtenerListaUsuariosTecnicos();
        ArrayAdapter<CharSequence> adaptadorUsuarioTecnico = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaUsuario);
        spnUsuarioTecnico.setAdapter(adaptadorUsuarioTecnico);

        spnUsuarioTecnico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    Usuario usuarioTecnicoSeleccionado = usuarios.get(position-1);
                    Log.i("Test", usuarioTecnicoSeleccionado.getId().toString() + " - " + usuarioTecnicoSeleccionado.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void obtenerListaCategorias(){


    }

    private void obtenerListaUsuariosTecnicos(){


        SQLiteDatabase db = conexionDb.getReadableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM usuario WHERE tipoUsuario = tecnico", null );

       // if (cr = 0)

    }}
