package info.juanmendez.breedgallery.api.models

/**
 * Created by Juan Mendez on 2/13/18.
 */
data class Breed( var name:String ){
    var pictureList:List<String> = listOf<String>()
}