package com.manochio.mobile.tccapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogadoAdminActivity extends AppCompatActivity {

    Button cadastrarVagas, removerVagas, listarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado_admin);

        cadastrarVagas = (Button) findViewById(R.id.btnAddVaga);
        removerVagas = (Button) findViewById(R.id.btnRemVaga);
        listarUsuario = (Button) findViewById(R.id.btnListUser);

        cadastrarVagas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrar = new Intent(LogadoAdminActivity.this, CadastroVagasActivity.class);
                startActivity(cadastrar);
            }
        });

        removerVagas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent remover = new Intent(LogadoAdminActivity.this, RemoverVagasAdminActivity.class);
                startActivity(remover);
            }
        });

        listarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(LogadoAdminActivity.this, AllUserActivity.class);
                startActivity(user);
            }
        });
    }
}
