package ado.edu.itla.taskapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.entidad.UsuarioActivo;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;
import ado.edu.itla.taskapp.vista.NormalActivity;
import ado.edu.itla.taskapp.vista.TecnicoActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LoginActivity";
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
        {
            final TextView mTextView = (TextView) findViewById(R.id.text);
// ...

// Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://192.168.200.150:8080";
            final Gson gson = new Gson();
// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.i("RESPONSE", response);
                            Usuario g = gson.fromJson(response, Usuario.class);

                            // Display the first 500 characters of the response string.
                            mTextView.setText("Response is: " + response.substring(0, 500));
                        }
                    }, new Response.ErrorListener() {


                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("RESPONSE", error.toString());

                    mTextView.setText("That didn't work!");
                }
            }) {

                @Override
                public byte[] getBody() throws AuthFailureError {

                    Gson gson = new Gson();
                    String json = gson.toJson(usuario);

                    return json.getBytes();
                }
            };


// Add the request to the RequestQueue.
            queue.add(stringRequest);
            ;
        }


        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText txtUsuario = (EditText) findViewById(R.id.txtUsuario);
                final EditText txtClave = (EditText) findViewById(R.id.txtClave);
                final TextView lblMensaje = (TextView) findViewById(R.id.lblMensaje);

                Usuario us =(Usuario) usuarioRepositorio.buscar(txtUsuario.getText().toString());
                if (us != null){
                    UsuarioActivo usuarioLogeado = UsuarioActivo.getInstance(us);
                    Log.i(LOG_TAG, us.getId().toString() + " - " + us.getEmail() + " - " + us.getNombre() + " - " + us.getContrasena());

                    if(us.getContrasena().equals(txtClave.getText().toString()) && us.getTipoUsuario().equals(Usuario.TipoUsuario.NORMAL)) {
                        Intent intent = new Intent(LoginActivity.this, NormalActivity.class);
                        startActivity(intent);
                    }
                    else if (us.getContrasena().equals(txtClave.getText().toString()) && us.getTipoUsuario().equals(Usuario.TipoUsuario.TECNICO)){
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