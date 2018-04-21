package info.juanmendez.breedgallery.data

import dagger.Component
import info.juanmendez.breedgallery.AppModule
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceRemote
import info.juanmendez.breedgallery.data.repository.breed.BreedRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface RepositoryComponent {
    fun provideBreedRepository(): BreedRepository
}