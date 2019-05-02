package com.rameda.zupflix.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rameda.zupflix.Dao.Tarefa;
import com.rameda.zupflix.R;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Tarefa>listFilmes;

    public Adapter(List<Tarefa> listFilmes) {
        this.listFilmes = listFilmes;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemListaFilmes = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapterfilmes,viewGroup,false);

        return new MyViewHolder(itemListaFilmes);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {

        Tarefa tarefa = listFilmes.get(i);

        myViewHolder.titulo.setText(tarefa.gettitulo());
        myViewHolder.ano.setText(tarefa.getAno());
        myViewHolder.ator.setText(tarefa.getAtor());
        myViewHolder.avaliacao.setText(tarefa.getAvaliacao());
        myViewHolder.descricao.setText(tarefa.getDescricao());
       //myViewHolder.urlImagem.setImageBitmap(tarefa.getUrlImagem());
//        Log.i("tarefaAdapter", tarefa.gettitulo() );
    }

    @Override
    public int getItemCount() {
        return this.listFilmes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, ano, avaliacao, ator, descricao;
        ImageView urlImagem;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tv_titulo);
            ano = itemView.findViewById(R.id.tv_ano);
            avaliacao = itemView.findViewById(R.id.tv_avaliacao);
            ator = itemView.findViewById(R.id.tv_Ator);
            descricao = itemView.findViewById(R.id.tv_descricao);
            urlImagem = itemView.findViewById(R.id.iv_filme);
        }
    }

}
