package com.example.maikon.milagedamanha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maikon.milagedamanha.CriaPostActivity;
import com.example.maikon.milagedamanha.GravarDesafioActivity;
import com.example.maikon.milagedamanha.MainActivity;
import com.example.maikon.milagedamanha.R;

import com.example.maikon.milagedamanha.Classes.Desafio;
import com.example.maikon.milagedamanha.R;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.example.maikon.milagedamanha.R.layout.item_list_desafios_layout;

import java.util.List;


public class Desafios_Adapter extends RecyclerView.Adapter<Desafios_Adapter.DesafioHolder> {

    List<Desafio> listDesafio;
    Context context;
    String id;
    public Desafios_Adapter(List<Desafio> listDesaf, Context context){
        this.listDesafio = listDesaf;
        this.context = context;
    }


    @NonNull
    @Override
    public DesafioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista =  LayoutInflater.from(parent.getContext()).inflate(item_list_desafios_layout, parent, false);

        final RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        vista.setLayoutParams(layoutParams);
        return new Desafios_Adapter.DesafioHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull final DesafioHolder bebHolder, final int position) {

        id = listDesafio.get(position).getId();
        bebHolder.campoNome.setText(listDesafio.get(position).getNome());
        bebHolder.campoDuracao.setText(listDesafio.get(position).getDuracao());

        bebHolder.meuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getItemCount(); i++){
                    if (position == i){
                        Toast.makeText(v.getContext(),"Clicou no desafio", Toast.LENGTH_SHORT).show();     //Aparece quando o card e precionado
                        Intent intent = new Intent( context, GravarDesafioActivity.class).putExtra("id", id);
                        context.startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listDesafio.size();
    }

    public class DesafioHolder extends RecyclerView.ViewHolder {

        TextView campoDuracao, campoNome;
        CardView meuCard;

        public DesafioHolder(@NonNull View itemView) {
            super(itemView);
            meuCard      = (CardView) itemView.findViewById(R.id.card_viewDesaf);
            campoNome    = (TextView) itemView.findViewById(R.id.desf_nome);
            campoDuracao = (TextView) itemView.findViewById(R.id.desf_duracao);


        }
    }
}