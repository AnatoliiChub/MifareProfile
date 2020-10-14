package com.anatolii.chub.profilereader.extensions

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

@MainThread
inline fun <T> LiveData<T>.observeSafe(
    owner : LifecycleOwner,
    crossinline observer : (t: T) -> Unit
) {
    observe(owner, {
        if(it != null){
            observer(it)
        }
    })
}