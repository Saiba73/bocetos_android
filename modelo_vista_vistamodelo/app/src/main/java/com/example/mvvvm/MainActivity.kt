package com.example.mvvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvvm.modelos.ModeloCajonTexto
import com.example.mvvvm.ui.theme.MvvvmTheme

class MainActivity : ComponentActivity() {
    val modelo_vista = ModeloCajonTexto()
    val modelo_configuracion = ModeloCajonTexto()
    val modelo_publicaciones_fyp = ModeloCajonTexto()
    val modelo_publicaciones_propias = ModeloCajonTexto()
    val modelo_comentarios = ModeloCajonTexto()
    val modelo_perfil = ModeloCajonTexto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MvvvmTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        modelo_vista = modelo_vista,
                        mv_comentarios = modelo_comentarios,
                        mv_publicaciones = modelo_publicaciones_propias
                    )
                    adios(mv = modelo_vista, mv_comentarios = modelo_comentarios, mv_publicaciones = modelo_publicaciones_fyp)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, modelo_vista: ModeloCajonTexto, mv_comentarios: ModeloCajonTexto, mv_publicaciones: ModeloCajonTexto) {
    // val cajon_de_texto: String = modelo_vista.cajon_de_texto.observerAsState
    // val cajon_de_texto: String by modelo_vista.cajon_de_texto.observeAsState(initial = "Valor inicial del modelo")
    val cajon_de_texto: String by modelo_vista.cajon_de_texto.observeAsState(initial = "Valor inicial de este lado")

    Column(modifier = modifier) {
        Text(
            text = "Hello ${cajon_de_texto}!",
            modifier = Modifier.fillMaxHeight(0.05f)
        )

        TextField(onValueChange = {
            modelo_vista.pasar_nuevo_texto(it)
        }, value = cajon_de_texto)
    }

}

@Composable
fun adios(mv: ModeloCajonTexto, mv_comentarios: ModeloCajonTexto, mv_publicaciones: ModeloCajonTexto){
    val cajon_de_texto: String by mv.cajon_de_texto.observeAsState(initial = "Valor inicial de este lado")


    TextField(onValueChange = {
        mv.pasar_nuevo_texto(it)
    }, value = cajon_de_texto)

    vista_1(mv_comentarios = mv_comentarios)
    vista_2(mv_configuracion = mv, mv_publicaciones = mv_publicaciones)
}

@Composable
fun vista_1(mv_comentarios: ModeloCajonTexto){
    Text("Vista 1")
}

@Composable
fun vista_2(mv_configuracion: ModeloCajonTexto, mv_publicaciones: ModeloCajonTexto){
    Text("Vista 1")
    vista_3(mv_configuracion, mv_publicaciones = mv_publicaciones)
}

@Composable
fun vista_3(mv_configuracion: ModeloCajonTexto, mv_publicaciones: ModeloCajonTexto){
    Text("Vista 3")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvvvmTheme {

    }
}