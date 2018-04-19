package info.juanmendez.breedgallery.utils

import android.databinding.BindingAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * This binding adapter is attached to a viewGroup
 * and it searches for those child views which are ImageViews,
 * in order to load them based on urlList
 */
@BindingAdapter("picassoImageGroup")
fun picassoImageGroup(viewGroup: ViewGroup, urlList: List<String>) {

    if(urlList.isNotEmpty()) {

        viewGroup.visibility = View.VISIBLE

        var imageList = mutableListOf<ImageView>()
        var length = viewGroup.childCount
        val picasso = Picasso.with(viewGroup.context)

        (0 until length).filter { viewGroup.getChildAt(it) is ImageView }.mapTo(imageList) { viewGroup.getChildAt(it) as ImageView }

        imageList.forEachIndexed({ index, image ->
            run {
                picasso.load(urlList[index]).fit().centerCrop().into(image)
            }
        })
    } else {
        //turning visibility off removes blinking from previous rows
        viewGroup.visibility = View.INVISIBLE
    }
}

@BindingAdapter("textCapitalize")
fun textCapitalize(textView: TextView, text: String) {
    textView.text = text.capitalize()
}
