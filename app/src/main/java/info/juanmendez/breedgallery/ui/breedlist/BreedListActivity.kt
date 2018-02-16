package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import info.juanmendez.breedgallery.R
import info.juanmendez.breedgallery.databinding.ActivityPetlistBinding
import info.juanmendez.breedgallery.ui.breedlist.adapter.BreedListAdapter
import info.juanmendez.breedgallery.ui.services.BreedListVM
import info.juanmendez.breedgallery.ui.services.BreedListObservable
import kotlinx.android.synthetic.main.activity_petlist.view.*
import org.androidannotations.annotations.*

@DataBound
@EActivity(R.layout.activity_petlist)
class BreedListActivity : AppCompatActivity(), BreedListView {

    @BindingObject
    lateinit var mBinding:ActivityPetlistBinding

    @Bean
    lateinit var mPresenter: BreedListPresenter

    @ViewById
    lateinit var recyclerView:RecyclerView

    @ViewById
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

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

        swipeRefreshLayout.setOnRefreshListener {
            mPresenter.refreshPetList()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun getLifeCycle():Lifecycle=lifecycle

    override fun getPetsObservable(): BreedListObservable {
        return ViewModelProviders.of( this ).get( BreedListVM::class.java).petListObservable
    }
}