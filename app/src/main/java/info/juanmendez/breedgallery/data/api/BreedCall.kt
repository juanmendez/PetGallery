package info.juanmendez.breedgallery.data.api

interface BreedCall<T> {
    fun onResponse(response: T)
    fun onError(exception: Exception)
}