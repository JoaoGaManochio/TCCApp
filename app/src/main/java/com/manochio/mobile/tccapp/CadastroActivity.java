package com.manochio.mobile.tccapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manochio.mobile.tccapp.POST.DadosApiCadastrar;

public class CadastroActivity extends AppCompatActivity {

    EditText txtNome, txtSobrenome, txtEmail, txtSenha, txtCidade, txtEstado;
    Button btnCadastro ;
    String ip = "http://192.168.0.105/TCCApp/public/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtNome = (EditText) findViewById(R.id.txtNomeVaga);
        txtSobrenome = (EditText) findViewById(R.id.txtTipoVaga);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtPass);
        txtCidade = (EditText) findViewById(R.id.txtCidade);
        txtEstado = (EditText) findViewById(R.id.txtEstado);
        btnCadastro = (Button) findViewById(R.id.btnCadastroVagas);

        //permite conexão com Internet na tread principal
           StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
           StrictMode.setThreadPolicy(policy);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (post()) {
                    Intent logar = new Intent(CadastroActivity.this, LogadoActivity.class);
                    startActivity(logar);
                    //CadastroActivity.this.finish();
                    //onBackPressed();
                }
            }
        });
    }

    //Pega as informações e envia para a API.
    public boolean post(){

        String first_name = txtNome.getText().toString();
        String last_name = txtSobrenome.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtSenha.getText().toString();
        String city = txtCidade.getText().toString();
        String state = txtEstado.getText().toString();

        //verificar se texto é branco
        if(!first_name.equals("") && !last_name.equals("") && !email.equals("") && !password.equals("") && !city.equals("")
                && !state.equals("")){
            DadosApiCadastrar dadosApiCadastrar = new DadosApiCadastrar(this, ip + "cadastrar", first_name, last_name, email, password,
                    city, state);
            dadosApiCadastrar.execute();
            return true;
        }
        else{
            Toast.makeText(this, "Por favor, verifique os campos!", Toast.LENGTH_SHORT).show();
            return  false;
        }
    }
}