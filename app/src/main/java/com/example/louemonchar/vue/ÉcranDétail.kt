package com.example.louemonchar.vue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.louemonchar.R

class ÉcranDétail : Fragment() {

    lateinit var textview1: TextView
    lateinit var textview2: TextView
    lateinit var textview3: TextView
    lateinit var textview4: TextView
    lateinit var textview5: TextView
    lateinit var textview6: TextView
    lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vue = inflater.inflate(R.layout.detail_auto, container, false)
        textview1 = vue.findViewById(R.id.textView1)
        textview2 = vue.findViewById(R.id.textView2)
        textview3 = vue.findViewById(R.id.textView3)
        textview4 = vue.findViewById(R.id.textView4)
        textview5 = vue.findViewById(R.id.textView5)
        textview6 = vue.findViewById(R.id.textView6)

        image = vue.findViewById(R.id.imageView4)

        image.setImageResource(R.drawable.hyundai_elantra)

        textview1.setText("Peut-être")
        textview2.setText("5")
        textview3.setText("Gaz")
        textview4.setText("Automatique")
        textview5.setText("Hyundai Elantra 2018")
        textview6.setText("Pierre")

        return vue
    }
}