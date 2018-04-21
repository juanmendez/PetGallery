package info.juanmendez.breedgallery.data.schedulers

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@Module
class SchedulerModule {

    @Provides
    @RunOn(SchedulerType.IO)
    fun provideIo(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @RunOn(SchedulerType.COMPUTATION)
    fun provideComputation(): Scheduler {
        return Schedulers.computation()
    }

    @Provides
    @RunOn(SchedulerType.UI)
    fun provideUi(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
