package com.razzaaq.moviedb.util

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes string: Int): String
    fun getQuantityString(@PluralsRes string: Int, quantity: Int, vararg formatArgs: Any): String
}