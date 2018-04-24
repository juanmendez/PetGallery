package info.juanmendez.breedgallery.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Breed (
    @PrimaryKey var name: String
) : RealmObject() {
    var pictureList: List<String> = listOf()
}