package uz.najottalim.work_n_hire

import android.app.Application
import timber.log.Timber

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}