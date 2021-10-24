package com.vish.newsmvvm.util

//Recommended by google

/**
 * Sealed class -> its kind of abstract class but we can define that which class can inherit this class
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /**
     * only following classes are allowed to inherit this class
     */
    class Success<T>(data: T?) : Resource<T>(data)

    /**
     * in case of error message will be always present, and data is optional
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * used to show loading state
     */
    class Loading<T> : Resource<T>()
}