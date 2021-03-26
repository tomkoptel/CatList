package com.olderwold.catlist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olderwold.catlist.domain.Cat
import com.olderwold.catlist.domain.CatApi
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CatListViewModel(
    private val catApi: CatApi,
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _state: MutableLiveData<State> = MutableLiveData<State>()

    val state: LiveData<State> = _state

    fun load() {
        every20seconds
            .flatMap { loadCats() }
            .doOnError {
                Log.e("CatListViewModel", "load() failed", it)
            }
            .doOnSubscribe {
                _state.postValue(State.Loading)
            }
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    _state.postValue(State.Success(it))
                },
                onError = {
                    _state.postValue(State.Error(it))
                }
            ).addTo(compositeDisposable)
    }

    private val every20seconds = Observable.interval(20, TimeUnit.SECONDS)

    private fun loadCats() = catApi.catList().toObservable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    sealed class State {
        data class Error(
            val value: Throwable
        ) : State()

        object Loading : State()

        data class Success(
            val catList: List<Cat>
        ) : State()
    }
}
