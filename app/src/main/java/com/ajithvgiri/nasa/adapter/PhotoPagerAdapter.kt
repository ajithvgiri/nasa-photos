package com.ajithvgiri.nasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.data.model.Photos
import com.ajithvgiri.nasa.utils.loadImage
import kotlinx.android.synthetic.main.pager_item.view.*


class PhotoPagerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //array list of photos from Nasa
    private var listOfPhotos = ArrayList<Photos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageGridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pager_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfPhotos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageGridViewHolder).bind(listOfPhotos[position],position){view->
        }
    }

    class ImageGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(photo: Photos, position: Int, onItemClick: (view:View) -> Unit){
            itemView.imageView.loadImage(photo.hdurl,photo.url)
            itemView.pager.setOnClickListener {
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