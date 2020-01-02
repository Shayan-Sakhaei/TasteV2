package ir.apptaste.android.di.component

import dagger.BindsInstance
import dagger.Component
import ir.apptaste.android.di.module.ResultFragmentModule
import ir.apptaste.android.di.scope.ResultFragmentScope
import ir.apptaste.android.view.adapter.ResultListAdapter
import ir.apptaste.android.view.fragment.ResultFragment

@ResultFragmentScope
@Component(modules = [ResultFragmentModule::class], dependencies = [AppComponent::class])
interface ResultFragmentComponent {

    fun provideResultListAdapter(): ResultListAdapter
    fun inject(resultFragment: ResultFragment)

    @Component.Builder
    interface Builder {
        fun build(): ResultFragmentComponent

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun resultFragment(resultFragment: ResultFragment): Builder
    }


}