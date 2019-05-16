package com.example.maikon.milagedamanha.Classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;

import java.util.Date;

public class Post {

    private String descricao, nomeUser, dadoImg, dadoImg2;
    private int tempMilagre, qtdEstrelas, id;
    private String dataPost;
    private Image imagemUser, imagePost;
    private Bitmap imagem, imagem2;

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
    public void setDadoImg2(String dadoImg) {
        this.dadoImg2 = dadoImg;
        try{
            byte[] byteCode = Base64.decode(dadoImg, Base64.DEFAULT);
            this.imagem2 = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getImagem() {
        return imagem;
    }
    public Bitmap getImagem2() {
        return imagem2;
    }

    public void setImagem2(Bitmap imagem2) {
        this.imagem2 = imagem2;
    }
    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public Image getImagePost() {
        return imagePost;
    }

    public String getDadoImg2() {
        return dadoImg2;
    }
    public void setImagePost(Image imagePost) {
        this.imagePost = imagePost;
    }








    // get and set do nome do usuario
    public String getNomeUser() {
        return nomeUser;
    }
    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    // get and set do id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // get and set da descricao
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // get and set do tempo do milagre
    public int getTempMilagre() {
        return tempMilagre;
    }
    public void setTempMilagre(int tempMilagre) {
        this.tempMilagre = tempMilagre;
    }

    // get and set das estrelas
    public int getQtdEstrelas() {
        return qtdEstrelas;
    }
    public void setQtdEstrelas(int qtdEstrelas) {
        this.qtdEstrelas = qtdEstrelas;
    }

    //  Get and set da data do post
    public String getDataPost() {
        return dataPost;
    }
    public void setDataPost(String dataPost) {
        this.dataPost = dataPost;
    }




}
