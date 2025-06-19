package com.juancarlos.cvportafolio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.juancarlos.cvportafolio.app.AppConfig;
import com.juancarlos.cvportafolio.app.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class EnviarContacto extends BaseActivity {

    private EditText editTextNombre, editTextEmail, editTextMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_contacto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMensaje = findViewById(R.id.editTextMensaje);
        Button buttonEnviar = findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "iM)J;NmDwn3[QTF~54?XA^EyuVsAiz.RayO&o7^g@z:.GNcnfF6_FM7V&KHi[";
                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String mensaje = editTextMensaje.getText().toString().trim();

                if (nombre.isEmpty() || email.isEmpty() || mensaje.isEmpty()) {
                    Toast.makeText(EnviarContacto.this, R.string.noenviado, Toast.LENGTH_SHORT).show();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.SEND,
                            response -> {
                                Toast.makeText(getApplicationContext(), R.string.enviado, Toast.LENGTH_LONG).show();
                                // Aquí podrías enviar el mensaje por email o a un servidor
                                editTextNombre.setText("");
                                editTextEmail.setText("");
                                editTextMensaje.setText("");
                            },
                            error -> {
                                Toast.makeText(getApplicationContext(), R.string.notenviado, Toast.LENGTH_LONG).show();
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("api_key", key); // La misma que en el PHP
                            params.put("nombre", nombre);
                            params.put("email", email);
                            params.put("mensaje", mensaje);
                            return params;
                        }
                    };

                    // Agrega la solicitud a la cola
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);


                }
            }
        });
    }
}