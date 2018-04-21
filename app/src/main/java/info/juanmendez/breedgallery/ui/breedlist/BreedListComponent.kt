package info.juanmendez.breedgallery.ui.breedlist

import dagger.Component
import info.juanmendez.breedgallery.data.RepositoryComponent
import info.juanmendez.breedgallery.data.schedulers.SchedulerModule
import info.juanmendez.breedgallery.ui.base.ActivityScope
import info.juanmendez.breedgallery.ui.breedlist.adapter.BreedItemHolder

@ActivityScope
@Component(
    modules = [BreedListPresenterModule::class, SchedulerModule::class],
    dependencies = [RepositoryComponent::class]
)
interface BreedListComponent {
    fun inject(activity: BreedListActivity)
    fun inject(breedItemHolder: BreedItemHolder)
}