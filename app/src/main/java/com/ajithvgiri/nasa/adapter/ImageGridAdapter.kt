package com.ajithvgiri.nasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.ui.home.OnItemClickListener
import kotlinx.android.synthetic.main.layout_grid_item_image.view.*

class ImageGridAdapter(private var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //array of colors to change the background color of screen
    private val colors = intArrayOf(
        android.R.color.darker_gray,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple,

        android.R.color.darker_gray,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple,

        android.R.color.darker_gray,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple,

        android.R.color.darker_gray,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple,

        android.R.color.darker_gray,
        android.R.color.holo_red_light,
        android.R.color.holo_blue_dark,
        android.R.color.holo_purple
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageGridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_grid_item_image, parent, false))
    }

    override fun getItemCount(): Int {
        return 16
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageGridViewHolder).bind(colors[position],position){view->
            onItemClickListener.getItemImage(view,position)
        }
    }

    class ImageGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(color: Int, position: Int, onItemClick: (view:View) -> Unit){
            itemView.container.setBackgroundColor(ContextCompat.getColor(itemView.context,color))
            itemView.textViewPosition.text = position.toString()
            itemView.container.setOnClickListener {
                ViewCompat.setTransitionName(itemView.textViewPosition, "title_${position}")
                onItemClick.invoke(itemView.textViewPosition)
            }
        }
    }
}