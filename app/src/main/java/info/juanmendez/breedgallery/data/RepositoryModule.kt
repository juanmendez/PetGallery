package info.juanmendez.breedgallery.data

import android.content.Context
import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.api.BreedClientHttp
import info.juanmendez.breedgallery.data.api.BreedClientHttp_

@Module
class RepositoryModule {

    @Provides
    fun provideBreedClientHTTP(context: Context): BreedClientHttp =
        BreedClientHttp_.getInstance_( context )
}