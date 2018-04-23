package info.juanmendez.breedgallery.data

import android.content.Context
import com.loumalnatis.android.data.repository.Local
import com.loumalnatis.android.data.repository.Remote
import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSource
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceLocal
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceRemote
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Remote
    @Singleton
    fun provideBreedDataSourceRemote( remote:BreedDataSourceRemote): BreedDataSource = remote

    @Provides
    @Local
    @Singleton
    fun provideBreedDataSourceLocal( local:BreedDataSourceLocal ): BreedDataSource = local


    @Provides
    @Singleton
    fun provideRealmConfiguration( context: Context ): RealmConfiguration {
        Realm.init(context)

        return RealmConfiguration
            .Builder()
            .name("info.juanmendez.breedgallery.default")
            .build().apply {
                Realm.setDefaultConfiguration( this )
            }
    }

    @Provides
    fun provideRealm( realmConfiguration: RealmConfiguration ):Realm=Realm.getDefaultInstance()
}