package com.example.maikon.milagedamanha.Fragmentos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.maikon.milagedamanha.Adapters.Post_Adapter;
import com.example.maikon.milagedamanha.Classes.Post;
import com.example.maikon.milagedamanha.Classes.User;
import com.example.maikon.milagedamanha.Classes.VolleySingleton;
import com.example.maikon.milagedamanha.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragmento_A extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    // senha 616254

    View v;
    RecyclerView myrecycleView;
    private List<Post> listPost;

    ProgressDialog progresso;
    JsonObjectRequest jsonObjectReq;
    Post_Adapter adapter;
    Post post;

    public Fragmento_A(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_a, container, false);
        myrecycleView = (RecyclerView) v.findViewById(R.id.my_recycler_view_sguindo);

        myrecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //myrecycleView.setAdapter(adapter);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        // Toda vez que a tela startar carrega os dados
        carregarWebService();
    }
    private void carregarWebService() {

        progresso = new ProgressDialog(getContext());
        progresso.setMessage("Carregando...");
        progresso.show();

        String url = "http://www.ellego.com.br/webservice/MilagDaManha/listarPost.php"; // armazena o caminho do webservice no servidor

        jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectReq);
        //request.add(jsonObjectReq);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();

        Toast.makeText(getContext(), "Não foi possível listar " +error.toString() , Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }

    public void onResponse(JSONObject response){

        progresso.hide();
        listPost = new ArrayList<>();
        //Toast.makeText(getContext(), "Busca concluida" , Toast.LENGTH_SHORT).show();
        post = null;
        JSONArray json = response.optJSONArray("post"); //passo o objeto para ter acesso as instancias
        String idUserPost;
        try {

            for(int i = 0; i<json.length(); i++){


                post = new Post();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                post.setId(jsonObject.optInt("id"));
                post.setDescricao(jsonObject.getString("descricao"));
                post.setDadoImg(jsonObject.getString("imagem"));
                idUserPost = jsonObject.getString("users_idusers");


                User user = new User();
                User user2 = new User();
                user.setContext(v.getContext());
                user2 = user.recuperarUser(idUserPost, user );
                post.setNomeUser(user2.getNome());
                listPost.add(post);

            }

            progresso.hide();
            adapter = new Post_Adapter(getContext(),listPost);
            myrecycleView.setAdapter(adapter);

        } catch (JSONException e){
            progresso.hide();

            Toast.makeText(getContext(), "Não foi possível listar "+response , Toast.LENGTH_SHORT).show();

        }

    }

}
