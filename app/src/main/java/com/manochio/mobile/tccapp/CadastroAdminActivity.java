package com.manochio.mobile.tccapp;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manochio.mobile.tccapp.POST.DadosApiCadastrarAdmin;

public class CadastroAdminActivity extends AppCompatActivity {

    EditText nomeAdmin, sobrenomeAdmin, emailAdmin, senhaAdmin, cidadeAdmin, estadoAdmin;
    Button cadastrarAdmin;
    String ip = "http://192.168.0.105/TCCApp/public/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_admin);

        nomeAdmin = (EditText) findViewById(R.id.txtNomeAdmin);
        sobrenomeAdmin = (EditText) findViewById(R.id.txtSobrenomeAdmin);
        emailAdmin = (EditText) findViewById(R.id.txtEmailAdmin);
        senhaAdmin = (EditText) findViewById(R.id.txtSenhaAdmin);
        cidadeAdmin = (EditText) findViewById(R.id.txtCidadeAdmin);
        estadoAdmin = (EditText) findViewById(R.id.txtEstadoAdmin);

        cadastrarAdmin = (Button) findViewById(R.id.btnCadastrarAdmin);

        //Conexão com net
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        cadastrarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (cadastro()){
                   Intent voltar = new Intent(CadastroAdminActivity.this, LogadoAdminActivity.class);
                   startActivity(voltar);
               }
            }
        });
    }

    public boolean cadastro(){
        String first_name = nomeAdmin.getText().toString();
        String last_name = sobrenomeAdmin.getText().toString();
        String email = emailAdmin.getText().toString();
        String password = senhaAdmin.getText().toString();
        String city = cidadeAdmin.getText().toString();
        String state = estadoAdmin.getText().toString();

        //verificar se texto é branco
        if(!first_name.equals("") && !last_name.equals("") && !email.equals("") && !password.equals("") && !city.equals("")
                && !state.equals("")){
            //Manda dados para API
            DadosApiCadastrarAdmin dadosApiCadastrarAdmin = new DadosApiCadastrarAdmin(this,ip + "cadastrar-admin",
                    first_name, last_name, email, password, city, state);
            dadosApiCadastrarAdmin.execute();
            return true;
        }
        else{
            Toast.makeText(this, "Por favor, verifique os campos!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
