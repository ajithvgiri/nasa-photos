package com.ajithvgiri.nasa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.ajithvgiri.nasa.R


class ImagePagerAdapter : PagerAdapter() {


    /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val view: View = LayoutInflater.from(container.context).inflate(R.layout.pager_item, null)
        val pager: ConstraintLayout = view.findViewById(R.id.pager)
        when(position){
            0-> pager.setBackgroundColor(ContextCompat.getColor(view.context,R.color.colorPrimaryDark))
            1-> pager.setBackgroundColor(ContextCompat.getColor(view.context,R.color.colorPrimary))
            2-> pager.setBackgroundColor(ContextCompat.getColor(view.context,R.color.colorAccent))
        }

//        imageView.setImageDrawable(context.getResources().getDrawable(getImageAt(position)))
        container.addView(view)
        return view
    }

    /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` == view
    }

    override fun getCount(): Int {
        return 3
    }

}