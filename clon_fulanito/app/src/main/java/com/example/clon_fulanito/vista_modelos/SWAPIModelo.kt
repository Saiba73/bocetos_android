package com.example.clon_fulanito.vista_modelos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clon_fulanito.API.SWAPI.RepositorioSWAPI
import com.example.clon_fulanito.modelos.swapi.PaginaContenedora
import kotlinx.coroutines.launch

class SWAPIModelo: ViewModel(){
    private val repositorio = RepositorioSWAPI()

    private  val _pagina_actual = MutableLiveData<PaginaContenedora>()
    val pagina_actual: LiveData<PaginaContenedora> = _pagina_actual

    private var pagina_siguiente: String? = null

    fun descargar_pagina(pagina: Int = 1){
        Log.v("Cargando pagina", "pagina: ${pagina}")
        viewModelScope.launch {
            try{
                val pagina = repositorio.obtener_naves_espaciales(pagina)
                pagina_siguiente = pagina.next
                _pagina_actual.postValue(pagina)
            }
            catch (error: Exception){
                Log.v("DESCARGA DE PAGINA SWAPI", "${error.message}")
            }
        }
    }


    fun pasar_a_siguiente_pagina(){



        val pagina_siguiente: Int? = pagina_actual.value?.indicar_pagina_siguiente()

        if(pagina_siguiente != null){
            descargar_pagina(pagina_siguiente)

        }

    }

    fun pasar_a_anterior_pagina() {
        val pagina_anterior: Int? = pagina_actual.value?.indicar_pagina_anterior()

        if(pagina_anterior != null){
            descargar_pagina(pagina_anterior)
            Log.v("Cargando pagina", "pagina-siguiente: ${pagina_anterior}")
        }

    }
}