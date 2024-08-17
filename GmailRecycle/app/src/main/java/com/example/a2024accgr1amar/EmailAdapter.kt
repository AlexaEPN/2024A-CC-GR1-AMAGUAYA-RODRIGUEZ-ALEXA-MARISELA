package com.example.a2024accgr1amar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private var items: List<EmailItem>) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val textView: TextView = itemView.findViewById(R.id.title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_email, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.title
        holder.imageView.setImageResource(item.imageResId)
        holder.descriptionTextView.text = item.description
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Método para actualizar los ítems
    fun updateItems(newItems: List<EmailItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}