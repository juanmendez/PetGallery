package info.juanmendez.breedgallery.ui.breedlist

import dagger.Component
import info.juanmendez.breedgallery.data.RepositoryComponent
import info.juanmendez.breedgallery.ui.base.ActivityScope

@ActivityScope
@Component(
    modules = [BreedListPresenterModule::class],
    dependencies = [RepositoryComponent::class]
)
interface BreadListComponent {
    fun inject(activity: BreedListActivity)
}