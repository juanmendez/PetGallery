package info.juanmendez.breedgallery.ui.breedlist

import dagger.Component
import info.juanmendez.breedgallery.data.RepositoryComponent
import info.juanmendez.breedgallery.services.ServicesModule
import info.juanmendez.breedgallery.ui.base.ActivityScope
import info.juanmendez.breedgallery.ui.breedlist.adapter.BreedItemHolder
import info.juanmendez.breedgallery.ui.breedlist.adapter.BreedListAdapter
import info.juanmendez.breedgallery.utils.schedulers.SchedulerModule

@ActivityScope
@Component(
    modules = [BreedListPresenterModule::class, SchedulerModule::class],
    dependencies = [RepositoryComponent::class]
)
interface BreedListComponent {
    fun inject(activity: BreedListActivity)
    fun inject(breedListAdapter: BreedListAdapter)
}