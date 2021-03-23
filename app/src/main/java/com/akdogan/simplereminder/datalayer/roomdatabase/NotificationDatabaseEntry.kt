package com.akdogan.simplereminder.datalayer.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications_table")
data class NotificationDatabaseEntry(
    @PrimaryKey(autoGenerate = true)
    val dbId: Long = 0,

    @ColumnInfo(name = "intent_id")
    val intentId: Int,

    @ColumnInfo(name = "trigger_time")
    val triggerTime: Long,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "is_active")
    val active: Int = 1
)

