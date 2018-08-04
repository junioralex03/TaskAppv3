package ado.edu.itla.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.net.URL;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.vista.CategoriaActivity;
import ado.edu.itla.taskapp.vista.CategoriaListaActivity;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class MainActivity extends AppCompatActivity {

  /*  Usuario usuario  = new Usuario();
    usuario.setEmail("junioralex199203@gmail.com");
    usuario.setNombre("Junior");

    Gson g = new Gson();
    String json = g.toJson(usuario);

    String cadena = "()";
    Usuario u =g.fromJson(cadena, Usuario.class).toString()*/


  URL gitUrl = new url = "https://api.github.com/gists/public";
  

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCategoria = findViewById(R.id.btnCategoria);
        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CategoriaListaActivity.class);
                startActivity(intent);
            }
        });

    }
}
