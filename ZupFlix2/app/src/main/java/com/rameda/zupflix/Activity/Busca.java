package com.rameda.zupflix.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rameda.zupflix.Adapter.Adapter;
import com.rameda.zupflix.Dao.DAO;
import com.rameda.zupflix.Dao.Tarefa;
import com.rameda.zupflix.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Busca extends AppCompatActivity {

    EditText ed_busca;
    Button btt_buscar;
    TextView tv_ano, tv_titulo, tv_ator, tv_descricao;
    public  static String valorBusca="";
    private SQLiteDatabase leitura;

    ImageView iv_poster;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoDeFilmes";
    private List<Tarefa> listFilmes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        carregaitensxml();

    }

    public void carregarListaTarefas() {

        DAO dao = new DAO(getApplicationContext());
        listFilmes = dao.lista();

        Adapter adapter = new Adapter(listFilmes);
/*
        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recicleFilmes.setLayoutManager(layoutManager);
        recicleFilmes.setHasFixedSize(true);
        recicleFilmes.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recicleFilmes.setAdapter(adapter);*/

    }


    private void carregaitensxml() {
        ed_busca = findViewById(R.id.ed_busca);
        ed_busca = findViewById(R.id.ed_busca);
        tv_titulo = findViewById(R.id.tv_Titulo);
        tv_ator = findViewById(R.id.tv_Ator);
        tv_descricao = findViewById(R.id.tv_Comentarios);
        tv_ano = findViewById(R.id.tv_anoLanc);
        btt_buscar = findViewById(R.id.seach);
        iv_poster = findViewById(R.id.iv_poster);
        //recicleFilmes = findViewById(R.id.rv_filmes);
    }

    private boolean verificaconexao() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    public void busca(View view) {

        verificaconexao();
        String url = "";
        String Name = ed_busca.getText().toString();

        if (Name.isEmpty()) {
            ed_busca.setError("insira um valor ");
        }
        if (verificaconexao() == true) {
            url = "http://www.omdbapi.com/?t=" + Name + "&plot=full&apikey=4e5c8b3d";
            buscananet(url);
        } else {

            ed_busca.setError("vc ta sem internet ");
            buscaoffline();

        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
        if (preferences.contains("titulo")) {


            String titulo = preferences.getString("titulo", "");
            String ano = preferences.getString("ano", "erro");
            String avaliacao = preferences.getString("avaliacao", "erro");
            String ator = preferences.getString("ator", "erro");
            String descricao = preferences.getString("descricao", "erro");

        }
    }








    private void buscaoffline() {

        Tarefa tarefa = new Tarefa();
        String titulo =tarefa.gettitulo();
        String ano = tarefa.getAno();
     //   String avaliacao = tarefa.getAvaliacao();
        String ator =tarefa.getAtor();
        String descricao = tarefa.getDescricao();
        DAO dao = new DAO(getApplicationContext());
        listFilmes = dao.lista();
        int i = 0;








        tv_titulo.setText("Titulo: " + titulo);
            tv_ano.setText("Ano: " + ano);
            tv_ator.setText("Atores: " + ator);
            tv_descricao.setText("Descrica: " + descricao);
        // Picasso.get().load(urlImagem).into(iv_poster);


    }

    private void buscananet(final String url) {
        if (url != null) {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject filme = new JSONObject(response);
                                String resultado = filme.getString("Response");

                                if (resultado.equals("True")) {

                                    String titulo = filme.getString("Title");
                                    String ano = filme.getString("Year");
                                    String avaliacao = filme.getString("Rated");
                                    String ator = filme.getString("Actors");
                                    String descricao = filme.getString("Plot");
                                    String urlImagem = filme.getString("Poster");


                                    tv_titulo.setText("Titulo: " + titulo);
                                    tv_ano.setText("Ano: " + ano);
                                    tv_ator.setText("Atores: " + ator);
                                    tv_descricao.setText("Descrica: " + descricao);
                                    Picasso.get().load(urlImagem).into(iv_poster);


                                    titulo = titulo.replaceAll("'", " ");
                                    ano = ano.replaceAll("'", " ");
                                    ator = ator.replaceAll("'", " ");
                                    descricao = descricao.replaceAll("'", " ");

                                    DAO dao = new DAO(getApplicationContext());
                                    Tarefa tarefa = new Tarefa();
                                    tarefa.settitulo(titulo);
                                    tarefa.setAno(ano);
                                    tarefa.setAvaliacao(avaliacao);
                                    tarefa.setAtor(ator);
                                    tarefa.setUrlImagem(urlImagem);
                                    tarefa.setDescricao(descricao);
                                    dao.salvar(tarefa);


                                    //          carregarListaTarefas();
                                    SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();


                                    editor.putString("titulo", titulo);
                                    editor.putString("ano", ano);
                                    editor.putString("avaliacao", avaliacao);
                                    editor.putString("ator", ator);
                                    editor.putString("descricao", descricao);
                                    editor.commit();


                                    // criarbanco(titulo, ano,avaliacao,ator, descricao,url);
                                }
                            } catch (JSONException e) {
                                Log.e("info", "errno no json" + e.getMessage());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            );
            queue.add(request);
        } else {

        }
    }

}

