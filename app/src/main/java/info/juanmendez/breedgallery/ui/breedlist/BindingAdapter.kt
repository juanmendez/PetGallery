package info.juanmendez.breedgallery.ui.breedlist

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso

@BindingAdapter("picassoSrc")
fun picassoSrc(layout: LinearLayout, images:List<String> ){

    if( images.isNotEmpty() ){

        var imageList = mutableListOf<ImageView>()
        var length = layout.childCount
        val picasso = Picasso.with( layout.context )

        (0 until length)
                .filter { layout.getChildAt(it) is ImageView }
                .mapTo(imageList) { layout.getChildAt(it) as ImageView }

        imageList.forEachIndexed( {index, image ->
            run {
                picasso.load(images[index]).fit().centerCrop().into(image)
                image.invalidate()
            }
        })
    }
}
