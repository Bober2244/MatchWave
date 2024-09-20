package dev.bober.utils

sealed class Resource<out T> {
    abstract val data: T?
    data class Success<T>(override val data: T) : Resource<T>()
    data class Loading<T>(override val data: T? = null) : Resource<T>()
    data class Error<T>(val error: Throwable, override val data: T? = null) : Resource<T>()

    fun getDataOrNull() : T? = data
}