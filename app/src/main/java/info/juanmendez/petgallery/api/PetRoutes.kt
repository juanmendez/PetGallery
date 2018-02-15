package info.juanmendez.petgallery.api

/**
 * Created by juan on 2/13/18.
 */
class PetRoutes {
    companion object {
        const val PET_URL = "https://dog.ceo"
        const val ALL_BREEDS = "/api/breeds/list"
        const val PICS_BY_BREED = "/api/breed/{breed}/images"
    }
}