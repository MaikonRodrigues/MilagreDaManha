package com.example.maikon.milagedamanha;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CronometroActivity extends AppCompatActivity {

    ImageView imcro;



    private static TextView section_label;
    private static long initialTime;
    private static Handler handler;
    private static boolean isRunning;
    private static final long MILLIS_IN_SEC = 1000L, SEC_IN_HORAS = 3600, SECS_IN_MIN = 60;
    CharSequence tempoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);
        imcro = (ImageView) findViewById(R.id.imgcron);
        imcro.setImageBitmap(baixarImagem("http://www.ellego.com.br/webservice/MilagDaManha/imagens/6a10bbd480e4c5573d8f3af73ae0454b.jpg"));

        handler = new Handler();
        section_label = (TextView) findViewById(R.id.section_label);
        // configuracao do tao flutuante
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton fab = (FloatingActionButton)view;
                if(!isRunning) {
                    isRunning = true;
                    initialTime = System.currentTimeMillis();
                    fab.setImageResource(android.R.drawable.ic_media_pause);
                    handler.postDelayed(runnable, MILLIS_IN_SEC);
                }else{
                    isRunning = false;
                    fab.setImageResource(android.R.drawable.ic_media_play);
                    handler.removeCallbacks(runnable);
                    tempoAtual = section_label.getText();
                    section_label.setText(tempoAtual);
                }
            }
        });
    }

    private final static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long seconds = (System.currentTimeMillis() - initialTime) / MILLIS_IN_SEC;
                section_label.setText(String.format("%02d:%02d:%02d",seconds / SEC_IN_HORAS, seconds / SECS_IN_MIN, seconds % SECS_IN_MIN));
                handler.postDelayed(runnable, MILLIS_IN_SEC);
            }
        }
    };

    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem;
            endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



}
