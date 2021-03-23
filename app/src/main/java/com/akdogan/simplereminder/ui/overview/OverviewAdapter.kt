package com.akdogan.simplereminder.ui.overview

import android.icu.text.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akdogan.simplereminder.R
import com.akdogan.simplereminder.datalayer.NotificationEntity

class OverviewAdapter() : RecyclerView.Adapter<OverviewAdapter.ReminderViewHolder>() {
    var dataSet = listOf<NotificationEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
            Log.i("RECYCLERVIEW", "set dataset called with $value")
        }

    class ReminderViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val messageTextView = listItemView.findViewById<TextView>(R.id.list_item_textview_message)
        val scheduledTextView = listItemView.findViewById<TextView>(R.id.list_item_textview_scheduled_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.i("RECYCLERVIEW", "onCreateViewHolder triggered")
        return ReminderViewHolder(inflater.inflate(R.layout.reminder_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val currentItem = dataSet[position]
        holder.messageTextView.text = "Message: ${currentItem.message}"
        val date = DateFormat.getDateInstance(DateFormat.LONG).format(currentItem.triggerTime)
        val time = DateFormat.getTimeInstance(DateFormat.SHORT).format(currentItem.triggerTime)
        holder.scheduledTextView.text = "Time: $date $time"
        Log.i("RECYCLERVIEW", "OnBindViewHolder Called with pos: $position and Time: $date $time")
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}