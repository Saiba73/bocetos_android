package com.example.daggerhilt

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VistaModeloCometarios @Inject constructor(): ViewModel(){
    var comentarios = mutableStateOf("Escribe texto aqui")
}