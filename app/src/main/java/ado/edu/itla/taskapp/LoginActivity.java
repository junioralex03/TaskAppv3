package ado.edu.itla.taskapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.io.CharArrayReader;

import ado.edu.itla.taskapp.MainActivity;
import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.db.ConexionDb;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioImp;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class LoginActivity extends AppCompatActivity {


    //Validamos el usuario y la contraseña

     ;
    private static final String LOG_TAG = "LogInActivity";
   // TextView = (AutoCompleteTextView) findViewById(R.id.txtUsuario);

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

         Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent ( LoginActivity.this, TareaAsignarActivity.class);
                startActivity(intent);
            }
        });
    }


    }