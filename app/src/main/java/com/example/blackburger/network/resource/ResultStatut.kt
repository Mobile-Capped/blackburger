package com.example.blackburger.network.resource

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface ResultStatut<out T> {
    data class Success<T>(val data: T) : ResultStatut<T>
    data class Error(val exception: Throwable? = null) : ResultStatut<Nothing>
    object Loading : ResultStatut<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<ResultStatut<T>> {
    return this
        .map<T, ResultStatut<T>> {
            Log.i(ResultStatut::class.java.name, "Success : $it")
            ResultStatut.Success(it)
        }
        .onStart {
            Log.i(ResultStatut::class.java.name, "Loading")
            emit(ResultStatut.Loading)
        }
        .catch {
            Log.i(ResultStatut::class.java.name, "Error : ${it.message}")
            emit(ResultStatut.Error(it))
        }
}