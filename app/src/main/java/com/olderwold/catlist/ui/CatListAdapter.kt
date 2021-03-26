package com.olderwold.catlist.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.ViewTarget
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

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.recycle()
    }

    override fun getItemCount(): Int = data.size

    @Suppress("DEPRECATION")
    class ViewHolder(
        private val layout: View
    ) : RecyclerView.ViewHolder(layout) {
        private val imageView = layout.findViewById<ImageView>(R.id.imageView)
        private var viewTarget: ViewTarget<ImageView, Drawable>? = null

        fun bind(item: Cat) {
            bindUI(item)
            loadUrl(item)
        }

        private fun bindUI(item: Cat) {
            imageView.contentDescription = item.id
        }

        private fun loadUrl(item: Cat) {
            val requestBuilder: RequestBuilder<Drawable> = Glide.with(layout.context).load(item.url)
            viewTarget = requestBuilder.into(imageView)
        }

        fun recycle() {
            viewTarget?.request?.clear()
            viewTarget = null
        }
    }
}
