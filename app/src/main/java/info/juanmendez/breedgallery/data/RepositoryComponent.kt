package info.juanmendez.breedgallery.data

import android.content.Context
import dagger.Component
import info.juanmendez.breedgallery.AppModule
import info.juanmendez.breedgallery.data.api.ApiModule
import info.juanmendez.breedgallery.data.repository.breed.BreedRepository
import info.juanmendez.breedgallery.services.ServicesModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, AppModule::class, RepositoryModule::class]
)
interface RepositoryComponent {
    fun provideBreedRepository(): BreedRepository
    fun provideContext():Context
}