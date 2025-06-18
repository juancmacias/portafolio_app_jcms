package com.juancarlos.cvportafolio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.juancarlos.cvportafolio.app.BaseActivity;

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
                String nombre = editTextNombre.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String mensaje = editTextMensaje.getText().toString().trim();

                if (nombre.isEmpty() || email.isEmpty() || mensaje.isEmpty()) {
                    Toast.makeText(EnviarContacto.this, R.string.noenviado, Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí podrías enviar el mensaje por email o a un servidor
                    editTextNombre.setText("");
                    editTextEmail.setText("");
                    editTextMensaje.setText("");
                    Toast.makeText(EnviarContacto.this, R.string.enviado, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}