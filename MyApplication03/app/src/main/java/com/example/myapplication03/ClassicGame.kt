package com.example.myapplication03

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class ClassicGame : AppCompatActivity() {
    companion object {
        const val PLAYERS_LIST = "player_list"
        const val MOVIES_LIST = "movies_list"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_game)




        val linearLayout: LinearLayout = findViewById(R.id.newLayout) // Создаем LinearLayout
        val sc: ScrollView = findViewById(R.id.scroll)

        val textView3: TextView = findViewById(R.id.textView3)
        var movies = intent.getStringArrayListExtra(MOVIES_LIST)
        val players = intent.getStringArrayListExtra(PLAYERS_LIST)


        val playButton: Button = findViewById(R.id.button_play)

        var playersCnt = players!!.size
        val baseMovieCnt = movies!!.size
        var moviesCnt = movies!!.size
        var endPl = getEnding(playersCnt)
        var end1 = getEnding1(moviesCnt)
        var endMov = getEnding(moviesCnt)
        var round = baseMovieCnt - moviesCnt + 1

        textView3.text = "Раунд $round\nДоступ$end1 $moviesCnt КИНОпроект$endMov"

        var mov_cnt = movies.size - 1

        if (mov_cnt == 0){
            Toast.makeText(this, "Последний раунд", Toast.LENGTH_SHORT).show()
        }


        var movie_ind = (0..mov_cnt).random()
        var movie = movies[movie_ind]

        var spion = (0..playersCnt-1).random()

        val infos: ArrayList<String> = arrayListOf()
        for (pl in 0..playersCnt - 1) {
            var info = movie
            if (pl == spion) {
                info = "Шпион"
            }
            infos.add(info)
//            println("$player: $info")
        }
        movies.removeAt(movie_ind)



//        var infos = gameRound(players, movies)







        for (i in 0 until playersCnt) {
            val button = Button(this, null, 0, R.style.PurpleButton) // Создаем новую кнопку
            button.text = players[i].toString() // Задаем текст кнопки
            button.setTextColor(Color.WHITE)


            button.id = i
            val params: Toolbar.LayoutParams = Toolbar.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT,
                Toolbar.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(5, 20, 5, 0)
            params.gravity = Gravity.CENTER

            button.layoutParams = params
            button.textSize = 24f

            button.setBackgroundResource(R.drawable.button_rounded)
            linearLayout.addView(button) // Добавляем кнопку в LinearLayout
            var sch = 0


            button.setOnClickListener() {
                val fragment = BlankFragment2.newInstance()
                val bundle = Bundle()
                bundle.putString("player", players[i])
                bundle.putString("info", infos[i])
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView3, fragment).commit()
                if (sch >= round -1) {
                    button.setBackgroundResource(R.drawable.button_bw)
                }
                sch += 1
            }
        }


        playButton.setOnClickListener() {

            for (i in 0 until playersCnt){
                val but = findViewById<Button>(i)
                but.text = players[i]
                but.setBackgroundResource(R.drawable.button_rounded)
            }

            mov_cnt = movies.size - 1
            if (mov_cnt >= 0) {

                if (mov_cnt == 0){
                    Toast.makeText(this, "Последний раунд", Toast.LENGTH_SHORT).show()
                }

                movie_ind = (0..mov_cnt).random()
                movie = movies[movie_ind]
                spion = (0..playersCnt-1).random()

                infos.clear()
                for (pl in 0..playersCnt-1) {
                    var info = movie
                    if (pl == spion) {
                        info = "Шпион"
                    }
                    infos.add(info)
                    //            println("$player: $info")
                }
                movies.removeAt(movie_ind)

                moviesCnt -= 1
                endPl = getEnding(playersCnt)
                end1 = getEnding1(moviesCnt)
                endMov = getEnding(moviesCnt)
                round += 1

                textView3.text = "Раунд $round\nДоступ$end1 $moviesCnt КИНОпроект$endMov"
            } else {
                Toast.makeText(this, "Закончились КИНОпроекты", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity3::class.java))
            }
//            infos = gameRound(players, movies)
        }



    }


    fun getEnding(players: Int): String {
        var ending = ""
        if ((players >= 5 && players <= 20) || (players % 10 <= 9 && players % 10 >= 5) || players % 10 == 0) {
            ending = "ов"
        } else {
            if (players % 10 != 1) {
                ending = "а"
            } else {
                ending = ""
            }
        }
        return ending
    }

    fun getEnding1(cNt: Int): String {
        var ending = ""
        if ((cNt % 10 == 1) && (cNt != 11)){
            ending = "ен"
        } else{
            ending = "но"
        }
        return ending
    }


    fun gameRound(players_list: ArrayList<String>, movies: ArrayList<String>): ArrayList<String> {
        val players_cnt = players_list.size
        val mov_cnt = movies.size - 1

        val movie_ind = (0..mov_cnt).random()
        val movie = movies[movie_ind]
        val cnt_of_spions = (0..players_cnt).random()

        val list_of_spions: MutableList<Int> = mutableListOf()
        val j = cnt_of_spions - 1
        val indee = players_cnt - 1
        for (i in 0..j) {
            val spion_ind = (0..indee).random()
            list_of_spions.add(spion_ind)
        }

        val status: MutableList<Int> = mutableListOf()
        for (k in 1..players_cnt) {
            status.add(0)
        }

        val n = list_of_spions.size - 1
        if (n > -1) {
            for (i in 0..n) {
                status[list_of_spions[i]] = 1
            }
        }

        val infos: ArrayList<String> = arrayListOf()
        for (pl in 0..status.size - 1) {
            val player = players_list[pl]
            var info = movie
            if (status[pl] == 1) {
                info = "Шпион"
            }
            infos.add(info)
//            println("$player: $info")
        }
        movies.removeAt(movie_ind)

        return infos
    }
}