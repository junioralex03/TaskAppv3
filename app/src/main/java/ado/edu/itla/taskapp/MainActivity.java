package ado.edu.itla.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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


  //URL gitUrl = new url = "https://api.github.com/gists/public";
  

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

        Usuario u = new Usuario();
        u.setNombre("Prueba");
        Gson g = new Gson();
        String json = g.toJson(u);
        Log.i("JSON", json);


        String usuarioSring = "{\"nombre\":\"Prueba\"}";
        u = g.fromJson(usuarioSring, Usuario.class);
        Log.i("JSON", u.toString());


        try {
            URL gitapi = new URL("https://api.github.com/gists/public");
            HttpURLConnection connection = (HttpURLConnection) gitapi.openConnection();

            if (connection.getResponseCode() == 200){
                InputStream result = connection.getInputStream();

                ByteArrayOutputStream result1 = new ByteArrayOutputStream();
                byte[] buffer = new byte[1204];
                int length;
                while ((length = result.read(buffer)) != -1){
                    result1.write(buffer, 0, length);
                }

                Log.i("API", result1.toString());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}