package com.example.clon_fulanito.ui.pantallas.principales

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.clon_fulanito.vista_modelos.FulanitoViewModel

@Composable
fun PantallaDePublicacion(modifier: Modifier, vm_fulanito: FulanitoViewModel){
    val comentarios by vm_fulanito.comentarios_de_publicacion.observeAsState(emptyList())

    val publicacion by vm_fulanito.publicacion_seleccionada.observeAsState(null)

    if(publicacion != null){
        Column(modifier = modifier.background(color = Color.DarkGray)) {
            Text("Titulo: ${publicacion!!.title}", color = Color.White)
            Text("${publicacion!!.body}", color = Color.White)

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {
                items(comentarios){ comentario ->
                    Text("Nombre: ${comentario.name}", color = Color.White)
                    Text("${comentario.body}", color = Color.White)
                    HorizontalDivider(color = Color.Black, thickness = 3.dp)


                }

            }
        }
    }
}