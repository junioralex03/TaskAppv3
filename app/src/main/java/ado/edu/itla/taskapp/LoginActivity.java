package ado.edu.itla.taskapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import ado.edu.itla.taskapp.MainActivity;
import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.db.ConexionDb;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class LoginActivity extends AppCompatActivity {

    private ConexionDb conexionDb;

    private static final String LOG_TAG = "LogInActivity";


    final EditText txtUsuario = (EditText) findViewById(R.id.txtUsuario);
    final EditText txtClave = (EditText) findViewById(R.id.txtClave);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnRegistrarse = findViewById(R.id.btnRegistrar);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

   /*      Button btnEntar = findViewById(R.id.btnEntrar);
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       //          UsuarioRepositorioDbImp =  UsuarioRepositorioDbImp


       if (Usuario) != null)

                {
                c.moveToFirst();
                do {

                Intent intent = new Intent ( LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
            }}

