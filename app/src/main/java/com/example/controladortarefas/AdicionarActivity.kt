package com.example.controladortarefas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdicionarActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)

        val helper = TarefaHelper(this)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        val seekBarStatus = findViewById<SeekBar>(R.id.seekBar_status)
        val tvPorcentagem = findViewById<TextView>(R.id.tv_porcentagem)

        seekBarStatus.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvPorcentagem.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnSalvar.setOnClickListener {
            val tarefa = Tarefa(
                descricao = findViewById<EditText>(R.id.edtDescricao).text.toString(),
                responsavel = findViewById<EditText>(R.id.edtResponsavel).text.toString(),
                status = findViewById<SeekBar>(R.id.seekBar_status).progress
            )
            helper.inserirTarefa(tarefa)
            Toast.makeText(this, "Tarefa salva", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}