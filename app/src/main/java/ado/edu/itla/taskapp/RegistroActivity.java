package ado.edu.itla.taskapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.TextView;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class RegistroActivity extends AppCompatActivity {

    private static final String LOG_TAG = "RegistroActivity";
    private Usuario usuario;
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioRepositorio = new UsuarioRepositorioDbImp(this);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        final EditText txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        final EditText txtEmailUsuario = (EditText) findViewById(R.id.txtEmailUsuario);
        final EditText txtContrasena = (EditText) findViewById(R.id.txtContrasena);
        final EditText txtContrasenaConfirmar = (EditText) findViewById(R.id.txtContrasenaConfirmar);
        final RadioButton rbtnTecnico = (RadioButton) findViewById(R.id.rbtnTipoTecnico);
        final RadioButton rbtnNormal = (RadioButton) findViewById(R.id.rbtnTipoNormal);
        final TextView txtValidacionContrasena = findViewById(R.id.txtValidacionContrasena);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usuario == null) {
                    usuario = new Usuario();
                }

                usuario.setNombre(txtNombreUsuario.getText().toString());
                usuario.setEmail(txtEmailUsuario.getText().toString());
                usuario.setContrasena(txtContrasena.getText().toString());
                if(rbtnNormal.isChecked()==true){
                    usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(rbtnNormal.getText().toString()));
                } else
                if
                        (rbtnTecnico.isChecked()==true){
                    usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(rbtnTecnico.getText().toString()));
                }
                Log.i(LOG_TAG, usuario.toString());

                usuarioRepositorio.guardar(usuario);

                Log.i(LOG_TAG, usuario.toString());
            }
        });
    }


}