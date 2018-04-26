package info.juanmendez.breedgallery

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class AppModule(private val context: Application) {
    init {
        Realm.init(context)

        val configuration =
            RealmConfiguration.Builder().name("info.juanmendez.breedgallery.default").build()

        Realm.setDefaultConfiguration(configuration)
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}