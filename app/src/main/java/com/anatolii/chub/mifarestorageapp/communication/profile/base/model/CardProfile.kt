package com.anatolii.chub.mifarestorageapp.communication.profile.base.model

import com.anatolii.chub.mifarestorageapp.communication.profile.base.ProfileFieldConverter

/**
 * The abstract class represents the entire data that storing on the card
 */
abstract class CardProfile<T : ProfileField> {

    val map = HashMap<Class<out T>, T>()

    /**
     * Returns the specification that describes how
     */
    abstract val spec: Map<Class<out T>, ProfileFieldConverter<out T>>

    fun put(value: T) {
        val key = value::class.java
        if (!spec.containsKey(key)) {
            throw UnsupportedItemException("item with type $key is not supported by current implementation")
        }
        map[key] = value
    }

}