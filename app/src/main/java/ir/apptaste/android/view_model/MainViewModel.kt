package ir.apptaste.android.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.apptaste.android.model.Repository
import ir.apptaste.android.model.api.ApiResponse
import ir.apptaste.android.model.api.ResultResponse

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val resultList = MutableLiveData<ArrayList<ResultResponse>>()
    private val resultListError = MutableLiveData<Boolean>()
    private val resultListLoading = MutableLiveData<Boolean>()
    private var mSelectedResultResponse: ResultResponse? = null


    fun getResultListError(): LiveData<Boolean> {
        return resultListError
    }

    fun getResultListLoading(): LiveData<Boolean> {
        return resultListLoading
    }

    fun fetchResult(userQuery: String, userType: String, userLimit: String) {
        repository.fetchResult(userQuery, userType, userLimit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ApiResponse> {
                override fun onSuccess(response: ApiResponse) {
                    resultListLoading.value = false
                    resultList.value = response.similar.results
                    resultListError.value = false
                }

                override fun onSubscribe(d: Disposable) {
                    this@MainViewModel.disposable.add(d)
                }

                override fun onError(e: Throwable) {
                    resultListLoading.value = false
                    resultListError.value = true
                }
            })
    }

    fun getResultList(): LiveData<ArrayList<ResultResponse>> {
        return resultList
    }

    fun setSelectedResultResponse(selectedResultResponse: ResultResponse) {
        mSelectedResultResponse = selectedResultResponse
    }

    fun getSelectedResultResponse():ResultResponse? {
        return mSelectedResultResponse
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}