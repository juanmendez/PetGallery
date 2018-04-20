package info.juanmendez.breedgallery.data

import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.api.BreedClientHttp

@Module
class RepositoryModule {
    @Provides
    fun provideBreedClientHTTP(): BreedClientHttp = BreedClientHttp()
}