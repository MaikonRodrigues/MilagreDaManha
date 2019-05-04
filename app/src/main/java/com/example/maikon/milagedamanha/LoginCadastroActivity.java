package com.example.maikon.milagedamanha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.maikon.milagedamanha.Fragmentos.Fragmento_Cadastro;
import com.example.maikon.milagedamanha.Fragmentos.Fragmento_Login;

public class LoginCadastroActivity extends FragmentActivity {
    Intent it;
    int getExtra, a;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cadastro);
        it = getIntent();

        getExtra = it.getIntExtra("set", a);

        if (getExtra == 1){
            Toast.makeText(this, "Realize seu Cadastro" , Toast.LENGTH_SHORT).show();
            if (savedInstanceState == null){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frameLayout, new Fragmento_Cadastro()).commit();
            }
        }else{
            if (savedInstanceState == null){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frameLayout, new Fragmento_Login()).commit();
            }
        }



    }
}