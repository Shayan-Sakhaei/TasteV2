package ir.apptaste.android.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ir.apptaste.android.di.module.DatabaseModule
import ir.apptaste.android.di.module.NetworkModule
import ir.apptaste.android.model.api.TasteWebService
import ir.apptaste.android.model.persistence.ResultDao

@Component(
    modules = [NetworkModule::class,
        DatabaseModule::class]
)
interface AppComponent {

    fun provideService(): TasteWebService
    fun provideDao(): ResultDao

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}