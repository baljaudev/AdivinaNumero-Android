package com.example.adivinanumero2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {

    public static final String CLAVE_NICK = "NICK";
    public static final String CLAVE_INTENTOS = "INTENTOS";
    EditText etNick;
    EditText etIntentos;
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        etNick = findViewById(R.id.etNick);
        etIntentos = findViewById(R.id.etIntentos);
        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nick = etNick.getText().toString().trim();
                String intentos = etIntentos.getText().toString().trim();

                if (nick.isEmpty() || intentos.isEmpty()) {
                    Toast.makeText(InicioActivity.this,
                            R.string.no_data, Toast.LENGTH_LONG).show();
                } else {
                    int numIntentos = Integer.parseInt(intentos);
                    Intent i = new Intent(InicioActivity.this, MainActivity.class);
                    i.putExtra(CLAVE_NICK, nick);
                    i.putExtra(CLAVE_INTENTOS, numIntentos);

                    startActivity(i);
                }
            }
        });
    }
}