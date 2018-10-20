package com.manochio.mobile.tccapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


public class LogadoActivity extends AppCompatActivity {
    TextView nome;
    Button verificar, reservar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        //Pega o ID recebido na LoginActivity
        final int var = this.getIntent().getIntExtra("id", 0);

        //Permite conexão com Internet na tread principal
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nome = (TextView) findViewById(R.id.ListName);
        verificar = (Button) findViewById(R.id.btnVerifica);
        reservar = (Button) findViewById(R.id.btnReservar);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogadoActivity.this, VagasDisponiveisActivity.class);
                intent.putExtra("id", var);
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogadoActivity.this, VagasDisponiveisUserActivity.class);
                intent.putExtra("id", var);
                startActivity(intent);
            }
        });

        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogadoActivity.this, VagasDisponiveisActivity.class);
                intent.putExtra("id", var);
                startActivity(intent);
            }
        });
    }
}
