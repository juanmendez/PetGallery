package info.juanmendez.breedgallery.data.repository.breed
import info.juanmendez.breedgallery.model.Breed
import io.reactivex.Flowable
import io.realm.Realm
import javax.inject.Inject

class BreedDataSourceLocal @Inject constructor() : BreedDataSource {

    override fun getBreeds(): Flowable<List<Breed>> {

        lateinit var realm:Realm
        lateinit var breedsAsFlowable: Flowable<List<Breed>>

        try {
            realm = Realm.getDefaultInstance()
            realm.executeTransaction{
                breedsAsFlowable = it.where( Breed::class.java )
                    .findAll().asFlowable()
                    .flatMap{  Flowable.fromIterable(it) }
                    .doOnNext{ realm.copyFromRealm(it) }
                    .toList()
                    .toFlowable()
            }
        } finally {
            realm.close()
        }

        return breedsAsFlowable
    }

    override fun getPicsByBreed(breedName: String): Flowable<List<String>> {

        lateinit var realm:Realm
        lateinit var picturesAsFlowable: Flowable<List<String>>

        try {
            realm = Realm.getDefaultInstance()
            realm.executeTransaction{
                it.where( Breed::class.java ).equalTo("name", breedName )
                    .findFirst()?.let {
                        picturesAsFlowable = it.asFlowable<Breed>().map { it.pictureList }
                    }
            }
        } finally {
            realm.close()
        }

        return picturesAsFlowable
    }

    override fun addBreed(breed: Breed) {
         lateinit var realm:Realm

        try {
            realm = Realm.getDefaultInstance()
            realm.executeTransaction{
                it.insertOrUpdate(breed)
            }
        } finally {
            realm.close()
        }
    }

    override fun deleteAllBreeds() {

        lateinit var realm:Realm

        try {
            realm = Realm.getDefaultInstance()
            realm.executeTransaction{
                it.delete( Breed::class.java )
            }
        } finally {
            realm.close()
        }
    }

    override fun addPicsByBreed(breedName: String, pics: List<String>) {

        lateinit var realm:Realm

        try {
            realm = Realm.getDefaultInstance()
            realm.executeTransaction{
                it.where(Breed::class.java)
                    .equalTo( "name", breedName )
                    .findFirst()?.let{
                        it.pictureList = pics
                    }
            }
        } finally {
            realm.close()
        }
    }
}