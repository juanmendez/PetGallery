package info.juanmendez.breedgallery.ui.breedlist

import android.databinding.BindingAdapter
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * This binding adapter is attached to a viewGroup
 * and it searches for those child views which are ImageViews,
 * in that way it is able to load those images based on given url list
 */
@BindingAdapter("picassoImageGroup")
fun picassoImageGroup(viewGroup: ViewGroup, images:List<String> ){

    if( images.isNotEmpty() ){

        var imageList = mutableListOf<ImageView>()
        var length = viewGroup.childCount
        val picasso = Picasso.with( viewGroup.context )

        (0 until length)
                .filter { viewGroup.getChildAt(it) is ImageView }
                .mapTo(imageList) { viewGroup.getChildAt(it) as ImageView }

        imageList.forEachIndexed( {index, image ->
            run {
                picasso.load(images[index]).fit().centerCrop().into(image)
                image.invalidate()
            }
        })
    }
}
