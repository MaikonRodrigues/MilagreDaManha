package com.example.maikon.milagedamanha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.maikon.milagedamanha.Adapters.MyFragmentPageAdapter;
import com.example.maikon.milagedamanha.Classes.VolleySingleton;
import com.example.maikon.milagedamanha.Classes.User;
import com.example.maikon.milagedamanha.Fragmentos.Fragmento_A;
import com.example.maikon.milagedamanha.Fragmentos.Fragmento_B;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Response.ErrorListener, Response.Listener<JSONObject> {
    Intent itget;
    String emailUser, idLogado, nomeLogado;
    ProgressDialog progresso;
    JsonObjectRequest jsonObjectReq;
    User usuarioLogado;
    RequestQueue request;
    TextView nomeUser, emailUserLog;
    TextView nav_user, nav_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // verificacao do usuario logado
        SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
        boolean jaLogou = prefs.getBoolean("estaLogado", false);

        if(jaLogou) {
            // chama a tela inicial
        }else {
            // chama a tela de login
            Intent intent = new Intent(this, LoginCadastroActivity.class);
            startActivity(intent);
        }

        itget = getIntent();
        idLogado = "82"; //itget.getStringExtra("id");
        nomeLogado = "Usuario"; //itget.getStringExtra("nome");
        emailUser = "";
        recuperarUser(idLogado);   //  Recuperando User pelo email

        // pegando textveiws do menu lateral
        nomeUser = (TextView)findViewById(R.id.nomeUser);
        emailUserLog = (TextView)findViewById(R.id.emailUser);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);
        nav_user = (TextView)hView.findViewById(R.id.nomeUser);
        nav_email = (TextView)hView.findViewById(R.id.emailUser);

        MyFragmentPageAdapter adapter = new MyFragmentPageAdapter( getSupportFragmentManager() );
        adapter.adicionar( new Fragmento_A(), "Seguindo");
        adapter.adicionar( new Fragmento_B(), "Você");

       ViewPager viewPager = (ViewPager) findViewById(R.id.abas_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.abas);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void recuperarUser(String id) {

        progresso = new ProgressDialog(this);
        progresso.setMessage("Carregando...");
        progresso.show();

        String url = "http://www.ellego.com.br/webservice/MilagDaManha/recUser.php"; // armazena o caminho do webservice no servidor

        jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        //request.add(jsonObjectReq);
        VolleySingleton.getIntanciaVolley(this).addToRequestQueue(jsonObjectReq);
    }

    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();

        //Toast.makeText(getApplicationContext(), "Busca concluida" , Toast.LENGTH_SHORT).show();
        usuarioLogado = null;
        JSONArray json = response.optJSONArray("users"); //passo o objeto para ter acesso as instancias


        try {

            for(int i = 0; i<json.length(); i++){
                usuarioLogado = new User();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                usuarioLogado.setNome(jsonObject.optString("nome"));
                usuarioLogado.setId(jsonObject.optInt("idusers"));
                usuarioLogado.setEmail(jsonObject.optString("email"));

               // Toast.makeText(getApplicationContext(), "id User logado e = "+usuarioLogado.getId() , Toast.LENGTH_SHORT).show();
            }
            nav_user.setText(usuarioLogado.getNome());
            nav_email.setText(usuarioLogado.getEmail());


            progresso.hide();


        } catch (JSONException e){
            progresso.hide();

            Toast.makeText(getApplicationContext(), "Não foi possível listar "+response , Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();

        Toast.makeText(getApplicationContext(), "Não foi possível listar " +error.toString() , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, LoginCadastroActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_criapost) {
            Intent intent = new Intent(this, CriaPostActivity.class).putExtra("id", idLogado);
            intent.putExtra("nome", nomeLogado);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
