package info.juanmendez.breedgallery.ui.breedlist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import butterknife.ButterKnife
import info.juanmendez.breedgallery.R
import info.juanmendez.breedgallery.databinding.ActivityPetlistBinding
import info.juanmendez.breedgallery.ui.breedlist.adapter.BreedListAdapter
import info.juanmendez.breedgallery.ui.services.BreedListObservable
import info.juanmendez.breedgallery.ui.services.BreedListVM
import org.androidannotations.annotations.*

@DataBound
@OptionsMenu(R.menu.menu)
@EActivity(R.layout.activity_petlist)
class BreedListActivity: AppCompatActivity(), BreedListView {

    @BindingObject
    lateinit var binding: ActivityPetlistBinding

    @Bean
    lateinit var presenter: BreedListPresenter

    @ViewById(R.id.petlist_recyclerview) lateinit var recyclerView:   RecyclerView
    @ViewById(R.id.petlist_refreshlayout) lateinit var refreshLayout: SwipeRefreshLayout

    @AfterInject
    fun afterInject() {
        presenter.register(this)
    }

    @AfterViews
    fun afterViews() {

        //The view doesn't use binding but it's beneficial for our recyclerView
        binding.breedListObservable = getBreadListObservable()
        drawRecyclerView()

        //user can refresh through the screen as well as through menuRefresh()
        refreshLayout.setOnRefreshListener {
            presenter.refreshPetList()
            refreshLayout.isRefreshing = false
        }
    }

    @OptionsItem
    fun menuRefresh(){
        presenter.refreshPetList()
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