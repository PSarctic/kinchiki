package com.example.myapplication03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment



class BlankFragment3 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank3, container, false)

        val backButton = view.findViewById<Button>(R.id.to_home)
        val label = view.findViewById<TextView>(R.id.label_rules)
        label.text = "Правила"
        val rules = view.findViewById<TextView>(R.id.rules)
        rules.text = "Перед началом раунда:\n" +
                "Вводятся имена игроков (>=3)\n\nНачало раунда:\nИгрок по нажатию кнопки" +
                " со своим именем видит свой статус: Шпион или Киношник." +
                "Если игрок Киношник, он видит КИНОпроект текущего раунда (взятый из КИНОбазы); " +
                "если Шпион, то нет.\nЦель Киношника: найти шпиона;\nЦель Шпиона: отгадать КИНОпроект.\n\n" +
                "# Классический режим: Шпион только один;\n" +
                "# Непредсказуемый режим: Шпионов может быть произвольное количество (Шпиона может и вовсе не быть" +
                " или все игроки могут быть Шпионами)\n\n" +
                "Ход раунда:\nИгроки по очереди задают друг другу вопросы связанные с КИНОпроектом раунда" +
                " (задавать вопрос человеку, задававшему вопрос тебе самому нельзя)\n\n" +
                "Конец раунда:\nШпион в любой момент может остановить игру и попробовать отгадать КИНОпроект (1 попытка)," +
                "а игроки могут остановить игру на голосование за шпиона (произвольное число раз): если все " +
                "единогласно проголосуют за одного человека, раунд заканчивается.\nПобеда Шпиона:\n" +
                "1) Шпион верно отгадал КИНОпроект;\n2) На голосовании выбрали другого игрока.\n" +
                "Победа Киношников:\n1) Шпион неверно отгадал фильм;\n2) На голосовании единогласно выбрали шпиона.\n\n P.s. - Почему игра называется Кинчики?\n- Потому что я Бэтмен!"


        // Установите обработчик нажатия на кнопку
        backButton.setOnClickListener {
            // Получите менеджера фрагментов и начните транзакцию
            val fragmentManager = parentFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(this)

            transaction.commit()
        }

        return view
    }
    companion object {

        @JvmStatic
        fun newInstance() = BlankFragment3()
    }
}