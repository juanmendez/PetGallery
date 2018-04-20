package info.juanmendez.breedgallery.data

import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.api.BreedDataSourceRemote

@Module
class RepositoryModule {
    @Provides
    fun provideBreedClientHttp(): BreedDataSourceRemote = BreedDataSourceRemote()
}