package com.olderwold.catlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olderwold.catlist.domain.Cat
import com.olderwold.catlist.domain.CatApi

class CatListViewModel(
    private val catApi: CatApi,
) : ViewModel() {
    private val _state: MutableLiveData<State> = MutableLiveData<State>()

    val state: LiveData<State> = _state

    fun load() {
        _state.value = State.Success(
            listOf(Cat(id = "7n2", url = "https://cdn2.thecatapi.com/images/7n2.jpg"))
        )
    }

    sealed class State {
        data class Error(
            val value: Throwable
        ): State()

        object Loading: State()

        data class Success(
            val catList: List<Cat>
        ): State()
    }
}
