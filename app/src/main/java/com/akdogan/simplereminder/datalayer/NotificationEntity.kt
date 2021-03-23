package com.akdogan.simplereminder.datalayer

data class NotificationEntity(
    val dbId: Long = 0,
    val intentId: Int,
    val triggerTime: Long,
    val message: String,
    val active: Boolean = true
)

