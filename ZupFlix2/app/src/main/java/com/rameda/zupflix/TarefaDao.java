package com.rameda.zupflix;

import com.rameda.zupflix.Dao.Tarefa;

import java.util.List;

public interface TarefaDao {
    public boolean salvar (Tarefa tarefa);
    public boolean atuazlizar(Tarefa tarefa);
    public boolean deletar (Tarefa tarefa);
    public List<Tarefa>lista ();





}
