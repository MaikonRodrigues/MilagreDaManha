package com.example.maikon.milagedamanha.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.maikon.milagedamanha.LoginCadastroActivity;
import com.example.maikon.milagedamanha.MainActivity;
import com.example.maikon.milagedamanha.R;

import java.util.HashMap;
import java.util.Map;

public class Fragmento_Login extends Fragment {
    View v;
    TextView btnNaoTenhoConta;
    EditText email, senha;
    Button  btnLogar;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmento_login, container, false);

        email = (EditText) v.findViewById(R.id.email_login);
        senha = (EditText) v.findViewById(R.id.password_login);
        btnLogar = (Button) v.findViewById(R.id.btnLogar);
        btnNaoTenhoConta = (TextView) v.findViewById(R.id.btnNaotenhoConta);

        btnNaoTenhoConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(), LoginCadastroActivity.class).putExtra("set", 1);
                startActivity(intent);
            }
        });
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });
        return v;
    }

    private void logar() {
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://www.ellego.com.br/webservice/MilagDaManha/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("1")){
                            startActivity(new Intent(v.getContext(), MainActivity.class));
                        }else{
                            Toast.makeText(v.getContext(),"Email ou Senha com erro", Toast.LENGTH_SHORT);
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{

                Map<String, String> params = new HashMap<>();
                params.put("username", email.getText().toString());
                params.put("password", email.getText().toString());
                return params;
            }
        };
    }
}
