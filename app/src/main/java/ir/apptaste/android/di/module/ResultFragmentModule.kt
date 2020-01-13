package ir.apptaste.android.di.module

import dagger.Module
import dagger.Provides
import ir.apptaste.android.di.scope.ResultFragmentScope
import ir.apptaste.android.view.adapter.ResultListAdapter

@Module
object ResultFragmentModule {

    @Provides
    @ResultFragmentScope
    fun provideResultListAdapter(): ResultListAdapter {
        return ResultListAdapter()
    }
}