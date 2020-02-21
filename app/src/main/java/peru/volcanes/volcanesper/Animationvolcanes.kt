package peru.volcanes.volcanesper

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.view.animation.Animation
import android.animation.AnimatorInflater



class Animationvolcanes : Activity() {


     lateinit var obte: ObjectAnimator

     lateinit var rocket: ImageView
    lateinit var boton: Button

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animationvolcanes)


      //  rocket = findViewById<ImageView>(R.id.cuadrado)
        //boton = findViewById<Button>(R.id.boton)

       // boton.setOnClickListener {
            slidedown()
       // }

        /*  rocket = findViewById<ImageView>(R.id.cuadrado)*/




        /*

        rocket = findViewById<ImageView>(R.id.cuadrado)
         boton = findViewById<Button>(R.id.boton)



      val obja: ObjectAnimator = ObjectAnimator.ofFloat(rocket, "x", 987f)

        boton.setOnClickListener {



                obja.setDuration(300)
                obja.repeatCount(ValueAnimator.INFINITE)
                obja.start()




        }
*/






/*
           //obja=ObjectAnimator.ofFloat(rocket, "x", 555)

        val positionAnimator = ValueAnimator.ofFloat(0f, 323f)

        positionAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket?.translationY = value
        }

        val rotationAnimator = ObjectAnimator.ofFloat(rocket, "rotation", 0f, 180f)

        val animatorSet = AnimatorSet()
        animatorSet.play(positionAnimator).with(rotationAnimator)
        animatorSet.duration = 6
        animatorSet.start()

*/



        /*
        val valueAnimator = ValueAnimator.ofFloat(0f, -screenHeight)

//2
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            rocket.translationY = value
        }

//5
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = Base.Companion.DEFAULT_ANIMATION_DURATION

//6
        valueAnimator.start()
*/


      //  v.animate().x(valueX).y(valueY).setDuration(500).start();


    }


    private fun slidedown() {
        obte = ObjectAnimator.ofFloat(rocket, "Y", 20f)

        obte.setDuration(300)
        obte.setRepeatCount(ValueAnimator.INFINITE)
        obte.setRepeatMode(ValueAnimator.RESTART)
        obte.start()
    }



}
