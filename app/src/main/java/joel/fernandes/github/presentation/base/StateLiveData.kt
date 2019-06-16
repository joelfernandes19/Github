package joel.fernandes.github.presentation.base

import androidx.lifecycle.MutableLiveData


class StateLiveData<T> : MutableLiveData<StateData<T>>() {

    fun postError(message: String) {
        postValue(StateData<T>().error(message))
    }

    fun postSuccess(data: T) {
        postValue(StateData<T>().success(data))
    }
}