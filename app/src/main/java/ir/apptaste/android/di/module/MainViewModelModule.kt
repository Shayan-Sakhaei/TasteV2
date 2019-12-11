package ir.apptaste.android.di.module

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ir.apptaste.android.model.Repository
import ir.apptaste.android.view.MainActivity
import ir.apptaste.android.view_model.MainViewModel
import ir.apptaste.android.view_model.MainViewModelFactory

@Module
class MainViewModelModule {

    @Provides
    fun provideMainViewModel(
        activity: MainActivity,
        viewModelFactory: MainViewModelFactory
    ): MainViewModel {
        return ViewModelProviders.of(activity, viewModelFactory).get(MainViewModel::class.java)
    }

}