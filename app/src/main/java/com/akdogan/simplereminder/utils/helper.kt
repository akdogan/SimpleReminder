package com.akdogan.simplereminder.utils

import com.akdogan.simplereminder.datalayer.roomdatabase.Repository

val ID_RANGE = 1..99999

fun generateNotificationId(): Int{
    while (true){
        val id = ID_RANGE.random()
        if (!Repository.notificationIdExists(id)){
            return id
        }
    }
}