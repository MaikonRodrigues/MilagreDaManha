package com.example.maikon.milagedamanha.Classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;

public class Desafio {

   private String nome, duracao, id;
   private boolean etapa1, etapa2, etapa3, etapa4, etapa5;

    public Desafio() {    }

    public Desafio(String id, String nome, String duracao, boolean etapa1, boolean etapa2, boolean etapa3, boolean etapa4, boolean etapa5) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.etapa1 = etapa1;
        this.etapa2 = etapa2;
        this.etapa3 = etapa3;
        this.etapa4 = etapa4;
        this.etapa5 = etapa5;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public boolean isEtapa1() {
        return etapa1;
    }

    public void setEtapa1(boolean etapa1) {
        this.etapa1 = etapa1;
    }

    public boolean isEtapa2() {
        return etapa2;
    }

    public void setEtapa2(boolean etapa2) {
        this.etapa2 = etapa2;
    }

    public boolean isEtapa3() {
        return etapa3;
    }

    public void setEtapa3(boolean etapa3) {
        this.etapa3 = etapa3;
    }

    public boolean isEtapa4() {
        return etapa4;
    }

    public void setEtapa4(boolean etapa4) {
        this.etapa4 = etapa4;
    }

    public boolean isEtapa5() {
        return etapa5;
    }

    public void setEtapa5(boolean etapa5) {
        this.etapa5 = etapa5;
    }
}
