package com.example.myapplication03

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object{
        const val MOVIES_LIST = "movies_list"
        const val GAME_NUMBER = "game_number"
        const val BASE = "base"
    }

    private var launcher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val label: TextView = findViewById(R.id.label_01)
        val userData: TextView = findViewById(R.id.user_data)
        val listView: ListView = findViewById(R.id.list_view)
        val addName: Button = findViewById(R.id.add_gamer)
        val ready: Button = findViewById(R.id.ready_button)

        val players: ArrayList<String> = arrayListOf()

        val base = intent.getIntExtra(BASE, 1)




        val gameNumber = intent.getIntExtra(GAME_NUMBER, 0)
        val movies = intent.getStringArrayListExtra(MOVIES_LIST)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, players)

        listView.adapter = adapter
        listView.setOnItemClickListener{ adapterView, view, i, l ->
            val delText = listView.getItemAtPosition(i).toString()
            adapter.remove(delText)
//            Toast.makeText(this, "Вы удалили: $delText", Toast.LENGTH_LONG).show()
        }

        addName.setOnClickListener(){
            val text = userData.text.toString().trim()
            if (text != ""){
                adapter.add(text)
            }
        }

        ready.setOnClickListener(){
            if (players.size >= 3){
                if (gameNumber == 0){
                    val intentSecond = Intent(this, ClassicGame::class.java)
                    intentSecond.putStringArrayListExtra(ClassicGame.PLAYERS_LIST, players)
                    intentSecond.putStringArrayListExtra(ClassicGame.MOVIES_LIST, movies)
                    startActivity(intentSecond)
                } else{
                    if (gameNumber == 1){
                        val intentSecond = Intent(this, MainActivity2::class.java)
                        intentSecond.putStringArrayListExtra(MainActivity2.PLAYERS_LIST, players)
                        intentSecond.putStringArrayListExtra(MainActivity2.MOVIES_LIST, movies)
                        startActivity(intentSecond)
                    }
                    else{
                        if (players.size >= 4){
                            val intentSecond = Intent(this, coopSpions::class.java)
                            intentSecond.putStringArrayListExtra(coopSpions.PLAYERS_LIST, players)
                            intentSecond.putStringArrayListExtra(coopSpions.MOVIES_LIST, movies)
                            startActivity(intentSecond)
                        } else {
                            Toast.makeText(this, "Необходимо минимум 4 игрока", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            else{
                Toast.makeText(this, "Необходимо минимум 3 игрока", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun getArrayOfList(list: MutableList<String>): ArrayList<String> {
        val a = list.size - 1
        val arrayA: ArrayList<String> = arrayListOf()
        for (i in 0..a){
            arrayA.add(list[i])
        }
        return arrayA
    }

}