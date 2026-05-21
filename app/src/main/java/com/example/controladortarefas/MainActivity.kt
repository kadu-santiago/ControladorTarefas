package com.example.controladortarefas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnAdd =  findViewById<Button>(R.id.btnInserir)
        val btnEdit = findViewById<Button>(R.id.btnEditar)
        val btnList = findViewById<Button>(R.id.btnVisualizar)
        val btnDel = findViewById<Button>(R.id.btnDeletar)

        btnAdd.setOnClickListener {
            val intent = Intent(this, AdicionarActivity::class.java)
            startActivity(intent)
        }

    }
}