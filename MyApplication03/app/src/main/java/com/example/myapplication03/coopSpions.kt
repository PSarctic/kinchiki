package com.example.myapplication03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class coopSpions : AppCompatActivity() {

    companion object{
        const val PLAYERS_LIST = "player_list"
        const val MOVIES_LIST = "movies_list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coop_spions)

        val players = intent.getStringArrayListExtra(PLAYERS_LIST)
        val movies = intent.getStringArrayListExtra(MOVIES_LIST)

        val gamersNumber = players!!.size

        val labCoop = findViewById<TextView>(R.id.label_coop)
        labCoop.text = "В игре $gamersNumber участников\nВыберите число шпионов"

        val number = findViewById<TextView>(R.id.number)
        val minusButton = findViewById<Button>(R.id.minus)
        val plusButton = findViewById<Button>(R.id.plus)
        val playButton = findViewById<Button>(R.id.play)

        var numbText = number.text.toString()
        var nowNumb = Integer.parseInt(numbText)


        minusButton.setOnClickListener(){
            numbText = number.text.toString()
            nowNumb = Integer.parseInt(numbText)
            if (nowNumb == 2){
                Toast.makeText(this, "Необходимо минимум 2 шпиона", Toast.LENGTH_SHORT).show()
            }
            else {
                nowNumb -= 1
                number.text = nowNumb.toString()
            }
        }

        plusButton.setOnClickListener(){
            numbText = number.text.toString()
            nowNumb = Integer.parseInt(numbText)

            if (nowNumb + 1 > gamersNumber/2){
                Toast.makeText(this, "Число шпионов превышает половину игроков", Toast.LENGTH_SHORT).show()
            }
            else {
                nowNumb += 1
                number.text = nowNumb.toString()
            }
        }

        playButton.setOnClickListener(){
            val startGame = Intent(this, coopGame::class.java)
            startGame.putStringArrayListExtra(coopGame.MOVIES_LIST, movies)
            startGame.putStringArrayListExtra(coopGame.PLAYERS_LIST, players)
            startGame.putExtra(coopGame.SPIONS_CNT, nowNumb)
            startActivity(startGame)
        }








    }
}