package com.example.maikon.milagedamanha.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maikon.milagedamanha.Classes.DownloadImageFromInternet;
import com.example.maikon.milagedamanha.Classes.Post;
import com.example.maikon.milagedamanha.R;

import java.util.List;

public class Post_Adapter extends RecyclerView.Adapter<Post_Adapter.PostHolder> {

    Context mContext;
    List<Post> listPost;

    public Post_Adapter(List<Post> listPost) {
        this.listPost = listPost;
    }

    public Post_Adapter(Context context, List<Post> post) {
        this.mContext = context;
        this.listPost = post;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_layout, parent, false);

        final RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        vista.setLayoutParams(layoutParams);
        return new PostHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, final int position) {

        holder.nomeUser.setText(listPost.get(position).getNomeUser());
        holder.descricao.setText(listPost.get(position).getDescricao());
        holder.postData.setText(listPost.get(position).getDataPost());

        /* Tratamento de erro para falta de imagem nos post pegando longBlob
        if (listPost.get(position).getImagem() != null){

            listPost.get(position).setDadoImg(listPost.get(position).getDadoImg());
            holder.imagemPost.setImageBitmap(listPost.get(position).getImagem());

        }else {
            holder.imagemPost.setImageResource(R.drawable.sem_foto);
        }

        if (listPost.get(position).getUrlImagem().equals("")) {

        }else{
            // setando imagem do post
            new DownloadImageFromInternet((ImageView) holder.imagemPost)
                    .execute(listPost.get(position).getUrlImagem());
        }

        if (listPost.get(position).getUserUrlImagem().equals(" ")) {

        }else{
            // setando imagem do usuario no post
            new DownloadImageFromInternet((ImageView) holder.imgUser)
                    .execute(listPost.get(position).getUserUrlImagem());

        }*/
            //holder.imagemPost.setImageBitmap(listPost.get(position).getImagem());



        /*  Tratamento de erro para falta de imagem dos usuarios
        if (listPost.get(position).getImagem2() != null){

            listPost.get(position).setDadoImg2(listPost.get(position).getDadoImg2());
            holder.imgUser.setImageBitmap(listPost.get(position).getImagem2());

        }else {
            holder.imgUser.setImageResource(R.drawable.com_facebook_profile_picture_blank_square);
        }*/


        holder.meuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getItemCount(); i++){
                    if (position == i){
                        Toast toast = Toast.makeText(v.getContext(),
                                "Clicou no Post", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });

         holder.imgLike.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getItemCount(); i++) {
                    if (position == i) {
                        Toast toast = Toast.makeText(v.getContext(),
                                "Like", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });

        holder.imgComent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getItemCount(); i++) {
                    if (position == i) {
                        Toast toast = Toast.makeText(v.getContext(),
                                "Comentario", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        CardView meuCard;
        TextView nomeUser, postData, descricao;
        ImageView imagemPost, imgUser, imgLike, imgComent;
        public PostHolder(@NonNull View itemView) {
            super(itemView);

            meuCard = (CardView) itemView.findViewById(R.id.card_view);
            nomeUser = (TextView) itemView.findViewById(R.id.text_nomeUser);
            postData = (TextView) itemView.findViewById(R.id.text_dataPost);
            descricao = (TextView) itemView.findViewById(R.id.text_descricao);

            imagemPost = (ImageView)itemView.findViewById(R.id.imagePost);
            imgUser = (ImageView)itemView.findViewById(R.id.item_fotoUser);

            imgLike = (ImageView)itemView.findViewById(R.id.imgLike);
            imgComent = (ImageView)itemView.findViewById(R.id.imgComent);

        }
    }


}
