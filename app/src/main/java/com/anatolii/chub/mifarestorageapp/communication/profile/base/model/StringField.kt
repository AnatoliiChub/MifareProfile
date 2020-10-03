package com.anatolii.chub.mifarestorageapp.communication.profile.base.model

import java.nio.charset.StandardCharsets.UTF_8

open class StringField(val value: String) : ProfileField {

    override val raw = value.toByteArray(UTF_8)

    override fun toString() = "${this::class.java} [ $value ]."

}