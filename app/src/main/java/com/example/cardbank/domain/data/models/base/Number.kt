package com.example.cardbank.domain.data.models.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Number(
    val length: Int?,
    val luhn: Boolean?
) : Parcelable