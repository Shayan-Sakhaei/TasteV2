package ir.apptaste.android.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.apptaste.android.model.Optional
import ir.apptaste.android.model.Repository
import ir.apptaste.android.model.api.ApiResponse
import ir.apptaste.android.model.api.ResultResponse
import ir.apptaste.android.model.persistence.ResultDao
import ir.apptaste.android.utility.mapper.ResultServerEntityMapper
import javax.inject.Inject

class MainViewModel(private val repository: Repository, private val resultDao: ResultDao) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val resultList = MutableLiveData<ArrayList<ResultResponse>>()
    private val resultListError = MutableLiveData<Boolean>()
    private val resultListLoading = MutableLiveData<Boolean>()
    private var mSelectedResultResponse: ResultResponse? = null

    @Inject
    lateinit var mResultServerEntityMapper: ResultServerEntityMapper


    fun getResultListError(): LiveData<Boolean> {
        return resultListError
    }

    fun getResultListLoading(): LiveData<Boolean> {
        return resultListLoading
    }

    fun fetchResult(userQuery: String, userType: String, userLimit: String) {
        resultListLoading.value = true
        repository.fetchAndSaveResult(userQuery, userType, userLimit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Optional<ArrayList<ResultResponse>>> {
                override fun onSuccess(response: Optional<ArrayList<ResultResponse>>) {
                    when (response) {
                        is Optional.Success -> {
                            resultListLoading.value = false
                            resultList.value = response.data
                            resultListError.value = false
                        }
                        is Optional.Failed -> {
                            resultListLoading.value = false
                            resultListError.value = true

                        }
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    this@MainViewModel.disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    //no need to do anything
                    // we already handled all error
                    // but just in case anything happens we can log it
                }
            })
    }

    @Deprecated("no need")
    private fun saveToDatabase(results: ArrayList<ResultResponse>) {
        results.forEach {
            mResultServerEntityMapper.map(it)
        }
    }


    fun getResultList(): LiveData<ArrayList<ResultResponse>> {
        return resultList
    }

    fun setSelectedResultResponse(selectedResultResponse: ResultResponse) {
        mSelectedResultResponse = selectedResultResponse
    }

    fun getSelectedResultResponse(): ResultResponse? {
        return mSelectedResultResponse
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}