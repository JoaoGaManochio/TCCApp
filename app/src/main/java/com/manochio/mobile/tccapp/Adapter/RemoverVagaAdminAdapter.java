package com.manochio.mobile.tccapp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manochio.mobile.tccapp.Model.Vagas;
import com.manochio.mobile.tccapp.POST.DadosApiRemoverVagas;
import com.manochio.mobile.tccapp.POST.DadosApiVagas;
import com.manochio.mobile.tccapp.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class RemoverVagaAdminAdapter extends ArrayAdapter<Vagas> {
    private final Context context;
    private ArrayList<Vagas> elementos;

    public RemoverVagaAdminAdapter (Context context, ArrayList<Vagas> elementos) throws IOException, JSONException {
        super(context, R.layout.vagas_dis, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final String id_vagas = elementos.get(position).getId();
        String url = "http://192.168.0.105/TCCApp/public/api/remover-vagas/" + id_vagas;
        final DadosApiRemoverVagas dadosApi = new DadosApiRemoverVagas(context, url, id_vagas);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.vagas_dis, parent, false);

        final TextView name = (TextView) rowView.findViewById(R.id.ListName);
        final TextView type = (TextView) rowView.findViewById(R.id.ListType);

        name.setText(elementos.get(position).getName());
        if(elementos.get(position).getType().equals("N")){
            type.setText("Normal");
        }
        else {
            type.setText("Deficiente");
        }

        //Deixa "enable" como "false".
        name.setKeyListener(null);
        type.setKeyListener(null);

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Remover");
        builder.setMessage("Deseja remover essa vaga?");

        //Define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Reserva a vaga.
                dadosApi.execute();
                type.setText("Removida");
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
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //Exibe
                    alerta.show();
                }
                return false;
            }
        });
        return rowView;
    }
}
