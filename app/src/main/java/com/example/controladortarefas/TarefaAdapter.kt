package com.example.controladortarefas

import android.content.Intent
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

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EditarActivity::class.java).apply {
                putExtra("TAREFA_ID", tarefa.id)
                putExtra("TAREFA_DESCRICAO", tarefa.descricao)
                putExtra("TAREFA_RESPONSAVEL", tarefa.responsavel)
                putExtra("TAREFA_STATUS", tarefa.status)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = listaTarefas.size

    class TarefaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtDescricao = view.findViewById<TextView>(R.id.txtDescricaoItem)
        val txtResponsavel = view.findViewById<TextView>(R.id.txtResponsavelItem)
        val txtStatus = view.findViewById<TextView>(R.id.txtStatusItem)
    }
}