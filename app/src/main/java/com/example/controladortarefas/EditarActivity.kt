package com.example.controladortarefas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditarActivity : AppCompatActivity() {

    private lateinit var edtDescricao: EditText
    private lateinit var edtResponsavel: EditText
    private lateinit var seekBarStatus: SeekBar
    private lateinit var tvPorcentagem: TextView
    private lateinit var btnSalvarEdicao: Button

    private var tarefaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        edtDescricao = findViewById(R.id.edtDescricaoEditar)
        edtResponsavel = findViewById(R.id.edtResponsavelEditar)
        seekBarStatus = findViewById(R.id.seekBar_statusEditar)
        tvPorcentagem = findViewById(R.id.tv_porcentagemEditar)
        btnSalvarEdicao = findViewById(R.id.btnSalvarEdicao)

        seekBarStatus.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvPorcentagem.text = "$progress%"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        tarefaId = intent.getIntExtra("TAREFA_ID", -1)
        val descricaoOriginal = intent.getStringExtra("TAREFA_DESCRICAO")
        val responsavelOriginal = intent.getStringExtra("TAREFA_RESPONSAVEL")
        val statusOriginal = intent.getIntExtra("TAREFA_STATUS", 0)

        edtDescricao.setText(descricaoOriginal)
        edtResponsavel.setText(responsavelOriginal)
        seekBarStatus.progress = statusOriginal
        tvPorcentagem.text = "$statusOriginal%"

        btnSalvarEdicao.setOnClickListener {
            val novaDescricao = edtDescricao.text.toString()
            val novoResponsavel = edtResponsavel.text.toString()
            val novoStatus = seekBarStatus.progress

            if (novaDescricao.isNotEmpty() && novoResponsavel.isNotEmpty()) {
                val db = TarefaHelper(this)
                val tarefaAtualizada = Tarefa(tarefaId, novaDescricao, novoResponsavel, novoStatus)
                val linhasAfetadas = db.atualizar(tarefaAtualizada)

                if (linhasAfetadas > 0) {
                    Toast.makeText(this, "Tarefa atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao atualizar a tarefa.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}