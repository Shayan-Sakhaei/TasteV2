package ir.apptaste.android.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.apptaste.android.model.Repository
import ir.apptaste.android.model.persistence.ResultDao
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}