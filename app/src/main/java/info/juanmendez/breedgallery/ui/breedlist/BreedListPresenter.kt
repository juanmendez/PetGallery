package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import info.juanmendez.breedgallery.api.BreedCall
import info.juanmendez.breedgallery.api.MyJsonBreedClientHttp
import info.juanmendez.breedgallery.models.BreedListResponse
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

/**
 * Created by Juan Mendez on 2/13/18.
 */
@EBean
class BreedListPresenter : LifecycleObserver {

    private lateinit var mView: BreedListView

    @Bean
    lateinit var mMyJsonHttp: MyJsonBreedClientHttp

    fun register(view: BreedListView): BreedListPresenter {
        mView = view
        mView.getLifeCycle().addObserver(this)

        return this
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {

        /**
         * ViewModel has a list of breeds, so initially we detect
         * if we need to go and fetch the content
         */
        if (mView.getBreadListObservable().breedList.isEmpty()) {
            refreshPetList()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    fun refreshPetList() {

        //gotcha, app broke due to a late call from its view while being destroyed
        if (!mView.getLifeCycle().currentState.equals(Lifecycle.State.RESUMED))
            return


        mMyJsonHttp.getBreeds(object : BreedCall<BreedListResponse> {
            override fun onError(exception: Exception?) {

            }

            override fun onResponse(response: BreedListResponse) {
                mView.getBreadListObservable().breedList = response.list
            }
        })
    }
}