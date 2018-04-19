package info.juanmendez.breedgallery.api

/**
 * Created by Juan Mendez on 2/13/18.
 * We decouple association with retrofit or any other http client
 * throughout the app
 */
interface BreedCall<T> {
    fun onResponse(response: T)
    fun onError(exception: Exception)
}