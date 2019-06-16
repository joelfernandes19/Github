package joel.fernandes.github

import android.app.Application
import joel.fernandes.cache.CacheLibrary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@App) }
        CacheLibrary.init(this@App)
    }
}