package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import info.juanmendez.breedgallery.api.BreedCall
import info.juanmendez.breedgallery.api.BreedClientHttp
import info.juanmendez.breedgallery.api.models.Breed
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import timber.log.Timber

/**
 * Created by juan on 2/13/18.
 */
@EBean
class BreedListPresenter : LifecycleObserver {

    private lateinit var mView: BreedListView

    @Bean
    lateinit var mHttp: BreedClientHttp

    fun register( view: BreedListView): BreedListPresenter {
        mView = view
        mView.getLifeCycle().addObserver(this)

        return this
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){

        if( mView.getPetsObservable().breedList.isEmpty() ){
            refreshPetList()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){

    }

    fun refreshPetList(){

        mHttp.getBreeds( object: BreedCall<List<Breed>> {
            override fun onError(exception: Exception) {
                Timber.e( exception.message )
            }

            override fun onResponse(response: List<Breed>) {
                mView.getPetsObservable().breedList = response
            }
        })
    }
}