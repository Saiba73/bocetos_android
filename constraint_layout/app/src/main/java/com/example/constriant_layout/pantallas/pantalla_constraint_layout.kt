package com.example.constriant_layout.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

// https://developer.android.com/develop/ui/compose/layouts/constraintlayout
// Informacion sobre el constraint layout
@Composable
fun PantallaDeCuadros(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val gradiente = listOf(Color.Cyan, Color.Magenta, Color.Green)
        val boton = createRef()
        val cajon_texto = createRef()
        val tamaño_de_cajas = 75.dp
        val (caja_a, caja_b, caja_c, caja_d, caja_e, caja_f, caja_g, caja_h, caja_i, caja_j, caja_k) = createRefs()
        val josue =createRef()

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_a){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)

        }){
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                Text("A", style = TextStyle(Color.White), textAlign = TextAlign.Center, modifier = Modifier.constrainAs(josue){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        }

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_b){
            top.linkTo(caja_a.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(caja_c.start)
        }){
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text("B", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, style = TextStyle(Color.White))
            }


        }


        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_c){
            top.linkTo(caja_a.bottom, margin = 50.dp)
            start.linkTo(caja_b.end)
            end.linkTo(parent.end)
        }){
            Text("C")
        }

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_d){
            top.linkTo(caja_b.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(caja_e.start)
        }){
            Text("D")
        }


        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_e){
            top.linkTo(caja_b.bottom, margin = 50.dp)
            start.linkTo(caja_d.start)
            end.linkTo(caja_f.end)
        }){
            Text("E")
        }

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_f){
            top.linkTo(caja_c.bottom, margin = 50.dp)
            start.linkTo(caja_e.end)
            end.linkTo(parent.end)
        }){
            Text("F")
        }

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_g){
            top.linkTo(caja_f.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(caja_i.start)
        }){
            Text("G")
        }

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_h){
            top.linkTo(caja_g.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(caja_i.start)
        }){
            Text("H")
        }

        Box(modifier = Modifier.size(tamaño_de_cajas * 2.65f).background(Color.DarkGray).constrainAs(caja_i){
            top.linkTo(caja_f.bottom, margin = 50.dp)
            start.linkTo(caja_g.start, margin = 80 .dp)
            end.linkTo(parent.end)
        }){
            Text("I")
        }

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_j){
            top.linkTo(caja_h.bottom, margin = 50.dp)
            start.linkTo(parent.start)
            end.linkTo(caja_k.end)
        }){
            Text("J")
        }

        Box(modifier = Modifier.size(tamaño_de_cajas).background(Color.DarkGray).constrainAs(caja_k){
            top.linkTo(caja_h.bottom, margin = 50.dp)
            start.linkTo(caja_j.start)
            end.linkTo(parent.end)
        }){
            Text("K")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Previsualizacion(){
    PantallaDeCuadros()
}