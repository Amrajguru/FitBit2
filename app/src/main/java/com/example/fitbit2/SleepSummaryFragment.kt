package com.example.fitbit2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class SleepSummaryFragment : Fragment() {
    private val inputs = mutableListOf<DisplayInput>()
    private lateinit var AveSleepText: TextView
    private lateinit var MaxSleepText: TextView
    private lateinit var MinSleepText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep_summary, container, false)
        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var AveSleep: Number =0
        var MaxSleep: Number =0
        var MinSleep: Number =0
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            (activity?.application as InputApplication).db.inputDao().getAll()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        DisplayInput(
                            entity.sleepType,
                            entity.sleepHours
                        )
                    }.also { mappedList ->
                        inputs.addAll(mappedList)
                    }
                }
        }

        AveSleepText = view.findViewById<TextView>(R.id.AveSleep)
        MaxSleepText = view.findViewById<TextView>(R.id.MaxSleep)
        MinSleepText = view.findViewById<TextView>(R.id.MinSleep)

        var totalSleep = 0
        for (i in 0..inputs.size){
            var sleepval=inputs[i].sleepHours?.toInt()
            if (sleepval != null) {
                if (sleepval > MaxSleep) {
                        MaxSleep = sleepval
                }
                if (sleepval < MinSleep) {
                        MinSleep = sleepval
                }
                    totalSleep += sleepval
            }
        }
        AveSleep = totalSleep/inputs.size

        AveSleepText.text=AveSleep.toString()
        MaxSleepText.text=MaxSleep.toString()
        MinSleepText.text=MinSleep.toString()

    }

    companion object {
        fun newInstance(): SleepSummaryFragment {
            return SleepSummaryFragment()
        }
    }
}