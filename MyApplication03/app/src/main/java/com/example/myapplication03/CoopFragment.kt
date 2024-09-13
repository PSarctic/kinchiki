package com.example.myapplication03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class CoopFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val view = inflater.inflate(R.layout.fragment_coop, container, false)
        val player = arguments?.getString("player")
        val info = arguments?.getString("info")
        val information = arguments?.getString("info_full")

        // Находите кнопку внутри вашего фрагмента
        val backButton = view.findViewById<Button>(R.id.hide_fr2)
        val text = view.findViewById<TextView>(R.id.textView)
        val text2 = view.findViewById<TextView>(R.id.textView5)
        val img = view.findViewById<ImageView>(R.id.imageView5)

        text.text = "$player"

        if (info == "Шпион"){
            text2.text = "\n\nВы - Шпион!\n \n"+information
            img.setImageResource(R.drawable.spion_01)
        } else{
            img.setImageResource(R.drawable.movie_01)
            text2.text = "\n\nВы - Киношник!\n \nКИНОпроект: $info\n"
        }


        // Установите обработчик нажатия на кнопку
        backButton.setOnClickListener {
            // Получите менеджера фрагментов и начните транзакцию
            val fragmentManager = parentFragmentManager
            val transaction = fragmentManager.beginTransaction()

            // Скрыть (или удалить) данный фрагмент при нажатии на кнопку
            transaction.hide(this) // для скрытия фрагмента
            // transaction.remove(this) // для удаления фрагмента

            transaction.commit()
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = CoopFragment()
    }
}