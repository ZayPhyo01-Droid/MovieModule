package com.common.extension

fun Double?.orZero () = this ?: 0.0
fun Boolean?.orFalse () = this ?: false
fun Int?.orZero () = this ?: 0