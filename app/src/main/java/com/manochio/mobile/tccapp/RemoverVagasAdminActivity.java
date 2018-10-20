package com.manochio.mobile.tccapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.manochio.mobile.tccapp.Adapter.RemoverVagaAdminAdapter;
import com.manochio.mobile.tccapp.Adapter.VagasAdapter;
import com.manochio.mobile.tccapp.GET.DadosGetApi;
import com.manochio.mobile.tccapp.Model.Vagas;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class RemoverVagasAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_vagas_admin);

        //permite conexão com Internet na tread principal
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // Pedir permissão para acesso a internet
            if (ActivityCompat.checkSelfPermission(RemoverVagasAdminActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(RemoverVagasAdminActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
            } else {
                mostarDados();
            }
        } catch (Exception e) {
            Toast.makeText(RemoverVagasAdminActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void mostarDados() throws IOException, JSONException {
        ListView listView = (ListView) findViewById(R.id.listRemover);
        ArrayList<Vagas> dados = DadosGetApi.verificarList();
        ArrayAdapter<Vagas> arrayAdapter = new RemoverVagaAdminAdapter(this, dados);
        listView.setAdapter(arrayAdapter);
    }
}
