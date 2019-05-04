package com.example.maikon.milagedamanha;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.maikon.milagedamanha.Classes.VolleySingleton;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class CriaPostActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    Button btnAddPost, btnAddFoto;
    EditText descricao;
    ImageView imagempost;
    Intent it;

    ProgressDialog progresso;
    RequestQueue request;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    private static final int COD_SELECIONA = 10;

    String users_idusers;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_post);

        it = getIntent();
        users_idusers = it.getStringExtra("id");

        request = Volley.newRequestQueue(CriaPostActivity.this);
        requestQueue = Volley.newRequestQueue(CriaPostActivity.this);

        btnAddPost = (Button) findViewById(R.id.btnEnviarPost);
        btnAddFoto = (Button) findViewById(R.id.btnAddFotos);

        descricao = (EditText) findViewById(R.id.textDescricaoPost);
        imagempost = (ImageView)findViewById(R.id.imageCriarPost);

        btnAddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, COD_SELECIONA);
            }
        });

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarWEBService();
            }
        });

    }
    private void carregarWEBService() {

        progresso = new ProgressDialog(this);
        progresso.setMessage("Carregando...");
        progresso.show();

        String url = "http://www.ellego.com.br/webservice/MilagDaManha/registrarPost.php";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progresso.hide();

                if (response.trim().equalsIgnoreCase("nao registra")) {

                    Toast.makeText(CriaPostActivity.this, "Registro não inserido, erro: " + response, Toast.LENGTH_SHORT).show();

                } else {
                    //Toast.makeText(CriaPostActivity.this, "Registrado com sucesso"+response, Toast.LENGTH_SHORT).show();
                    excluirDuplicata(response);
                    Intent it = new Intent(CriaPostActivity.this, MainActivity.class).putExtra("id", users_idusers);
                    startActivity(it);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CriaPostActivity.this, "Erro ao Registrar erro: "+ error, Toast.LENGTH_SHORT).show();
                progresso.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams()  throws AuthFailureError {

                String descr = descricao.getText().toString();
                String imagem = converterImgString(bitmap);
                Map<String,String> parametros = new HashMap<>();
                parametros.put("descricao", descr);
                parametros.put("users_idusers", users_idusers);
                parametros.put("imagem", imagem);

                return parametros;
            }

        };

       // requestQueue.add(stringRequest);
        VolleySingleton.getIntanciaVolley(this).addToRequestQueue(stringRequest);

    }
    public void excluirDuplicata(String id){

        JsonObjectRequest jsonObjectReq1;
        String url = "http://www.ellego.com.br/webservice/MilagDaManha/deletePost.php?id="+id; // armazena o caminho do webservice no servidor

        jsonObjectReq1 = new JsonObjectRequest(Request.Method.GET, url, null, this,this);

        VolleySingleton.getIntanciaVolley(this).addToRequestQueue(jsonObjectReq1);

    }
    @Override
    public void onResponse(JSONObject response) {
        //Toast.makeText(CriaPostActivity.this, "Duplicata excluida", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //Toast.makeText(CriaPostActivity.this, "Duplicata nao foi excluida", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case COD_SELECIONA:
                Uri tabPost = data.getData();
                imagempost.setImageURI(tabPost);

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),tabPost);
                    imagempost.setVisibility(View.VISIBLE);
                    imagempost.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    private String converterImgString(Bitmap bitmap) {

        ByteArrayOutputStream array=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagemByte=array.toByteArray();
        String imagemString= Base64.encodeToString(imagemByte,Base64.DEFAULT);

        return imagemString;
    }



}
