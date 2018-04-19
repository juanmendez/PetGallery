package info.juanmendez.breedgallery

import android.app.Application
import org.androidannotations.annotations.EApplication
import timber.log.BuildConfig
import timber.log.Timber

@EApplication
class AndroidApp:Application() {

    override fun onCreate() {
        super.onCreate()
        handleTimber()
    }

    private fun handleTimber(){
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