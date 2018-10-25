package com.manochio.mobile.tccapp.GET;

import com.manochio.mobile.tccapp.Model.Usuario;
import com.manochio.mobile.tccapp.Model.Vagas;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DadosGetApi{
   private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try{
            while ((line = r.readLine()) != null){
                total.append(line).append('\n');
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return total.toString();
    }

    private static String request(String stringUrl) throws IOException {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream((urlConnection.getInputStream()));
            return readStream(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<Vagas> verificarList() throws JSONException, IOException {
        String resposta = request("http://192.168.0.107/TCCApp/public/api/verifica-vagas");
        JSONArray jsonArray = new JSONArray(resposta);
        ArrayList<Vagas> s = new ArrayList<Vagas>();

        for (int i = 0; i < jsonArray.length(); i++){
            Vagas vagas = new Vagas();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            vagas.name = jsonObject.getString("name");
            vagas.type = jsonObject.getString("type");
            vagas.id   = jsonObject.getString("id");
            s.add(vagas);
        }
        return s;
   }

    public static ArrayList<Vagas> verificarListUser(int id) throws JSONException, IOException {
        String resposta = request("http://192.168.0.107/TCCApp/public/api/verifica-vagas-user/" + id);
        JSONArray jsonArray = new JSONArray(resposta);
        ArrayList<Vagas> s = new ArrayList<Vagas>();

        for (int i = 0; i < jsonArray.length(); i++){
            Vagas vagas = new Vagas();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            vagas.name = jsonObject.getString("name");
            vagas.type = jsonObject.getString("type");
            vagas.id   = jsonObject.getString("id");
            s.add(vagas);
        }
        return s;
    }

    public static ArrayList<Usuario> listUser() throws JSONException, IOException {
        String resposta = request("http://192.168.0.105/TCCApp/public/api/usuario");
        JSONArray jsonArray = new JSONArray(resposta);
        ArrayList<Usuario> s = new ArrayList<Usuario>();

        for (int i = 0; i < jsonArray.length(); i++){
            Usuario user = new Usuario();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            user.first_name = jsonObject.getString("first_name");
            user.email = jsonObject.getString("email");
            user.city  = jsonObject.getString("city");
            user.state = jsonObject.getString("state");
            s.add(user);
        }
        return s;
    }
}



