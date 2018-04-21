package info.juanmendez.breedgallery.ui.breedlist

import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceRemote

@Module
class BreedListPresenterModule(val view: BreedListContract.View) {

    @Provides fun provideView() = view
}