package com.pwol.flutter_app_1agas2

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.AlarmClock
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.pwol.flutter_app_1agas2.databinding.ActivityFullscreenSplashBinding
import com.pwol.flutter_app_1agas2.ui.login.LoginActivity

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@Suppress("DEPRECATION")
class FullscreenActivitySplash : AppCompatActivity() {

private lateinit var binding: ActivityFullscreenSplashBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityFullscreenSplashBinding.inflate(layoutInflater)
         setContentView(binding.root)

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({

            /*val editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
            editor.putBoolean("isLogin", false)
            editor.commit()*/

            val prefs = getSharedPreferences("pref", Context.MODE_PRIVATE)
            val isLogin = prefs.getBoolean("isLogin", false)
            if(!isLogin){
                val intentLogin = Intent(this, LoginActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, "message")
                }
                startActivity(intentLogin)
                finish()
            }else{

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 500) // 3000 is the delayed time in milliseconds.
    }

}