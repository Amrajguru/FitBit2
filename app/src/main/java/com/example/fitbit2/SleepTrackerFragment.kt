package com.example.fitbit2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbit2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class SleepTrackerFragment : Fragment() {
    private val inputs = mutableListOf<DisplayInput>()
    private lateinit var inputsRecyclerView: RecyclerView
    private lateinit var inputsAdapter: InputAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep_tracker, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        inputsRecyclerView = view.findViewById(R.id.inputslist)
        inputsRecyclerView.layoutManager = layoutManager
        inputsRecyclerView.setHasFixedSize(true)
        inputsAdapter = InputAdapter(inputs)
        inputsRecyclerView.adapter = inputsAdapter

        // Update the return statement to return the inflated view from above
        return view
    }

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lifecycleScope.launch {
        (activity?.application as InputApplication).db.inputDao().getAll().collect { databaseList ->
            databaseList.map { entity ->
                DisplayInput(
                    entity.sleepType,
                    entity.sleepHours
                )
            }.also { mappedList ->
                inputs.clear()
                inputs.addAll(mappedList)
                inputsAdapter.notifyDataSetChanged()
            }
        }
    }

    var inputbutton = view.findViewById<Button>(R.id.inputbutton)

    inputbutton.setOnClickListener() {
        val intent = Intent(view.context, InputActivity::class.java)
        this.startActivity(intent)
    }
}

    companion object {
        fun newInstance(): SleepTrackerFragment {
            return SleepTrackerFragment()
        }
    }
}