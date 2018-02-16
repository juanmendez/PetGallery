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
import info.juanmendez.petgallery.ui.petlist.petlist_adapter.PetListAdapter
import info.juanmendez.petgallery.ui.services.PetListViewModel
import info.juanmendez.petgallery.ui.services.PetListObservable
import org.androidannotations.annotations.*

@DataBound
@EActivity(R.layout.activity_petlist)
class PetListActivity : AppCompatActivity(), PetListView {

    @BindingObject
    lateinit var mBinding:ActivityPetlistBinding

    @Bean
    lateinit var mPresenter: PetListPresenter

    @ViewById(R.id.petlist_rv)
    lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.register(this)
    }

    @AfterViews
    fun afterViews() {
        mBinding.petListObservable = getPetsObservable()
        var linearLayoutManager = LinearLayoutManager( this )
        linearLayoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = PetListAdapter(layoutInflater, this)
    }

    override fun getLifeCycle():Lifecycle=lifecycle

    override fun getPetsObservable(): PetListObservable {
        return ViewModelProviders.of( this ).get( PetListViewModel::class.java).petListObservable
    }
}