package com.example.maikon.milagedamanha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.maikon.milagedamanha.Adapters.MyFragmentPageAdapter;
import com.example.maikon.milagedamanha.Fragmentos.Fragmento_A;
import com.example.maikon.milagedamanha.Fragmentos.Fragmento_B;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{//, Response.ErrorListener, Response.Listener<JSONObject> {

    String emailUser, nomeLogado;
    RequestQueue request;
    TextView nomeUser;
    TextView nav_user, nav_email;
    int idLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // verificacao do usuario logado
        SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", MODE_PRIVATE);
        boolean jaLogou = prefs.getBoolean("estaLogado", false);


        if(jaLogou) {
            // chama a tela inicial

        }else {
            // chama a tela de login
            Intent intent = new Intent(this, LoginCadastroActivity.class);
            startActivity(intent);
        }

        idLogado   =  prefs.getInt("id", 0);
        nomeLogado =  prefs.getString("nome", "sem nome");
        emailUser  =  prefs.getString("email", "sem nome");


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
        // pegando textveiws do menu lateral
        nav_user = (TextView)hView.findViewById(R.id.nomeUser);
        nav_email = (TextView)hView.findViewById(R.id.emailUser);

        nav_user.setText(prefs.getString("nome",""));
        nav_email.setText(prefs.getString("email",""));

        MyFragmentPageAdapter adapter = new MyFragmentPageAdapter( getSupportFragmentManager() );
        adapter.adicionar( new Fragmento_A(), "Seguindo");
        adapter.adicionar( new Fragmento_B(), "Você");

        ViewPager viewPager = (ViewPager) findViewById(R.id.abas_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.abas);
        tabLayout.setupWithViewPager(viewPager);
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
            Intent intent = new Intent(this, CriaPostActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.nav_grav_atividade) {
            // Handle the camera action
        } else if (id == R.id.nav_desafios) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
