package info.juanmendez.petgallery.ui

import android.support.v7.app.AppCompatActivity
import info.juanmendez.petgallery.R
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_petlist)
class PetListActivity : AppCompatActivity() {

    @Bean
    lateinit var presenter: PetListPresenter

    @AfterInject
    fun whenOnCreate() {
        presenter.register(this)
    }
}