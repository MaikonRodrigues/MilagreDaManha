package com.example.maikon.milagedamanha.Classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;

import java.util.Date;

public class Post {

    private String descricao, nomeUser, dadoImg;
    private int tempMilagre, qtdEstrelas, id;
    private String dataPost;
    private Image imagemUser, imagePost;
    private Bitmap imagem;

    public Post() {
    }

    public Post(String descricao, String nomeUser, int tempMilagre, int qtdEstrelas, String dataPost, Image imagemUser, Image imagePost, int id) {
        this.descricao = descricao;
        this.nomeUser = nomeUser;
        this.tempMilagre = tempMilagre;
        this.qtdEstrelas = qtdEstrelas;
        this.dataPost = dataPost;
        this.imagemUser = imagemUser;
        this.imagePost = imagePost;
        this.id = id;
    }

    public String getDadoImg() {
        return dadoImg;
    }

    public void setDadoImg(String dadoImg) {
        this.dadoImg = dadoImg;
        try{
            byte[] byteCode = Base64.decode(dadoImg, Base64.DEFAULT);
            this.imagem = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public int getTempMilagre() {
        return tempMilagre;
    }

    public void setTempMilagre(int tempMilagre) {
        this.tempMilagre = tempMilagre;
    }

    public int getQtdEstrelas() {
        return qtdEstrelas;
    }

    public void setQtdEstrelas(int qtdEstrelas) {
        this.qtdEstrelas = qtdEstrelas;
    }

    public String getDataPost() {
        return dataPost;
    }

    public void setDataPost(String dataPost) {
        this.dataPost = dataPost;
    }

    public Image getImagemUser() {
        return imagemUser;
    }

    public void setImagemUser(Image imagemUser) {
        this.imagemUser = imagemUser;
    }

    public Image getImagePost() {
        return imagePost;
    }

    public void setImagePost(Image imagePost) {
        this.imagePost = imagePost;
    }
}
