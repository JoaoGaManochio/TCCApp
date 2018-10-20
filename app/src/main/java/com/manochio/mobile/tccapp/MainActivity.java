package com.manochio.mobile.tccapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button cadastro;
    Button logar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cadastro = (Button) findViewById(R.id.btnCadastroVagas);
        logar = (Button) findViewById(R.id.btnLogar);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastros = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(cadastros);
            }
        });

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logar = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logar);
            }
        });
    }
}
