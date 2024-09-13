package com.example.myapplication03

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.listedit.SharedPreferencesHelper

class MainActivity3 : AppCompatActivity() {
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val intentPlayers = Intent(this, MainActivity::class.java)
        var base = 1

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK){
                val movies = result.data?.getStringArrayListExtra("MOVIES")
                intentPlayers.putStringArrayListExtra(MainActivity.MOVIES_LIST, movies)
                base = 0
            }
        }





//        intentPlayers.putStringArrayListExtra(MainActivity.MOVIES_LIST, movies())


        val classicGame = findViewById<Button>(R.id.classic)
        val neoGame = findViewById<Button>(R.id.neo)
        val coopGame = findViewById<Button>(R.id.coop)
        val toCineBase = findViewById<Button>(R.id.to_movies)
        val pos001 = findViewById<ImageView>(R.id.p001)
        val pos002 = findViewById<ImageView>(R.id.p002)
        val pos003 = findViewById<ImageView>(R.id.p003)
        val pos004 = findViewById<ImageView>(R.id.p004)
        val pos005 = findViewById<ImageView>(R.id.p005)
        val pos006 = findViewById<ImageView>(R.id.p006)
        val pos007 = findViewById<ImageView>(R.id.p007)
        val pos008 = findViewById<ImageView>(R.id.p008)
        val pos009 = findViewById<ImageView>(R.id.p009)
        val pos010 = findViewById<ImageView>(R.id.p010)
        val pos011 = findViewById<ImageView>(R.id.p011)
        val pos012 = findViewById<ImageView>(R.id.p012)

        classicGame.visibility = View.VISIBLE
        classicGame.isClickable

        val cinemaList = SharedPreferencesHelper.getArrayList(this, "list_key")
        val info = findViewById<ImageButton>(R.id.imageButton)

        info.setOnClickListener(){
            val fragment = BlankFragment3.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, fragment).commit()
        }




        neoGame.setOnClickListener(){
            if (base == 0){
                intentPlayers.putExtra(MainActivity.GAME_NUMBER, 1)
                intentPlayers.putExtra(MainActivity.BASE, base)
                startActivity(intentPlayers)
            }
            else{
                intentPlayers.putExtra(MainActivity.GAME_NUMBER, 1)
                intentPlayers.putExtra(MainActivity.BASE, base)
                intentPlayers.putStringArrayListExtra(MainActivity.MOVIES_LIST, cinemaList)
                startActivity(intentPlayers)
//                Toast.makeText(this, "Сначала зайдите в КИНОбазу", Toast.LENGTH_SHORT).show()
            }

        }
        classicGame.setOnClickListener(){
            if (base == 0){
                intentPlayers.putExtra(MainActivity.GAME_NUMBER, 0)
                intentPlayers.putExtra(MainActivity.BASE, base)
                startActivity(intentPlayers)
            }
            else{
                intentPlayers.putExtra(MainActivity.GAME_NUMBER, 0)
                intentPlayers.putExtra(MainActivity.BASE, base)
                intentPlayers.putStringArrayListExtra(MainActivity.MOVIES_LIST, cinemaList)
                startActivity(intentPlayers)
//                Toast.makeText(this, "Сначала зайдите в КИНОбазу", Toast.LENGTH_SHORT).show()
            }

        }
        coopGame.setOnClickListener(){
            if (base == 0){
                intentPlayers.putExtra(MainActivity.GAME_NUMBER, 2)
                intentPlayers.putExtra(MainActivity.BASE, base)
                startActivity(intentPlayers)
            }
            else{
                intentPlayers.putExtra(MainActivity.GAME_NUMBER, 2)
                intentPlayers.putExtra(MainActivity.BASE, base)
                intentPlayers.putStringArrayListExtra(MainActivity.MOVIES_LIST, cinemaList)
                startActivity(intentPlayers)
//                Toast.makeText(this, "Сначала зайдите в КИНОбазу", Toast.LENGTH_SHORT).show()
            }

        }

        toCineBase.setOnClickListener(){
            launcher?.launch(Intent(this, CineBase::class.java))
            base = 0
        }

    }


    fun movies(): ArrayList<String> {
        val movies: ArrayList<String> = arrayListOf("Шрек",
            "Мадагаскар",
            "Рик и Морти",
            "Гравити Фолз",
            "Финес и Ферб",
            "Король Лев",
            "Смешарики",
            "Золушка",
            "Холодное сердце",
            "Мстители",
            "Фиксики",
            "Барбоскины",
            "Барби",
            "Зверополис",
            "Зверопой",
            "Лунтик",
            "Корпорация монстров",
            "Человек паук через вселенные",
            "Человек паук через вселенные 2",
            "Ральф",
            "Суперсемейка",
            "Человек паук 2002",
            "Пираты Карибского моря",
            "Люди икс",
            "Флэш",
            "Железный человек",
            "Отряд самоубийц",
            "Стражи галактики",
            "Астробой",
            "Валли",
            "История игрушек",
            "Маленький принц",
            "Гарри Поттер",
            "Город героев",
            "Рататуй",
            "Кунг-фу панда",
            "Мандалорец",
            "Свинка пепа",
            "Назад в будущее",
            "Тачки",
            "Черепашки ниндзя 2012",
            "Маша и медведь",
            "Ледниковый период",
            "Пипец",
            "Один дома",
            "Игра в кальмара",
            "Малыш на драйве",
            "Бэтмен",
            "Спанч боб")
        return movies
    }
}