package com.example.louemonchar.presentation.intro


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.louemonchar.MainActivity

import com.example.louemonchar.R

class IntroVue : AppCompatActivity(), IntroInterface.Vue {

    private lateinit var presentateur: IntroInterface.Presentateur
    private var image1: ImageView? = null
    private var image2: ImageView? = null
    private var text1: TextView? = null
    private var text2: TextView? = null
    private var demarrageApplication: ProgressBar? = null
    private var textePourcentage: TextView? = null
    private var texteProgression: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_intro)

        textePourcentage = findViewById(R.id.pourcentage)
        presentateur = IntroPresentateur(this)

        // Initialisation de la barre de progression en horizontale
        demarrageApplication = findViewById(R.id.barreDemarrage)
        demarrageApplication?.max = 100
        presentateur = IntroPresentateur(this)
        texteProgression = findViewById(R.id.texteProgression)

        image1 = findViewById(R.id.logoImageView)
        image2 = findViewById(R.id.introImageView)
        text1 = findViewById(R.id.logoTitle)
        text2 = findViewById(R.id.logoSlogan)

        Handler().postDelayed({
            presentateur.commencerAnimation()
        }, 1000)
    }

    override fun afficherEcranPrincipal() {
        startEnterAnimation()
        Handler().postDelayed({
            startExitAnimation()
        }, 1500)

        // Démarrer la progression de la barre de progression après l'animation de sortie
        Handler().postDelayed({
            // Afficher la barre de progression et le texteProgression
            demarrageApplication?.visibility = View.VISIBLE
            textePourcentage?.visibility = View.VISIBLE
            texteProgression?.visibility = View.VISIBLE // Affichage du texteProgression
            val handler = Handler()
            handler.post(object : Runnable {
                var progress = 0
                override fun run() {
                    if (progress <= 100) {
                        demarrageApplication?.progress = progress
                        textePourcentage?.text = "$progress%" // Afficher le pourcentage actuel
                        progress++
                        if (progress > 100) {
                            demarrageApplication?.visibility = View.GONE
                            textePourcentage?.visibility = View.GONE // Cacher le TextView une fois terminé
                            texteProgression?.visibility = View.GONE // Cacher le texteProgression
                        } else {
                            handler.postDelayed(this, 20)
                        }
                    }
                }
            })
        }, 3000)

        // Démarrer l'écran principal après le délai d'attente
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }

    private fun startEnterAnimation() {
        image1?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.logoentrant))
        image2?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.imageentrant))
        text1?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textentrant))
        text2?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textentrant))
        image1?.visibility = View.VISIBLE
        image2?.visibility = View.VISIBLE
        text1?.visibility = View.VISIBLE
        text2?.visibility = View.VISIBLE
    }

    private fun startExitAnimation() {
        image1?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.logosortant))
        image2?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.imagesortant))
        text1?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textsortant))
        text2?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textsortant))
        image1?.visibility = View.INVISIBLE
        image2?.visibility = View.INVISIBLE
        text1?.visibility = View.INVISIBLE
        text2?.visibility = View.INVISIBLE
    }
}

