package com.manochio.mobile.tccapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.manochio.mobile.tccapp.POST.DadosApiCadastrar;
import com.manochio.mobile.tccapp.POST.DadosApiCadastrarVagas;

public class CadastroVagasActivity extends AppCompatActivity {

    Button cadatrarVaga;
    TextView nomeVaga;
    Spinner typeVaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_vagas);

        cadatrarVaga = (Button) findViewById(R.id.btnCadastroVagas);
        nomeVaga = (TextView) findViewById(R.id.txtNomeVaga);
        typeVaga = (Spinner) findViewById(R.id.spinnerTipoVagas);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipoVagas, android.R.layout.simple_spinner_item);
        typeVaga.setAdapter(adapter);

        cadatrarVaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               postVagas();
            }
        });
    }

    //Pega as informações e envia para a API.
    public boolean postVagas(){

        String name = nomeVaga.getText().toString();
        String type = typeVaga.getSelectedItem().toString();
        String v = "";
        if (type.equals("Normal")){
            v = "N";
        }
        if (type.equals("Deficiente")){
            v = "D";
        }

        //verificar se texto é branco
        if(!name.equals("") && !type.equals("")){
            DadosApiCadastrarVagas dadosApiCadastrarVagas =
                    new DadosApiCadastrarVagas(this, "http://192.168.0.105/TCCApp/public/api/cadastrar-vagas",name, v);
            dadosApiCadastrarVagas.execute();
            //Toast.makeText(this, "Vaga cadastrada com sucesso!!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this, "Por favor, verifique os campos!", Toast.LENGTH_SHORT).show();
            return  false;
        }
    }
}
