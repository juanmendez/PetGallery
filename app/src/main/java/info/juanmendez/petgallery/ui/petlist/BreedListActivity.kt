package info.juanmendez.petgallery.ui.petlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import info.juanmendez.petgallery.R
import info.juanmendez.petgallery.databinding.ActivityPetlistBinding
import info.juanmendez.petgallery.ui.petlist.petlist_adapter.BreedListAdapter
import info.juanmendez.petgallery.ui.services.BreedListVM
import info.juanmendez.petgallery.ui.services.BreedListObservable
import org.androidannotations.annotations.*

@DataBound
@EActivity(R.layout.activity_petlist)
class BreedListActivity : AppCompatActivity(), BreedListView {

    @BindingObject
    lateinit var mBinding:ActivityPetlistBinding

    @Bean
    lateinit var mPresenter: BreedListPresenter

    @ViewById(R.id.breedlist_rv)
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.register(this)
    }

    @AfterViews
    fun afterViews() {
        mBinding.breedListObservable = getPetsObservable()
        var linearLayoutManager = LinearLayoutManager( this )
        linearLayoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = BreedListAdapter(layoutInflater, this)
    }

    override fun getLifeCycle():Lifecycle=lifecycle

    override fun getPetsObservable(): BreedListObservable {
        return ViewModelProviders.of( this ).get( BreedListVM::class.java).petListObservable
    }
}