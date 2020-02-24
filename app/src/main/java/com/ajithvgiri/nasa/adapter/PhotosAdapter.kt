package com.ajithvgiri.nasa.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ajithvgiri.nasa.R
import com.ajithvgiri.nasa.data.model.Photos
import com.ajithvgiri.nasa.utils.GlideApp
import com.ajithvgiri.nasa.utils.NasaGlideModule.Companion.drawableCrossFadeFactory
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.layout_grid_item_photo.view.*

class PhotosAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

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

        }
    }

    class ImageGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(photo: Photos, position: Int, onItemClick: (view:View) -> Unit){
            GlideApp.with(itemView).load(photo.url).transition(withCrossFade(drawableCrossFadeFactory))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        Log.e("ImageGridViewHolder","onLoadFailed ${e?.message}")
                        return e?.message.isNullOrEmpty()
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        //do something when picture already loaded
                        itemView.progress.visibility = View.GONE
                        return false
                    }
                })
                .into(itemView.imageView)
            itemView.container.setOnClickListener {
                ViewCompat.setTransitionName(itemView.imageView, "title_${position}")
                onItemClick.invoke(itemView.imageView)
            }
        }
    }

    // helper function for adding images to listOfPhotos
    fun addPhotos(listOfPhotos: List<Photos>){
        val position = listOfPhotos.size
        listOfPhotos.forEach {
            this.listOfPhotos.add(it)
            notifyItemInserted(position - 1)
        }
    }
}