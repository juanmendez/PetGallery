package info.juanmendez.breedgallery.data

import com.loumalnatis.android.data.repository.Local
import com.loumalnatis.android.data.repository.Remote
import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSource
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceLocal
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceRemote
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Remote
    fun provideBreedDataSourceRemote(remote: BreedDataSourceRemote): BreedDataSource = remote

    @Provides
    @Local
    fun provideBreedDataSourceLocal(local: BreedDataSourceLocal): BreedDataSource = local
}