package info.juanmendez.breedgallery.data.repository.breed

import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmList
import io.realm.kotlin.where
import javax.inject.Inject

class BreedDataSourceLocal @Inject constructor() : BreedDataSource {

    val realm: Realm = Realm.getDefaultInstance()

    override fun getBreeds(forceRemote: Boolean): Flowable<List<Breed>> {

        val realm = Realm.getDefaultInstance()
        val list = mutableListOf<Breed>()

        try {
            realm.executeTransaction {

                it.where<Breed>().findAll().forEach { list.add(realm.copyFromRealm(it)) }
            }
        } finally {
            if(!realm.isClosed) realm.close()
        }

        return Flowable.just(list)
    }

    override fun getPicsByBreed(breedName: String): Flowable<RealmList<String>> {

        val realm = Realm.getDefaultInstance()
        val picturesAsFlowable = RealmList<String>()

        try {
            realm.executeTransaction {
                it.where(Breed::class.java).equalTo("name", breedName).findFirst()?.let {
                        picturesAsFlowable.addAll(it.pictureList)
                    }
            }
        } finally {
            realm.close()
        }

        return Flowable.just(picturesAsFlowable)
    }

    override fun addBreed(breed: Breed) {

        val realm = Realm.getDefaultInstance()

        try {
            realm.executeTransaction {
                it.insertOrUpdate(breed)
            }
        } finally {
            if(!realm.isClosed) realm.close()
        }
    }

    override fun deleteAllBreeds() {

        val realm = Realm.getDefaultInstance()

        try {
            realm.executeTransaction {
                it.delete(Breed::class.java)
            }
        } finally {
            realm.close()
        }
    }

    override fun addPicsByBreed(breedName: String, pics: RealmList<String>) {

        val realm = Realm.getDefaultInstance()

        try {
            realm.executeTransaction {
                it.where<Breed>().equalTo("name", breedName).findFirst()?.let {
                        it.pictureList.apply {
                            clear()
                            addAll(pics)
                        }
                    }
            }
        } finally {
            if(!realm.isClosed) realm.close()
        }
    }
}