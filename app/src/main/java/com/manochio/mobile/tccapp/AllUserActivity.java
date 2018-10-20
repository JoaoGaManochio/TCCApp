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

import com.manochio.mobile.tccapp.Adapter.UserAdpter;
import com.manochio.mobile.tccapp.Adapter.VagasAdapter;
import com.manochio.mobile.tccapp.GET.DadosGetApi;
import com.manochio.mobile.tccapp.Model.Usuario;
import com.manochio.mobile.tccapp.Model.Vagas;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class AllUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user); //permite conexão com Internet na tread principal
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // Pedir permissão para acesso a internet
            if (ActivityCompat.checkSelfPermission(AllUserActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AllUserActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
            } else {
                mostarDados();
            }
        } catch (Exception e) {
            Toast.makeText(AllUserActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void mostarDados() throws IOException, JSONException {
        ListView listView = (ListView) findViewById(R.id.listUser);
        ArrayList<Usuario> dados = DadosGetApi.listUser();
        ArrayAdapter<Usuario> arrayAdapter = new UserAdpter(this, dados);
        listView.setAdapter(arrayAdapter);
    }
}
