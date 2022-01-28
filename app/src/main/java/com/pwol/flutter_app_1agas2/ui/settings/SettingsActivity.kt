package com.pwol.flutter_app_1agas2.ui.settings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.pwol.flutter_app_1agas2.MainActivity
import com.pwol.flutter_app_1agas2.R
import com.pwol.flutter_app_1agas2.database.ServicesDatabase
import com.pwol.flutter_app_1agas2.databinding.ActivityMainBinding
import java.io.File

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // create variable for context
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btnReset: Button = findViewById(R.id.btnResetDB)
        btnReset.setOnClickListener {
            //show dialog
            val builder = AlertDialog.Builder(this) //this = activity
            builder.setTitle("Reset Database")
            builder.setMessage("¿Está seguro de que desea restablecer la base de datos?")
            builder.setPositiveButton("Yes"){dialog, which ->
                //Yes button clicked
                deleteDatabase()
            }
            builder.setNegativeButton("No"){dialog, which ->
                //No button clicked
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()

        }
    }
    private fun deleteDatabase() {
        val databasesDir = File(context?.applicationInfo?.dataDir.toString() + "/databases")
        File(databasesDir, "1a_database").delete()
        File(databasesDir, "1a_database-shm").delete()
        File(databasesDir, "1a_database-wal").delete()


        val intent = Intent()
        intent.putExtra("refresh", "refresh")
        setResult(Activity.RESULT_OK, intent)
        finish()


    }
}