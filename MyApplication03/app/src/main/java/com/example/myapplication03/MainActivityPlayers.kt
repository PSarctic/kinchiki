package com.example.myapplication03

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivityPlayers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_players)
        val label: TextView = findViewById(R.id.label_01)
        val userData: TextView = findViewById(R.id.user_data)
        val listView: ListView = findViewById(R.id.list_view)
        val addName: Button = findViewById(R.id.add_gamer)
        val back: Button = findViewById(R.id.back)

        val movies: MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movies)

        listView.adapter = adapter
        listView.setOnItemClickListener{ adapterView, view, i, l ->
            val delText = listView.getItemAtPosition(i).toString()
            adapter.remove(delText)
            Toast.makeText(this, "Вы удалили: $delText", Toast.LENGTH_LONG).show()
        }

        addName.setOnClickListener(){
            val text = userData.text.toString().trim()
            if (text != ""){
                adapter.add(text)
            }
        }

        back.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}