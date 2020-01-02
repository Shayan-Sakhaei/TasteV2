package ir.apptaste.android

import android.app.Application
import ir.apptaste.android.di.component.AppComponent
import ir.apptaste.android.di.component.DaggerAppComponent

class App : Application() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}