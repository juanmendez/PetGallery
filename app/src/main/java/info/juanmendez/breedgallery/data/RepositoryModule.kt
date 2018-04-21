package info.juanmendez.breedgallery.data

import com.loumalnatis.android.data.repository.Remote
import dagger.Module
import dagger.Provides
import info.juanmendez.breedgallery.data.repository.breed.BreedDataSourceRemote

@Module
class RepositoryModule {

    @Provides
    @Remote
    fun provideBreedDataSourceRemote( remote:BreedDataSourceRemote): BreedDataSourceRemote = remote
}