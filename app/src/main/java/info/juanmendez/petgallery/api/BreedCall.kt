package info.juanmendez.petgallery.api

/**
 * Created by juan on 2/13/18.
 */
interface BreedCall<T> {
    fun onResponse( response:T )
    fun onError( exception: Exception )
}