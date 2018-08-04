package ado.edu.itla.taskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.entidad.UsuarioActivo;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class TareaAsignarActivity extends AppCompatActivity {
    private CategoriaRepositorio categoriaRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private TareaRepositorio tareaRepositorio;
    private Tarea tarea;
    private Categoria categoria;
    private Usuario usuarioAsignado;
    private static final String LOG_TAG = "TareaAsignarActivity";


    Spinner spnCategorias, spnUsuarioTecnico;
    EditText txtNombreTarea, txtDescripcionTarea;
    Button btnGuardarTarea, btnCancelarTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_asignar);

        categoriaRepositorio = new CategoriaRepositorioDbImp(this);
        usuarioRepositorio = new UsuarioRepositorioDbImp(this);
        tareaRepositorio = new TareaRepositorioImp(this);

        //Obtiene y muestra la Lista de Categorias
        List<Categoria> categorias = categoriaRepositorio.buscar(null);
        spnCategorias = findViewById(R.id.spnCategoria);
        ArrayAdapter<Categoria> adaptadorCategoria = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categorias);
        adaptadorCategoria.insert(new Categoria().setNombre("Seleccione una categoria").setId(0), 0);
        spnCategorias.setAdapter(adaptadorCategoria);

        //Obtiene la Categoria seleccionada
        spnCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){

                    categoria = (Categoria) spnCategorias.getSelectedItem();
                    Log.i(LOG_TAG, categoria.getId().toString() + " - " + categoria.getNombre());

                    UsuarioActivo u = UsuarioActivo.getInstance();
                    if(u.getId() != null)
                        Log.i(LOG_TAG, u.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Obtiene y muestra la Lista de Usuarios Tecnicos
        List<Usuario> usuarios = usuarioRepositorio.buscarTecnicos(null);
        spnUsuarioTecnico = findViewById(R.id.spnUsuarioTecnico);
        ArrayAdapter<Usuario> adaptadorUsuarioTecnico = new ArrayAdapter(this,android.R.layout.simple_spinner_item,usuarios);
        adaptadorUsuarioTecnico.insert(new  Usuario().setNombre("Seleccione un Técnico ").setId(0), 0);
        spnUsuarioTecnico.setAdapter(adaptadorUsuarioTecnico);



        //Obtiene el Usuario seleccionado
        spnUsuarioTecnico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){

                    usuarioAsignado = (Usuario) spnUsuarioTecnico.getSelectedItem();
                    Log.i(LOG_TAG, usuarioAsignado.getId().toString() + " - " + usuarioAsignado.getNombre() );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnGuardarTarea = findViewById(R.id.btnGuardarTarea);
        btnGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNombreTarea = findViewById(R.id.txtNombreTarea);
                txtDescripcionTarea = findViewById(R.id.txtDescripcionTarea);

                if (txtNombreTarea == null)
                    return;

                if (txtDescripcionTarea == null)
                    return;

                if (categoria == null || categoria.getId() <= 0 )
                    return;

                if (usuarioAsignado.getId() == null || usuarioAsignado.getId() <= 0)
                    return;

                else {

                    tarea = new Tarea();
                    tarea.setUsuarioAsignado(usuarioAsignado);
                    tarea.setCategoria(categoria);
                    tarea.setDescripcion(txtDescripcionTarea.getText().toString());
                    tarea.setNombre(txtNombreTarea.getText().toString());
                    tarea.setUsuarioCreador(UsuarioActivo.getInstance());
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    tarea.setFecha(c.getTime());
                    tarea.setEstado(Tarea.TareaEstado.PENDIENTE);

                    Log.i(LOG_TAG, tarea.toString());

                    tareaRepositorio.guardar(tarea);

                    Log.i(LOG_TAG, tarea.toString());
                }

            }
        });


    }
}