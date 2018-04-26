package info.juanmendez.breedgallery.ui.breedlist

import dagger.Module
import dagger.Provides

@Module
class BreedListPresenterModule(val view: BreedListContract.View) {

    @Provides
    fun provideView() = view
}