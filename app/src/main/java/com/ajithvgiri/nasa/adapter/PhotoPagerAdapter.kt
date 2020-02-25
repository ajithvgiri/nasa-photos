package com.ajithvgiri.nasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        (holder as ImageGridViewHolder).bind(listOfPhotos[position],position)
    }

    class ImageGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(photo: Photos, position: Int){
            itemView.imageView.loadImage(photo.hdurl,photo.url)
            itemView.textViewDate.text = photo.date
            itemView.textViewCopyRight.text = if (photo.copyright.isNullOrEmpty()){
                ""
            }else{
                itemView.context.getString(R.string.copyright,photo.copyright)
            }
            itemView.textViewTitle.text = photo.title
            itemView.textViewExplanation.text = photo.explanation
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