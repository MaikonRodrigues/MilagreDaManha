package com.example.maikon.milagedamanha.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User implements Response.ErrorListener, Response.Listener<JSONObject> {
    int id;
    String nome, email, dadoImg;
    JsonObjectRequest jsonObjectReq;
    Context context;
    private Image imagemUser;
    private Bitmap imagem;

    public User(int id, String nome, String email, String dadoImg, Context context, Image imagemUser, Bitmap imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dadoImg = dadoImg;
        this.context = context;
        this.imagemUser = imagemUser;
        this.imagem = imagem;
    }

    public User recuperarUser(String id, User user) {

        if (id != "0") {
            String url = "http://www.ellego.com.br/webservice/MilagDaManha/recUser.php?id=" + id; // armazena o caminho do webservice no servidor

            jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            VolleySingleton.getIntanciaVolley(context).addToRequestQueue(jsonObjectReq);

        } else {
            return user;
        }
        return user;
    }
    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("User");
        for(int i = 0; i<json.length(); i++){

            User User = new User();
            JSONObject jsonObject = null;
            try {
                jsonObject = json.getJSONObject(i);
                User.setId(jsonObject.optInt("id"));
                User.setNome(jsonObject.getString("nome"));
                User.setDadoImg(jsonObject.getString("imagem"));
                User.setEmail(jsonObject.getString("email"));

                recuperarUser( "0", User);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }
    public String getDadoImg() {
        return dadoImg;
    }

    public void setDadoImg(String dadoImg) {
        this.dadoImg = dadoImg;
        try{
            byte[] byteCode = Base64.decode(dadoImg, Base64.DEFAULT);
            this.imagem = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public User() {

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
