package com.manochio.mobile.tccapp.POST;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class DadosApiRemoverVagas extends AsyncTask<Void, Void, String> {

    private Context httpContext; // Contexto
    ProgressDialog progressDialog; // dialogo carregando.
    public String resultadoApi="";
    public String linkResquestApi=""; //link para consumir o serviço api.
    public String id_vaga;

    public DadosApiRemoverVagas(Context ctx, String linkAPI, String id_vaga){
        this.httpContext = ctx;
        this.linkResquestApi = linkAPI;
        this.id_vaga = id_vaga;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Processando Solicitação", "Por favor espere!!");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoApi=s;
        Toast.makeText(httpContext, resultadoApi, Toast.LENGTH_SHORT).show(); // mostra uma notificação do resultado da request
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = null;

        String wsURL = linkResquestApi;
        URL url = null;
        try{
            // cria a conexão com a api
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //Cria o objeto Json para enviar por POST.
            JSONObject parametrosPost = new JSONObject();
            parametrosPost.put("id", id_vaga);


            // Definir parametros para conexao
            urlConnection.setReadTimeout(15000 /*Milissigundos*/);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            //Obter o resultadao da request
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = urlConnection.getResponseCode(); // conexão ok?
            String responseName = urlConnection.getResponseMessage();

            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String linea = "";
                while ((linea=in.readLine()) != null){
                    sb.append(linea);
                    break;
                }
                in.close();
                result = "Vaga removida!";
                //result= sb.toString();
            }
            else{
                result = "Erro:" + responseCode + " " + responseName;
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //Funções
    //Transformar Json Object a Strings
    public String getPostDataString(JSONObject params) throws Exception{

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();

        while (itr.hasNext()){
            String key = itr.next();
            Object value = params.get(key);

            if(first){
                first = false;
            }
            else{
                result.append("&");
            }
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}
