package com.example.fitbit2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InputAdapter(private val inputs: List<DisplayInput>) : RecyclerView.Adapter<InputAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_input, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val input = inputs[position]
        holder.bind(input)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val sleepTypeTextView = itemView.findViewById<TextView>(R.id.SleepType)
        private val sleepHrsTextView = itemView.findViewById<TextView>(R.id.SleepAmount)

        fun bind(input: DisplayInput) {
            sleepTypeTextView.text = input.sleepType
            sleepHrsTextView.text = input.sleepHours
        }
    }

    override fun getItemCount() = inputs.size
}