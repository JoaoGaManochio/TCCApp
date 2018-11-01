package com.manochio.mobile.tccapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manochio.mobile.tccapp.LogadoActivity;
import com.manochio.mobile.tccapp.Model.Vagas;
import com.manochio.mobile.tccapp.POST.DadosApiVagas;
import com.manochio.mobile.tccapp.R;
import com.manochio.mobile.tccapp.VagasDisponiveisActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class VagasAdapter extends ArrayAdapter<Vagas> {

    private final Context context;
    private  ArrayList<Vagas> elementos;
    int id_user;
    String ip = "http://172.22.37.152/TCCApp/public/api/";

    public VagasAdapter (Context context, ArrayList<Vagas> elementos, int id_user) throws IOException, JSONException {
        super(context, R.layout.vagas_dis, elementos);
        this.context = context;
        this.elementos = elementos;
        this.id_user = id_user;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        final String id_vagas = elementos.get(position).getId();
        String url = ip + "reservar-vagas/" + id_vagas + "/" + id_user;
        final DadosApiVagas dadosApi = new DadosApiVagas(context, url, id_vagas, id_user);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.vagas_dis, parent, false);

        final TextView name = (TextView) rowView.findViewById(R.id.ListName);
        final TextView type = (TextView) rowView.findViewById(R.id.ListType);

        name.setText(elementos.get(position).getName());
        if (elementos.get(position).getType().equals("N")) {
            type.setText("Normal");
        } else {
            type.setText("Deficiente");
        }

        //Deixa "enable" como "false".
        name.setKeyListener(null);
        type.setKeyListener(null);

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Reservar");
        builder.setMessage("Deseja reservar essa vaga?");

        //Define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Reserva a vaga.
                dadosApi.execute();
                    name.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                return false;
                            }
                            return false;
                        }
                    });
                }
        });

        //Define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Não fazer nada.
            }
        });

        //cria o AlertDialog
        final AlertDialog alerta;
        alerta = builder.create();

        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Exibe
                    alerta.show();
                }
                return false;
            }
        });

        return rowView;
    }
}