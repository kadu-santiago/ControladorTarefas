package com.example.controladortarefas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListarActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)
    }

    override fun onResume(){
        super.onResume()
        val helper = TarefaHelper(this)
        val rv = findViewById<RecyclerView>(R.id.rvTarefas)

        val lista = helper.listarTarefas()

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = TarefaAdapter(lista)

    }
}