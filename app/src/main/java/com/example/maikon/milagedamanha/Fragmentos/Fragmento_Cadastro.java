package com.example.maikon.milagedamanha.Fragmentos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.maikon.milagedamanha.Classes.User;
import com.example.maikon.milagedamanha.Classes.VolleySingleton;
import com.example.maikon.milagedamanha.MainActivity;
import com.example.maikon.milagedamanha.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Fragmento_Cadastro extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    View v;
    EditText email, senha, confSenha, nome;
    Button btnCadastrar;
    Intent itget;
    ProgressDialog progresso;
    JsonObjectRequest jsonObjectReq;
    User usuarioLogado;
    String idLogado;
    ImageView imgUser;
    Bitmap bitmap = null;

    private static final int COD_SELECIONA = 10;


    private static final String REGISTER_URL="http://www.ellego.com.br/webservice/MilagDaManha/regUser.php";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmento_cadastro, container, false);

        usuarioLogado = new User();
        nome = (EditText) v.findViewById(R.id.nome);
        email = (EditText) v.findViewById(R.id.email);
        senha = (EditText)  v.findViewById(R.id.password);
        confSenha = (EditText) v.findViewById(R.id.confirmPassword);
        btnCadastrar = (Button) v.findViewById(R.id.btnCadastrar);
        imgUser = (ImageView) v.findViewById(R.id.imgUser);

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, COD_SELECIONA);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nome.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                        senha.getText().toString().isEmpty() || confSenha.getText().toString().isEmpty() ){
                    Toast.makeText(v.getContext(), "Todos os campos são obrigatorios", Toast.LENGTH_SHORT).show();
                }else {

                        if (senha.getText().toString().equals(confSenha.getText().toString())){
                            cadastrar();
                            //registerUser();
                        }else{
                            Toast.makeText(v.getContext(), "As senhas não são iguais", Toast.LENGTH_SHORT).show();
                        }
                    

                }
            }
        });
        return v;
    }

    private void cadastrar() {

        progresso = new ProgressDialog(getContext());
        progresso.setMessage("Carregando...");
        progresso.show();
        usuarioLogado.setEmail(email.getText().toString());
        String url = "http://www.ellego.com.br/webservice/MilagDaManha/registroUser.php?nome="+nome.getText().toString()+"&email="+email.getText().toString()+"&senha="
                +senha.getText().toString(); // armazena o caminho do webservice no servidor
        url.replace(" ", "%20");
        jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        //request.add(jsonObjectReq);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectReq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();
        Toast.makeText( v.getContext(), "Nao foi possivel conectar ao servidor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();
        JSONArray json = response.optJSONArray("users");
        JSONObject jsonObject = null;
        try {
            jsonObject = json.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText( v.getContext(), "Cadastrado com sucesso ", Toast.LENGTH_SHORT).show();


        Intent it = new Intent(v.getContext(), MainActivity.class).putExtra("id", jsonObject.optString("idusers"));
        it.putExtra("nome", jsonObject.optString("nome") );
        startActivity(it);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case COD_SELECIONA:
                Uri tabPost = data.getData();
                imgUser.setImageURI(tabPost);

                try {
                    bitmap=MediaStore.Images.Media.getBitmap(v.getContext().getContentResolver(),tabPost);
                    imgUser.setVisibility(View.VISIBLE);
                    imgUser.setImageBitmap(bitmap);
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