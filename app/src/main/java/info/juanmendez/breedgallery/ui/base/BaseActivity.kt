package info.juanmendez.breedgallery.ui.base

import android.arch.lifecycle.LifecycleRegistry
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import info.juanmendez.breedgallery.AndroidApp
import info.juanmendez.breedgallery.data.RepositoryComponent

open class BaseActivity : RxAppCompatActivity() {
    val lifecycleRegistry = LifecycleRegistry(this)

    fun getRepositoryComponent(): RepositoryComponent {
        return (application as AndroidApp).repositoryComponent
    }
}