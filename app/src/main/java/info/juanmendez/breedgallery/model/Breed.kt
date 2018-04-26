package info.juanmendez.breedgallery.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Breed(
    @PrimaryKey var name: String = ""
) : RealmObject() {
    var pictureList: RealmList<String> = RealmList()
}