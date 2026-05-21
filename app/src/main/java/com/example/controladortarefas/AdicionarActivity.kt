package com.example.controladortarefas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class AdicionarActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)

        val helper = TarefaHelper(this)
        val btn = findViewById<Button>(R.id.btnSalvar)

        btn.setOnClickListener {
            val tarefa = Tarefa(
                descricao = findViewById<EditText>(R.id.edtDescricao).text.toString(),
                responsavel = findViewById<EditText>(R.id.edtResponsavel).text.toString(),
                status = findViewById<Spinner>(R.id.spnStatus)

            )
        }
    }
}