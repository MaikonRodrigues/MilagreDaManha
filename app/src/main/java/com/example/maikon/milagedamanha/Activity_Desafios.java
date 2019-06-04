package com.example.maikon.milagedamanha;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.maikon.milagedamanha.Adapters.Desafios_Adapter;
import com.example.maikon.milagedamanha.Classes.Desafio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Desafios extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    private RecyclerView recyclerDesafios;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Desafio> listaDesafio;
    ProgressDialog progresso;
    RequestQueue request;
    JsonObjectRequest jsonObjectReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafios);

        recyclerDesafios = (RecyclerView) findViewById(R.id.my_recycler_view_listEnd);
        recyclerDesafios.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerDesafios.setLayoutManager(layoutManager);
        recyclerDesafios.setItemAnimator(new DefaultItemAnimator());

        listaDesafio = new ArrayList<Desafio>();

        recyclerDesafios.setLayoutManager(new LinearLayoutManager(Activity_Desafios.this));
        //recyclerBebida.setHasFixedSize(true);
        request = Volley.newRequestQueue(getApplicationContext());
        carregarWebService();

    }

    private void carregarWebService() {

        progresso = new ProgressDialog(this);
        progresso.setMessage("Carregando...");
        progresso.show();

        String url = "http://www.ellego.com.br/webservice/MilagDaManha/listarDesafios.php"; // armazena o caminho do webservice no servidor

        jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectReq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();

        Toast.makeText(getApplicationContext(), "Não foi possível listar " +error.toString() , Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();

        Toast.makeText(getApplicationContext(), "Busca concluida" , Toast.LENGTH_SHORT).show();
        Desafio desafio = null;
        JSONArray json = response.optJSONArray("desafio"); //passo o objeto para ter acesso as instancias

        try {

            for(int i = 0; i<json.length(); i++){
                desafio = new Desafio();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                desafio.setId(jsonObject.optString("id_desafio"));
                desafio.setNome(jsonObject.optString("nome"));
                desafio.setDuracao(jsonObject.optString("duracao"));

                listaDesafio.add(desafio);

            }

            progresso.hide();
            Desafios_Adapter adapter = new Desafios_Adapter(listaDesafio, this);
            recyclerDesafios.setAdapter(adapter);

        } catch (JSONException e){
            progresso.hide();

            Toast.makeText(getApplicationContext(), "Não foi possível listar "+response , Toast.LENGTH_SHORT).show();

        }
    }
}
