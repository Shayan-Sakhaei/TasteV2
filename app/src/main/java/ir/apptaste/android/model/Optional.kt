package ir.apptaste.android.model

sealed class Optional<T : Any>{

    class Success<T : Any>(val data : T) : Optional<T>()
    class Failed<T : Any>(val error : String) : Optional<T>()

}