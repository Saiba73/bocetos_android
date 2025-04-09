package com.example.clon_fulanito.vista_modelos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clon_fulanito.API.SWAPI.RepositorioSWAPI
import com.example.clon_fulanito.modelos.swapi.PaginaContenedora
import kotlinx.coroutines.launch

enum class Estados{
    cargando,
    mostrando_lista_de_naves,
    mostrando_nave,
    error,
    registrarFrustracion
}



class SWAPIModelo: ViewModel(){
    private val repositorio = RepositorioSWAPI()

    private  val _pagina_actual = MutableLiveData<PaginaContenedora>()
    val pagina_actual: LiveData<PaginaContenedora> = _pagina_actual

    private var pagina_siguiente: String? = null

    private val _estado_actual = MutableLiveData<Estados>(Estados.cargando)
    val estado_actual: LiveData<Estados> = _estado_actual

    fun descargar_pagina(pagina: Int = 1){
        _estado_actual.postValue(Estados.cargando)
        Log.v("Cargando pagina", "pagina: ${pagina}")
        viewModelScope.launch {
            try{
                val pagina = repositorio.obtener_naves_espaciales(pagina)
                //pagina_siguiente = pagina.next
                _pagina_actual.postValue(pagina)
                _estado_actual.postValue(Estados.mostrando_lista_de_naves)
            }
            catch (error: Exception){
                Log.v("DESCARGA DE PAGINA SWAPI", "${error.message}")
                _estado_actual.postValue(Estados.error)
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

    fun indicar_un_problema(){
        _estado_actual.postValue(Estados.registrarFrustracion)
    }
}