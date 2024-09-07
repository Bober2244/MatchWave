package dev.bober.utils

sealed class ScreenState<out T> {
    abstract val data: T?
    data class Success<T>(override val data: T) : ScreenState<T>()
    data class Loading<T>(override val data: T? = null) : ScreenState<T>()
    data class Error<T>(val error: Throwable, override val data: T? = null) : ScreenState<T>()
}