package com.example.model.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AddOrEditModel(
    val title: MutableState<String> = mutableStateOf(""),
    val imageUrl: MutableState<String> = mutableStateOf(""),
    val note: MutableState<String> = mutableStateOf(""),
    val date: MutableState<String> = mutableStateOf(""),
    val isEdited: MutableState<Boolean> = mutableStateOf(false)
)