package com.example.adivinanumero2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adivinanumero2.data.Datos;

public class MainActivity extends AppCompatActivity {


    TextView tvIntentos;
    TextView tvPista;
    Button btnComprobar;
    EditText etNumero;
    RelativeLayout rl;
    private boolean acertado = false;
    private int numSecreto;
    private String nick;
    private int contIntentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nick = getIntent().getStringExtra(InicioActivity.CLAVE_NICK);
        contIntentos = getIntent().getIntExtra(InicioActivity.CLAVE_INTENTOS, Datos.NUM_INTENTOS);

        numSecreto = Datos.generarNumSecreto();
        System.out.println("------Número secreto --> " + numSecreto);

        tvIntentos = findViewById(R.id.tvIntentos);
        tvPista = findViewById(R.id.tvPista);
        btnComprobar = findViewById(R.id.btnComparacion);
        etNumero = findViewById(R.id.etNumero);
        rl = findViewById(R.id.layout_rl);

        tvIntentos.setText(String.format(getResources().getString(R.string.tvIntentosRestantes), contIntentos));

        tvPista.setText(String.format(getResources().getString(R.string.tvTextoInicio), Datos.MIN, Datos.MAX, Datos.NUM_INTENTOS, nick));

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNumero.getText().toString().isEmpty()) tvPista.setText(R.string.pistaVacio);
                else {
                    contIntentos--;
                    tvIntentos.setText(String.format(getString(R.string.tvIntentosRestantes), contIntentos));

                    comprobarNumero(numSecreto);

                    if (acertado) numeroAcertado();
                    else if (contIntentos == 0) finJuego(numSecreto);
                }
            }
        });
    }

    private void numeroAcertado() {
        tvIntentos.setText("");
        tvPista.setText(String.format(getString(R.string.pistaAcierto), nick));
        rl.setBackgroundColor(getResources().getColor(R.color.green));
        btnComprobar.setText(R.string.btnFinJuego);
        btnComprobar.setEnabled(false);
    }

    private void comprobarNumero(int randomNumber) {
        if (randomNumber > Integer.parseInt(etNumero.getText().toString())) tvPista.setText(String.format(getString(R.string.pistaMayor), nick));
        else if (randomNumber < Integer.parseInt(etNumero.getText().toString())) tvPista.setText(String.format(getString(R.string.pistaMenor), nick));
        else acertado = true;
    }

    private void finJuego(int randomNumber) {
        tvPista.setText(String.format(getString(R.string.pistaFinInt), nick, randomNumber));
        rl.setBackgroundColor(getResources().getColor(R.color.red));
        btnComprobar.setText(R.string.btnFinJuego);
        btnComprobar.setEnabled(false);

    }

}