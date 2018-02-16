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
import info.juanmendez.breedgallery.ui.services.BreedListObservable
import info.juanmendez.breedgallery.ui.services.BreedListVM
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

        //The view doesn't use mBinding but it's beneficial for our recyclerView
        mBinding.breedListObservable = getBreadListObservable()
        drawRecyclerView()

        //user can refresh through the screen as well as through menu item
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter.refreshPetList()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun drawRecyclerView() {
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayout.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = BreedListAdapter(layoutInflater, this)
    }

    override fun getLifeCycle():Lifecycle=lifecycle


    override fun getBreadListObservable(): BreedListObservable {
        //this is useful as binding between presenter and view
        return ViewModelProviders.of( this ).get( BreedListVM::class.java).breedListObservable
    }
}