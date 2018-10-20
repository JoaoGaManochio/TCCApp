package com.manochio.mobile.tccapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.manochio.mobile.tccapp.Model.Usuario;
import com.manochio.mobile.tccapp.R;
import java.util.ArrayList;

public class UserAdpter extends ArrayAdapter<Usuario> {

    private final Context context;
    private ArrayList<Usuario> elementos;

    public UserAdpter (Context context, ArrayList<Usuario> elementos){
        super(context, R.layout.list_user, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_user, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.ListNameUser);
        TextView email = (TextView) rowView.findViewById(R.id.ListEmailUser);
        TextView cidade = (TextView) rowView.findViewById(R.id.ListCidadeUser);
        TextView estado = (TextView) rowView.findViewById(R.id.ListEstadoUser);
        TextView topo = (TextView) rowView.findViewById(R.id.ListNameUserTop);

        topo.setText(elementos.get(position).getFirst_name());
        name.setText("Nome: " + elementos.get(position).getFirst_name());
        email.setText("Email: " + elementos.get(position).getEmail());
        cidade.setText("Cidade: " + elementos.get(position).getCity());
        estado.setText("Estado: " + elementos.get(position).getState());

        //Deixa "enable" como "false".
        topo.setKeyListener(null);
        name.setKeyListener(null);
        email.setKeyListener(null);
        cidade.setKeyListener(null);
        estado.setKeyListener(null);

        return rowView;
    }
}
