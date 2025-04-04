package com.example.clon_fulanito.ui.pantallas.principales.star_wars

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clon_fulanito.vista_modelos.SWAPIModelo
import kotlin.math.round

@Composable
fun PantallaNavesEspaciales(modifier: Modifier){
    val vm_swapi = SWAPIModelo()

    val pagina_actual by vm_swapi.pagina_actual.observeAsState(null)

    LaunchedEffect(Unit) {
        vm_swapi.descargar_pagina()
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        if(pagina_actual == null){
            Text("CARGANDO", fontSize = 25.sp)
        }
        else{
                Text("Resultados", fontSize = 25.sp)

                LazyColumn (modifier = Modifier.padding(top = 25.dp).background(color = Color.Red)) {
                    items(pagina_actual!!.results){ nave_espacial ->
                        Text("NAVE: ${nave_espacial.name}", modifier = Modifier.padding(top = 5.dp), color = Color.White)
                        Text("Modelo: ${nave_espacial.model}", modifier = Modifier.padding(bottom = 5.dp), color = Color.White)
                        HorizontalDivider(color = Color.Black, thickness = 3.dp)
                    }
                }

            Row(
                modifier = Modifier.fillMaxSize().height(56.dp).padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Pagina anterior",
                    modifier = Modifier.background(color = Color.Green).clickable {
                        Log.v("STARWARS", "Pagina anterior de naves")
                    })

                Text("Pagina siguiente",
                    modifier = Modifier.background(color = Color.Green).clickable {
                        Log.v("STARWARS", "Pagina siguiente de naves")
                    })
            }


        }
    }
}