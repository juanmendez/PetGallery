package info.juanmendez.breedgallery.ui.breedlist

import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.api.BreedClientHttp

@Module
class BreedListPresenterModule(val view: BreedListContract.View) {

    @Provides fun provideView() = view

    @Provides
    fun provideBreedListPresenter( breedClientHttp: BreedClientHttp, view: BreedListContract.View ) =
            BreedListPresenter( view, breedClientHttp )
}