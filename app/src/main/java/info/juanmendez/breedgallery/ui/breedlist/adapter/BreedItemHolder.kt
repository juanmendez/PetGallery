package info.juanmendez.breedgallery.ui.breedlist.adapter

import android.support.v7.widget.RecyclerView
import com.android.databinding.library.baseAdapters.BR
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import info.juanmendez.breedgallery.data.repository.breed.BreedRepository
import info.juanmendez.breedgallery.data.schedulers.RunOn
import info.juanmendez.breedgallery.data.schedulers.SchedulerType
import info.juanmendez.breedgallery.databinding.ItemPetBinding
import info.juanmendez.breedgallery.model.Breed
import info.juanmendez.breedgallery.ui.breedlist.BreedListContract
import info.juanmendez.breedgallery.ui.breedlist.viewmodel.BreedObservable
import javax.inject.Inject

class BreedItemHolder(
    var view: BreedListContract.View,
    var binding: ItemPetBinding
) : RecyclerView.ViewHolder(binding.root) {

    @Inject lateinit var breedRepository: BreedRepository

    @Inject @field:RunOn(SchedulerType.COMPUTATION) lateinit var computationScheduler: io.reactivex.Scheduler

    @Inject @field:RunOn(SchedulerType.UI) lateinit var uiScheduler: io.reactivex.Scheduler

    init {
        binding.breedObservable = BreedObservable()
        view.breedListComponent.inject( this )
    }

    fun setBreed(breed: Breed) {
        binding.breedObservable?.breed = breed

        if(breed.pictureList.isEmpty()) {

            breedRepository.getPicsByBreed(breed.name)
                .subscribeOn( computationScheduler )
                .observeOn(uiScheduler)
                .bindToLifecycle( view )
                .subscribe({
                    breed.pictureList = it
                    binding.breedObservable?.notifyPropertyChanged(BR.breed)
                })
        }
    }
}