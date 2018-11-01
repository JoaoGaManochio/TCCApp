package com.manochio.mobile.tccapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manochio.mobile.tccapp.POST.DadosApiLogin;

public class LoginActivity extends AppCompatActivity {

    EditText txtPass;
    EditText txtEmail;
    Button btnLogar;
    String ip = "http://172.22.37.152/TCCApp/public/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtPass = (EditText) findViewById(R.id.txtPass);
        txtEmail = (EditText) findViewById(R.id.txtLogin);
        btnLogar = (Button) findViewById(R.id.btnLogin);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fazer a verificalção de dados estao corretos.
                logar();
            }
        });
    }
    public void logar(){
        String email = txtEmail.getText().toString();
        String password = txtPass.getText().toString();
        //Verificar o campo "is admin" na api.
            if (!email.equals("") && !password.equals("")) {
                DadosApiLogin dadosApi = new DadosApiLogin(this, ip + "login", email, password);
                dadosApi.execute();
            } else {
                Toast.makeText(this, "Campos em branco!", Toast.LENGTH_SHORT).show();
                //return false;
//            }
        }
    }
}


