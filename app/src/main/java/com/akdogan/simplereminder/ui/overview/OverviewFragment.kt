package com.akdogan.simplereminder.ui.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.akdogan.simplereminder.R
import com.akdogan.simplereminder.datalayer.roomdatabase.Repository
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OverviewFragment : Fragment() {
    lateinit var adapter: OverviewAdapter
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        val recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        adapter = OverviewAdapter()
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        Repository.publicRemindersList.observe(viewLifecycleOwner, {
            adapter.dataSet = it
            Log.i("RECYCLERVIEW", "Overviewfragment public list observer called with $it")
        })

        Repository.notificationsList.observe(viewLifecycleOwner, {
            Log.i("RECYCLERVIEW", "Overviewfragment notlist observer called with $it")
        })

        view.findViewById<FloatingActionButton>(R.id.button_new_reminder).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}