package ado.edu.itla.taskapp;

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
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class TareaActivity extends AppCompatActivity {
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
                    Log.i("Prueba", categoriaSeleccionada.getId().toString() + " - " + categoriaSeleccionada.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Obtiene y muestra la Lista de Usuarios Tecnicos
        usuarios = usuarioRepositorio.buscarTecnicos(null);
        spnUsuarioTecnico = findViewById(R.id.spnUsuarioTecnico);
        obtenerListaUsuariosTecnicos();
        ArrayAdapter<CharSequence> adaptadorUsuarioTecnico = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaUsuario);
        spnUsuarioTecnico.setAdapter(adaptadorUsuarioTecnico);

        //Obtiene el Usuario seleccionado
        spnUsuarioTecnico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    Usuario usuarioTecnicoSeleccionado = usuarios.get(position-1);
                    Log.i("PRUEBA", usuarioTecnicoSeleccionado.getId().toString() + " - " + usuarioTecnicoSeleccionado.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void obtenerListaCategorias(){
        listaCategoria = new ArrayList<String>();
        listaCategoria.add("Seleccione una Categoria");

        for(int i=0; i < categorias.size();i++){
            listaCategoria.add(categorias.get(i).getId() + " - " + categorias.get(i).getNombre());
        }

    }

    private void obtenerListaUsuariosTecnicos(){
        listaUsuario = new ArrayList<String>();
        listaUsuario.add("Seleccione un Técnico");

        for(int i = 0; i < usuarios.size(); i++){
            listaUsuario.add(usuarios.get(i).getId() + " - " + usuarios.get(i).getNombre());
        }

    }
}
