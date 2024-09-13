package com.example.myapplication03

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.listedit.SharedPreferencesHelper

class CineBase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cine_base)


        val label: TextView = findViewById(R.id.label_02)
        val userCinema: TextView = findViewById(R.id.user_cinema)
        val movieView: ListView = findViewById(R.id.list_view2)
        val addCinema: Button = findViewById(R.id.add_cinema)
        val readyOk: Button = findViewById(R.id.ok)

        val arrayList = SharedPreferencesHelper.getArrayList(this, "list_key")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)

        movieView.adapter = adapter
        movieView.setOnItemClickListener{ adapterView, view, i, l ->
            val delText = movieView.getItemAtPosition(i).toString()
            adapter.remove(delText)
//            Toast.makeText(this, "Вы удалили: $delText", Toast.LENGTH_LONG).show()
        }

        addCinema.setOnClickListener(){
            val text = userCinema.text.toString().trim()
            if (text != ""){
                adapter.add(text)
            }
        }
        readyOk.setOnClickListener(){
            SharedPreferencesHelper.saveArrayList(this, "list_key", arrayList)
            val resultIntent = Intent()
            resultIntent.putStringArrayListExtra("MOVIES", arrayList)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }

}