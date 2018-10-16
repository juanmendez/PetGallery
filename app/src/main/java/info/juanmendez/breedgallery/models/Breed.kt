package info.juanmendez.breedgallery.models

/**
 * Created by Juan Mendez on 2/13/18.
 */
data class Breed(var name: String, var subBreed: List<String>? = listOf()) {
    var pictureList: List<String> = listOf<String>()
}