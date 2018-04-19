package info.juanmendez.breedgallery.data

import dagger.Component
import info.juanmendez.breedgallery.AppModule
import info.juanmendez.breedgallery.ui.breedlist.BreedListActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface RepositoryComponent {
    fun inject(o: BreedListActivity)
}