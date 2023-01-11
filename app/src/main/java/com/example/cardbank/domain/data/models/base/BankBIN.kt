package com.example.cardbank.domain.data.models.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BankBIN(
    val bank: Bank?,
    val brand: String?,
    val country: Country?,
    val number: Number?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
) : Parcelable