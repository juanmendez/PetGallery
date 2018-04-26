package info.juanmendez.breedgallery.services

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ServicesModule {

    @Provides
    fun provideNetworkService( context: Context ):NetworkService = NetworkService( context )
}