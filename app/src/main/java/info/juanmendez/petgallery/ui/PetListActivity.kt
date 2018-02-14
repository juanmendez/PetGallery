package info.juanmendez.petgallery.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import info.juanmendez.petgallery.R
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_petlist)
class PetListActivity : AppCompatActivity() {

    @Bean
    lateinit var presenter: PetListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.register(this)
    }
}