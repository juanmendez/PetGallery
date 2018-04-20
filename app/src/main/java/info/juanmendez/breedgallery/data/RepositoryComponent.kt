package info.juanmendez.breedgallery.data

import dagger.Component
import info.juanmendez.breedgallery.AppModule
import info.juanmendez.breedgallery.data.api.BreedClientHttp
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface RepositoryComponent {
    fun provideBreedClientHttp(): BreedClientHttp
}