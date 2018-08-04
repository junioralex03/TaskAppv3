package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.TareaDescripcionActivity;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.UsuarioActivo;
import ado.edu.itla.taskapp.repositorio.TareaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioImp;

public class TecnicoActivity extends AppCompatActivity {

    private TareaRepositorio tareaRepositorio;
    private final String LOG_TAG = "TecnicoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);

        tareaRepositorio = new TareaRepositorioImp(this);
        UsuarioActivo usuarioLogeado = UsuarioActivo.getInstance();
        List<Tarea> tareas = tareaRepositorio.buscarAsignadaA(usuarioLogeado);

        ListView tareaListView = (ListView) findViewById(R.id.tarea_usuario_normal_listview);
        tareaListView.setAdapter(new TareaListAdapterUTecnico(this, tareas));

        tareaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Tarea t = (Tarea) parent.getItemAtPosition(position);
                Intent intent = new Intent(TecnicoActivity.this, TareaDescripcionActivity.class);
                intent.putExtra("tarea", t);
                startActivity(intent);
            }
        });
    }
}