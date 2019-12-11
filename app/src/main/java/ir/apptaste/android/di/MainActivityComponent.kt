package ir.apptaste.android.di

import dagger.BindsInstance
import dagger.Component
import ir.apptaste.android.di.module.MainViewModelModule
import ir.apptaste.android.view.MainActivity

@Component(modules = [MainViewModelModule::class],
    dependencies = [AppComponent::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): MainActivityComponent

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun mainActivity(mainActivity: MainActivity): Builder
    }
}