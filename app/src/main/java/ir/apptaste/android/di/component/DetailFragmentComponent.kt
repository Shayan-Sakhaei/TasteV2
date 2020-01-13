package ir.apptaste.android.di.component

import dagger.BindsInstance
import dagger.Component
import ir.apptaste.android.di.scope.ResultFragmentScope
import ir.apptaste.android.view.fragment.DetailFragment

@ResultFragmentScope
@Component(dependencies = [AppComponent::class])
interface DetailFragmentComponent {


    fun inject(detailFragment: DetailFragment)

    @Component.Builder
    interface Builder {

        fun build(): DetailFragmentComponent

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun detailFragment(detailFragment: DetailFragment): Builder
    }
}
