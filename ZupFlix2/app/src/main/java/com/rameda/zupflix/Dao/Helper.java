package com.rameda.zupflix.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Helper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "db_filmes";
    public static String TABELA_FILMES = "tb_filmes";

    public Helper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql ="CREATE TABLE IF NOT EXISTS " + TABELA_FILMES +
                "(id_filme INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL,"+
                "ano TEXT NOT NULL," +
                "avaliacao TEXT NOT NULL," +
                "ator TEXT NOT NULL," +
                "descricao TEXT NOT NULL); ";

        try {
            db.execSQL( sql );
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_FILMES + " ;" ;

        try {
            db.execSQL( sql );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }

    }

}
