package info.juanmendez.breedgallery.models

data class BreedListResponse(var status: String, var list: List<Breed>)

data class RawBreedListResponse(var status: String, var message: Map<String, List<String>>)