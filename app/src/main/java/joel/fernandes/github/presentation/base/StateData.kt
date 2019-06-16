package joel.fernandes.github.presentation.base

import androidx.annotation.NonNull
import androidx.annotation.Nullable

class StateData<T> {

    @NonNull
    @get:NonNull
    var status: DataStatus? = null
        private set

    @Nullable
    @get:Nullable
    var data: T? = null
        private set

    @Nullable
    @get:Nullable
    var error: String = ""
        private set

    fun success(@NonNull data: T): StateData<T> {
        this.status = DataStatus.SUCCESS
        this.data = data
        this.error = ""
        return this
    }

    fun error(@NonNull message: String): StateData<T> {
        this.status = DataStatus.ERROR
        this.data = null
        this.error = message
        return this
    }


    enum class DataStatus {
        SUCCESS,
        ERROR
    }
}