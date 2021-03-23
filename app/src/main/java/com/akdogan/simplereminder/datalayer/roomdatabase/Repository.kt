package com.akdogan.simplereminder.datalayer.roomdatabase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.akdogan.simplereminder.datalayer.NotificationEntity


object Repository {

    private lateinit var database: RoomDAO

    /*private */var notificationsList: LiveData<List<NotificationDatabaseEntry>> = liveData {
        emit(emptyList<NotificationDatabaseEntry>())
    }

    val publicRemindersList: LiveData<List<NotificationEntity>>
        get() = Transformations.map(notificationsList) {
                it.toDomainModel()
            }

    fun setup(con: Context) {
        database = NotificationDatabase.getInstance(con).notificationDatabaseDao
        notificationsList = database.getAllEntriesLiveData()
    }

    suspend fun addNotification(item: NotificationEntity) {
        database.addEntry(item.toDatabaseModel())
    }

    fun notificationIdExists(id: Int): Boolean {
        return notificationsList.value?.find { it.intentId == id } != null
    }
}

fun NotificationDatabaseEntry.toDomainModel(): NotificationEntity {
    return NotificationEntity(
        this.dbId,
        this.intentId,
        this.triggerTime,
        this.message,
        this.active.toBoolean()
    )
}

fun List<NotificationDatabaseEntry>.toDomainModel(): List<NotificationEntity> {
    return this.map { it.toDomainModel() }
}

fun NotificationEntity.toDatabaseModel(): NotificationDatabaseEntry {
    return NotificationDatabaseEntry(
        this.dbId,
        this.intentId,
        this.triggerTime,
        this.message,
        this.active.toInt()
    )
}

fun Int.toBoolean(): Boolean {
    return this != 0
}

fun Boolean.toInt(): Int {
    return if (this) {
        1
    } else {
        0
    }
}