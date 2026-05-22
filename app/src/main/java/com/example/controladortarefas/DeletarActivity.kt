package com.example.controladortarefas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeletarActivity : AppCompatActivity() {

    private lateinit var edtDescricaoParaDeletar: EditText
    private lateinit var btnBuscarEExcluir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletar)

        edtDescricaoParaDeletar = findViewById(R.id.edtDescricaoParaDeletar)
        btnBuscarEExcluir = findViewById(R.id.btnBuscarEExcluir)

        btnBuscarEExcluir.setOnClickListener {
            val descricaoDigitada = edtDescricaoParaDeletar.text.toString().trim()

            if (descricaoDigitada.isNotEmpty()) {
                val db = TarefaHelper(this)
                val idEncontrado = db.buscarIdPorDescricao(descricaoDigitada)

                if (idEncontrado != -1) {
                    val linhasAfetadas = db.deletarTarefa(idEncontrado)

                    if (linhasAfetadas > 0) {
                        Toast.makeText(this, "Tarefa excluída com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Erro ao tentar excluir no banco.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Tarefa não encontrada.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Por favor, digite uma descrição.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}