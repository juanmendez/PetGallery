package info.juanmendez.breedgallery

import android.app.Application
import info.juanmendez.breedgallery.data.DaggerRepositoryComponent
import info.juanmendez.breedgallery.data.RepositoryComponent
import timber.log.BuildConfig
import timber.log.Timber

class AndroidApp : Application() {

    val repositoryComponent: RepositoryComponent by lazy {
        DaggerRepositoryComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        handleTimber()
    }

    private fun handleTimber() {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String, message: String, t: Throwable) {
                    return
                }
            })
        }
    }
}