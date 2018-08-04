package ado.edu.itla.taskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.entidad.UsuarioActivo;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;
import ado.edu.itla.taskapp.vista.NormalActivity;
import ado.edu.itla.taskapp.vista.TecnicoActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LogInActivity";
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioRepositorio = new UsuarioRepositorioDbImp(this);

        Button btnRegistrarse = findViewById(R.id.btnRegistrar);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });




        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText txtEmailUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
                final EditText txtContrasenaUsuario = (EditText) findViewById(R.id.txtUsuario);
                final TextView lblMensaje = (TextView) findViewById(R.id.lblMensaje);

                Usuario us =(Usuario) usuarioRepositorio.buscar(txtEmailUsuario.getText().toString());
                if (us != null){
                    UsuarioActivo usuarioLogeado = UsuarioActivo.getInstance(us);
                    Log.i(LOG_TAG, us.getId().toString() + " - " + us.getEmail() + " - " + us.getNombre() + " - " + us.getContrasena());

                    if(us.getContrasena().equals(txtContrasenaUsuario.getText().toString()) && us.getTipoUsuario().equals(Usuario.TipoUsuario.NORMAL)) {
                        Intent intent = new Intent(LoginActivity.this, NormalActivity.class);
                        startActivity(intent);
                    }
                    else if (us.getContrasena().equals(txtContrasenaUsuario.getText().toString()) && us.getTipoUsuario().equals(Usuario.TipoUsuario.TECNICO)){
                        Intent intent = new Intent(LoginActivity.this,  TecnicoActivity.class);
                        startActivity(intent);
                    }

                    else {
                        lblMensaje.setText("Usuario y contraseña no coinciden, Favor intentar nuevamente.");
                        Log.i(LOG_TAG, "00000000fx");
                    }
                }
                else{
                    lblMensaje.setText("Usuario y contraseña no coinciden, Favor intentar nuevamente.");
                }



            }
        });
    }

}