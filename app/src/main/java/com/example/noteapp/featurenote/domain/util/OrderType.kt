package com.example.noteapp.featurenote.domain.util

sealed class OrderType {

    object Ascending: OrderType()
    object Descending: OrderType()
}
