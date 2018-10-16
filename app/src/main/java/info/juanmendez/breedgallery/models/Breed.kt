package info.juanmendez.breedgallery.models

/**
 * Created by Juan Mendez on 2/13/18.
 */
data class Breed(val name: String, val subBreed: List<String>) {
    var pictureList: List<String> = listOf<String>()
}