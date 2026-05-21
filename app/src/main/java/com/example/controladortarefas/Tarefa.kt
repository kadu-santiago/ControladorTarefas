package com.example.controladortarefas

data class Tarefa(
    val id: Int = 0,
    val descricao: String,
    val responsavel: String,
    val status: Int = 0
)
