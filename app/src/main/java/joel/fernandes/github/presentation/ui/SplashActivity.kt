package joel.fernandes.github.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import joel.fernandes.github.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private val SPLASH_DELAY: Long = 3000

    private var runnable : Runnable? = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        runnable = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lottieSplashAnim.speed = 1.2f
        lottieSplashAnim.playAnimation()
        handler = Handler()
        handler.postDelayed(runnable, SPLASH_DELAY)
    }
}