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
import com.manochio.mobile.tccapp.POST.DadosApiCancelarVagas;
import com.manochio.mobile.tccapp.R;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;

public class CancelarVagasAdapter extends ArrayAdapter<Vagas> {

    private final Context context;
    private ArrayList<Vagas> elementos;
    int id_user;

    public CancelarVagasAdapter (Context context, ArrayList<Vagas> elementos, int id_user) throws IOException, JSONException {
        super(context, R.layout.vagas_dis, elementos);
        this.context = context;
        this.elementos = elementos;
        this.id_user = id_user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final String id_vagas = elementos.get(position).getId();
        String url = "http://192.168.0.105/TCCApp/public/api/cancelar-vagas/" + id_vagas + "/" + id_user;
        final DadosApiCancelarVagas dadosApi = new DadosApiCancelarVagas(context, url, id_vagas, id_user);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.vagas_dis, parent, false);

        final TextView name = (TextView) rowView.findViewById(R.id.ListName);
        final TextView type = (TextView) rowView.findViewById(R.id.ListType);

        //Seta os dados pegos.
        name.setText(elementos.get(position).getName());
        if(elementos.get(position).getType().equals("N")){
            type.setText("Normal");
        }
        else {
            type.setText("Deficiente");
        }

        //Não deixa editar os campos.
        name.setKeyListener(null);
        type.setKeyListener(null);


        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Reservar");
        builder.setMessage("Deseja cancelar essa vaga?");

        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Cancela a vaga.
                dadosApi.execute();
                type.setText("Cancelada");
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

        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Não fazer nada.
            }
        });

        //cria o AlertDialog
        final AlertDialog alerta;
        alerta = builder.create();

        //Mostra a mensagem quando clica no campo.
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
