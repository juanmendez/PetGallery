package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import info.juanmendez.breedgallery.api.BreedCall
import info.juanmendez.breedgallery.api.BreedClientHttp
import info.juanmendez.breedgallery.api.models.Breed
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.Presenter
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract.View
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean
import timber.log.Timber

/**
 * Created by Juan Mendez on 2/13/18.
 */
@EBean
class BreedListPresenter : Presenter {

    private lateinit var listView: View

    @Bean
    lateinit var http: BreedClientHttp

    override fun register(view: View): Presenter {
        listView = view
        listView.getLifeCycle().addObserver(this)

        return this
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {

        /**
         * ViewModel has a list of breeds, so initially we detect
         * if we need to go and fetch the content
         */
        if(listView.getBreadListObservable().breedList.isEmpty()) {
            refreshPetList()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    override fun refreshPetList() {

        //gotcha, app broke due to a late call from its view while being destroyed
        if(!listView.getLifeCycle().currentState.equals(Lifecycle.State.RESUMED)) return


        http.getBreeds(object : BreedCall<List<Breed>> {
            override fun onError(exception: Exception) {
                //TODO: implement an error display in the View
                Timber.e(exception.message)
            }

            override fun onResponse(response: List<Breed>) {
                listView.getBreadListObservable().breedList = response
            }
        })
    }
}