package com.akdogan.simplereminder.datalayer.roomdatabase

import android.app.Notification
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NotificationDatabaseEntry::class], version = 1, exportSchema = false)
abstract class NotificationDatabase : RoomDatabase(){

    abstract val notificationDatabaseDao: RoomDAO

    companion object {

        @Volatile
        private var INSTANCE: NotificationDatabase? = null

        fun getInstance(context: Context): NotificationDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        NotificationDatabase::class.java,
                        "dive_log_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}