package info.juanmendez.breedgallery

import android.app.Application
import org.androidannotations.annotations.EApplication
import timber.log.BuildConfig
import timber.log.Timber

/**
 * Created by Juan Mendez on 2/13/18.
 */
@EApplication
class BreedGalleryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        handleTimber()
    }

    private fun handleTimber() {
        if (BuildConfig.DEBUG) {
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