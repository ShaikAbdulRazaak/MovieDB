package com.razzaaq.moviedb.ui.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Detail(val movieId: Int) : Parcelable

@Serializable
@Parcelize
data object Dashboard : Parcelable
