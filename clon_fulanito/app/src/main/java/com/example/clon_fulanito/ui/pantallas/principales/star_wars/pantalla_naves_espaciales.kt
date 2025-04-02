package com.example.clon_fulanito.ui.pantallas.principales.star_wars

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.clon_fulanito.vista_modelos.SWAPIModelo

@Composable
fun PantallaNavesEspaciales(modifier: Modifier){
    val vm_swapi = SWAPIModelo()

    val pagina_actual by vm_swapi.pagina_actual.observeAsState(null)

    LaunchedEffect(Unit) {
        vm_swapi.descargar_pagina()
    }

    Column(modifier = modifier){
        if(pagina_actual == null){
            Text("CARGANDO")
        }
        else{
            Text("Resultados")

            LazyColumn {
                items(pagina_actual!!.results){ nave_espacial ->
                    Text("NAVE: ${nave_espacial.name}")
                }
            }
        }
    }
}