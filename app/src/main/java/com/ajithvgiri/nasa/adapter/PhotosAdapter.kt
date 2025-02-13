package com.ajithvgiri.nasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.data.model.Photos
import com.ajithvgiri.nasa.ui.home.OnItemClickListener
import com.ajithvgiri.nasa.utils.loadImage
import kotlinx.android.synthetic.main.layout_grid_item_photo.view.*

class PhotosAdapter(private var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //array list of photos from Nasa
    private var listOfPhotos = ArrayList<Photos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageGridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_grid_item_photo, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfPhotos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageGridViewHolder).bind(listOfPhotos[position],position){view->
            onItemClickListener.getItemImage(view,position)
        }
    }

    class ImageGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(photo: Photos, position: Int, onItemClick: (view:View) -> Unit){
            itemView.imageView.loadImage(photo.url)
            itemView.textViewTitle.text = photo.title
            itemView.container.setOnClickListener {
                ViewCompat.setTransitionName(itemView.imageView, "title_${position}")
                onItemClick.invoke(itemView.imageView)
            }
        }
    }

    // helper function for adding images to listOfPhotos
    fun addPhotos(listOfPhotos: List<Photos>){
        this.listOfPhotos.clear()
        val position = listOfPhotos.size
        listOfPhotos.forEach {
            this.listOfPhotos.add(it)
            notifyItemInserted(position - 1)
        }
    }
}