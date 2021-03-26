package com.olderwold.catlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.olderwold.catlist.R
import com.olderwold.catlist.domain.Cat

class CatListAdapter : RecyclerView.Adapter<CatListAdapter.ViewHolder>() {
    var data: List<Cat> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.cat_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = data[position]
        holder.bind(cat)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        layout: View
    ) : RecyclerView.ViewHolder(layout) {
        private val imageView = layout.findViewById<ImageView>(R.id.imageView)

        fun bind(item: Cat) {
            // Glide to load image
        }

        fun recycle() {
        }
    }
}
