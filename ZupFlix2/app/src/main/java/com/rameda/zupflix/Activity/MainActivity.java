package com.rameda.zupflix.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rameda.zupflix.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleImageView imagebusca;
        imagebusca  = findViewById(R.id.item_seach);




    }

    public void chamabusca(View view) {
        Intent chamabusca = new Intent(getApplicationContext(), Busca.class);
        startActivity(chamabusca);
    }
}
