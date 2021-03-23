package com.akdogan.simplereminder.ui.newnotification

import android.app.Application
import android.icu.text.DateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akdogan.simplereminder.datalayer.NotificationEntity
import com.akdogan.simplereminder.datalayer.roomdatabase.Repository
import com.akdogan.simplereminder.postNotification
import kotlinx.coroutines.launch
import java.util.*

class NewReminderViewModel : ViewModel() {

    private val _triggerTime = MutableLiveData<Long>()
    val triggerTime: LiveData<Long>
        get() = _triggerTime

    init {
        _triggerTime.value = Calendar.getInstance().timeInMillis
        printDate()
    }

    fun createReminder(app: Application, userMessage: String) {
        triggerTime.value?.let {
            val id = postNotification(app, userMessage, it)
            viewModelScope.launch {
                Repository.addNotification(
                    NotificationEntity(
                        intentId = id,
                        triggerTime = it,
                        message = userMessage
                    )
                )
            }
        }
    }


    fun updateTriggerTime(timeInMillis: Long) {
        _triggerTime.value = timeInMillis
        printDate()
    }

    fun printDate() {
        val date = DateFormat.getDateInstance(DateFormat.LONG).format(_triggerTime.value)
        val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(_triggerTime.value)
        Log.i("TRIGGERTIME", "Set to $date $time")
    }

}