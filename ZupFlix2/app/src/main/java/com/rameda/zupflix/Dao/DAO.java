package com.rameda.zupflix.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rameda.zupflix.TarefaDao;

import java.util.ArrayList;
import java.util.List;

public class DAO implements TarefaDao {

    private SQLiteDatabase escrever;
    private SQLiteDatabase leitura;

    public DAO(Context context) {
        Helper db = new Helper(context);
        escrever = db.getWritableDatabase();
        leitura = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        //String titulo =tarefa.getTituloAno();
        cv.put("titulo",tarefa.gettitulo());
        cv.put("ano",tarefa.getAno());
        cv.put("avaliacao",tarefa.getAvaliacao());
        cv.put("ator",tarefa.getAtor());
        cv.put("descricao",tarefa.getDescricao());

        try {
            escrever.insert(Helper.TABELA_FILMES,null,cv);
            Log.i("info","INSERIDO COM SUCESSO  ");
        }catch (Exception e){
            Log.e("info","erro ao salvar "+e.getMessage());
            return false;

        }

        return true;
    }

    @Override
    public boolean atuazlizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> lista() {

        List<Tarefa> listTarefa = new ArrayList<>();
        String sql = "SELECT * FROM "+ Helper.TABELA_FILMES + ";";
        Cursor cursor = leitura.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String ano = cursor.getString(cursor.getColumnIndex("ano"));
            String avaliacao = cursor.getString(cursor.getColumnIndex("avaliacao"));
            String ator = cursor.getString(cursor.getColumnIndex("ator"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));

            listTarefa.add(tarefa);

        }


        return listTarefa;
    }
}
