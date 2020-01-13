package ir.apptaste.android.di.component

import dagger.BindsInstance
import dagger.Component
import ir.apptaste.android.di.module.MainViewModelModule
import ir.apptaste.android.di.scope.MainActivityScope
import ir.apptaste.android.view.MainActivity
import ir.apptaste.android.view_model.MainViewModel

@MainActivityScope
@Component(
    modules = [MainViewModelModule::class],
    dependencies = [AppComponent::class]
)
interface MainActivityComponent {

    fun provideMainViewModel(): MainViewModel

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): MainActivityComponent

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun mainActivity(mainActivity: MainActivity): Builder
    }
}