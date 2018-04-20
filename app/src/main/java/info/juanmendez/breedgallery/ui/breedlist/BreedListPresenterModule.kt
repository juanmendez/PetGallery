package info.juanmendez.breedgallery.ui.breedlist

import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.api.BreedDataSourceRemote

@Module
class BreedListPresenterModule(val view: BreedListContract.View) {

    @Provides fun provideView() = view

    @Provides
    fun provideBreedListPresenter(breedDataSourceRemote: BreedDataSourceRemote, view: BreedListContract.View ) =
            BreedListPresenter( view, breedDataSourceRemote )
}