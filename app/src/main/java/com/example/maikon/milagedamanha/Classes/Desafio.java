package com.example.maikon.milagedamanha.Classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;

public class Desafio {

   private String nome;
   private  int duracao;
   private boolean est1, est2, est3, est4, est5;

    public Desafio() {    }

    public Desafio(String nome, int duracao, boolean est1, boolean est2, boolean est3, boolean est4, boolean est5) {
        this.nome = nome;
        this.duracao = duracao;
        this.est1 = est1;
        this.est2 = est2;
        this.est3 = est3;
        this.est4 = est4;
        this.est5 = est5;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public boolean isEst1() {
        return est1;
    }

    public void setEst1(boolean est1) {
        this.est1 = est1;
    }

    public boolean isEst2() {
        return est2;
    }

    public void setEst2(boolean est2) {
        this.est2 = est2;
    }

    public boolean isEst3() {
        return est3;
    }

    public void setEst3(boolean est3) {
        this.est3 = est3;
    }

    public boolean isEst4() {
        return est4;
    }

    public void setEst4(boolean est4) {
        this.est4 = est4;
    }

    public boolean isEst5() {
        return est5;
    }

    public void setEst5(boolean est5) {
        this.est5 = est5;
    }
}
