package info.juanmendez.breedgallery.services

import android.content.Context
import android.net.ConnectivityManager

class NetworkService(context: Context) {

    private val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isOnline(): Boolean = connectivityManager.activeNetworkInfo?.let {
        it.isAvailable && it.isConnected
    } == true
}
