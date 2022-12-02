package com.scientifichamster.common.extensions

fun Any?.isNull() = this == null
fun Any?.isNotNull() = this != isNull()