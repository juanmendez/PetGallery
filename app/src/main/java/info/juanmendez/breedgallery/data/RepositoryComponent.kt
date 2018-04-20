package info.juanmendez.breedgallery.data

import dagger.Component
import info.juanmendez.breedgallery.AppModule
import info.juanmendez.breedgallery.data.api.BreedDataSourceRemote
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface RepositoryComponent {
    fun provideBreedClientHttp(): BreedDataSourceRemote
}