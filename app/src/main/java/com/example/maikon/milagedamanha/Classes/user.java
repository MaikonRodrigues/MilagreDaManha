package com.example.maikon.milagedamanha.Classes;

import android.app.ProgressDialog;
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

import java.text.BreakIterator;

public class user implements Response.ErrorListener, Response.Listener<JSONObject> {
    int id;
    String nome, email, dadoImg;
    JsonObjectRequest jsonObjectReq;
    View v;
    private Image imagemUser;
    private Bitmap imagem;

    public user(int id, String nome, String email, String dadoImg, JsonObjectRequest jsonObjectReq, View v, Image imagemUser, Bitmap imagem) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dadoImg = dadoImg;
        this.jsonObjectReq = jsonObjectReq;
        this.v = v;
        this.imagemUser = imagemUser;
        this.imagem = imagem;
    }

    private user recuperarUser(String id, user user) {

        if (id != "0") {
            String url = "http://www.ellego.com.br/webservice/MilagDaManha/recUser.php?id=" + id; // armazena o caminho do webservice no servidor

            jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            VolleySingleton.getIntanciaVolley(v.getContext()).addToRequestQueue(jsonObjectReq);

        } else {
            return user;
        }
        return user;
    }
    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("user");
        for(int i = 0; i<json.length(); i++){

            user user = new user();
            JSONObject jsonObject = null;
            try {
                jsonObject = json.getJSONObject(i);
                user.setId(jsonObject.optInt("id"));
                user.setNome(jsonObject.getString("nome"));
                user.setDadoImg(jsonObject.getString("imagem"));
                user.setEmail(jsonObject.getString("email"));

                recuperarUser( "0", user);
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

    public user() {

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
