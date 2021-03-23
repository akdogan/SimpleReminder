package com.akdogan.simplereminder.datalayer.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDAO {
    @Insert
    suspend fun addEntry(item: NotificationDatabaseEntry)

    @Query("SELECT * from notifications_table ORDER BY trigger_time DESC")
    fun getAllEntriesLiveData(): LiveData<List<NotificationDatabaseEntry>>
}