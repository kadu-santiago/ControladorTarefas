package com.example.controladortarefas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(private val listaTarefas: List<Tarefa>) :
    RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_adapter, parent, false)
        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]

        holder.txtDescricao.text = tarefa.descricao
        holder.txtResponsavel.text = "Responsável: ${tarefa.responsavel}"
        holder.txtStatus.text = "Progresso: ${tarefa.status}%"
    }

    override fun getItemCount() = listaTarefas.size

    class TarefaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtDescricao = view.findViewById<TextView>(R.id.txtDescricaoItem)
        val txtResponsavel = view.findViewById<TextView>(R.id.txtResponsavelItem)
        val txtStatus = view.findViewById<TextView>(R.id.txtStatusItem)
    }
}