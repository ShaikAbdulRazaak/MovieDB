package com.razzaaq.moviedb.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringProviderImpl @Inject constructor(@ApplicationContext val context: Context) :
    StringProvider {
    override fun getString(string: Int): String = context.getString(string)

    override fun getQuantityString(
        string: Int,
        quantity: Int,
        vararg formatArgs: Any
    ): String = context.resources.getQuantityString(string, quantity, *formatArgs)
}