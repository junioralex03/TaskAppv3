package ado.edu.itla.taskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.entidad.UsuarioActivo;

public class TareaDescripcionActivity extends AppCompatActivity {
    private static final String LOG_TAG = "TareaDetalleActivity";
    private Tarea tarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_detalle);

        Button btnCambiarEstado = (Button) findViewById(R.id.btnCambiarEstado);
        TextView lblFechaTarea = (TextView) findViewById(R.id.lblFechaTarea);
        TextView lblCategoriaAsignada = (TextView) findViewById(R.id.lblCategoriaAsignada);
        TextView lblUsuarioAsignado = (TextView) findViewById(R.id.lblUsuarioAsignado);
        TextView lblEstadoAsignado = (TextView) findViewById(R.id.lblEstadoAsignado);
        TextView lblNombreAsignado = (TextView) findViewById(R.id.lblNombreAsignado);
        TextView lblDescripcionTarea = (TextView) findViewById(R.id.lblDescripcionTarea);
        TextView lblUsuarioAsignadoTitulo = (TextView) findViewById(R.id.lblUsuarioAsignadoTitulo);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("tarea")){
            tarea = (Tarea) bundle.getSerializable("tarea");

            UsuarioActivo usuarioLogeado = UsuarioActivo.getInstance();

            if (usuarioLogeado.getTipoUsuario().equals(Usuario.TipoUsuario.NORMAL)) {

                lblFechaTarea.setText(tarea.getFecha().toString());
                lblCategoriaAsignada.setText(tarea.getCategoria().getNombre());
                lblUsuarioAsignado.setText(tarea.getUsuarioAsignado().getNombre());
                lblEstadoAsignado.setText(tarea.getEstado().toString());
                lblNombreAsignado.setText(tarea.getNombre());
                lblDescripcionTarea.setText(tarea.getDescripcion());
            }

            if (usuarioLogeado.getTipoUsuario().equals(Usuario.TipoUsuario.TECNICO))
            {
                lblUsuarioAsignadoTitulo.setText("Creado por fulanito:");
                lblFechaTarea.setText(tarea.getFecha().toString());
                lblCategoriaAsignada.setText(tarea.getCategoria().getNombre());
                lblUsuarioAsignado.setText(tarea.getUsuarioCreador().getNombre());
                lblEstadoAsignado.setText(tarea.getEstado().toString());
                lblNombreAsignado.setText(tarea.getNombre());
                lblDescripcionTarea.setText(tarea.getDescripcion());
            }
        }
    }
}