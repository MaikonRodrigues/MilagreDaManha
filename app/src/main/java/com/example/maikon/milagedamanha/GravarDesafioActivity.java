package com.example.maikon.milagedamanha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GravarDesafioActivity extends AppCompatActivity {

    Intent intent;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravar_desafio);

        //  Pegando dados do id do desafio
        intent = getIntent();
        id = String.valueOf(intent.getExtras());

        // Teste para ver se o id esta sendo recebido corretamente
        Toast.makeText(this,"id recebido :"+id, Toast.LENGTH_LONG).show();
    }
}
