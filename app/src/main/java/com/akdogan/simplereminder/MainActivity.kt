package com.akdogan.simplereminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.akdogan.simplereminder.datalayer.roomdatabase.Repository

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


            Repository.setup(applicationContext)
            Log.i("ID Exists TEST", "Result with empty list should be false, is: ${Repository.notificationIdExists(3)}")

        createMyNotificationChannel()






    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createMyNotificationChannel(){
        if (Build.VERSION.SDK_INT >= 26){
            val channel = NotificationChannel(
                SINGLE_CHANNEL_ID,
                SINGLE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply{
                lightColor = Color.MAGENTA
                enableLights(true)
                enableVibration(true)
                description = "Channel description"
            }
            applicationContext.getSystemService(NotificationManager::class.java).createNotificationChannel(
                channel
            )

        }

    }

}