package com.example.controladortarefas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.concurrent.atomics.AtomicArray

class TarefaHelper (context: Context):
    SQLiteOpenHelper(context, "ger_tarefa.db", null, 1){

    companion object{
        const val TABLE_NAME = "tarefas"
        const val COL_ID = "id"
        const val COL_DESCRICAO = "descricao"
        const val COL_RESP = "responsavel"
        const val COL_STATUS = "status"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_DESCRICAO TEXT,
                $COL_RESP TEXT,
                $COL_STATUS INTEGER
            )
        """
            db.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVer: Int, newVer: Int){
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    fun inserirTarefa(tarefa: Tarefa): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_DESCRICAO, tarefa.descricao)
        contentValues.put(COL_RESP, tarefa.responsavel)
        contentValues.put(COL_STATUS, tarefa.status)

        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result
    }

    fun listarTarefas(): ArrayList<Tarefa>{
        val lista = ArrayList<Tarefa>()
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
                val desc = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESCRICAO))
                val resp = cursor.getString(cursor.getColumnIndexOrThrow(COL_RESP))
                val status = cursor.getInt(cursor.getColumnIndexOrThrow(COL_STATUS))

                lista.add(Tarefa(id, desc, resp, status))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun atualizarTarefa(tarefa: Tarefa): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_DESCRICAO, tarefa.descricao)
        contentValues.put(COL_RESP, tarefa.responsavel)
        contentValues.put(COL_STATUS, tarefa.status)

        val sucesso = db.update(TABLE_NAME, contentValues,
            "$COL_ID = ?",
            arrayOf(tarefa.id.toString()))
        db.close()
        return sucesso
    }

    fun deletarTarefa(id: Int): Int{
        val db = this.writableDatabase
        val sucesso = db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(id.toString()))
        db.close()
        return sucesso
    }
}