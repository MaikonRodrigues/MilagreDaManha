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

       // holder.nomeUser.setText(listPost.get(position).getNomeUser());
        //holder.postData.setText((CharSequence) listPost.get(position).getDataPost());
        holder.descricao.setText(listPost.get(position).getDescricao());


        if (listPost.get(position).getImagem() != null){

            listPost.get(position).setDadoImg(listPost.get(position).getDadoImg());

            holder.imagemPost.setImageBitmap(listPost.get(position).getImagem());

        }else {
            holder.imagemPost.setImageResource(R.drawable.sem_foto);
        }


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
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        CardView meuCard;
        TextView nomeUser, postData, descricao;
        ImageView imagemPost;
        public PostHolder(@NonNull View itemView) {
            super(itemView);

            meuCard = (CardView) itemView.findViewById(R.id.card_view);
            nomeUser = (TextView) itemView.findViewById(R.id.text_nomeUser);
            postData = (TextView) itemView.findViewById(R.id.text_dataPost);
            descricao = (TextView) itemView.findViewById(R.id.text_descricao);

            imagemPost = (ImageView)itemView.findViewById(R.id.imagePost);

        }
    }


}
