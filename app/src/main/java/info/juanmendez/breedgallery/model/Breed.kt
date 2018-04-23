package info.juanmendez.breedgallery.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
data class Breed constructor( @PrimaryKey var name: String): RealmModel {
    var pictureList: List<String> = listOf()
}