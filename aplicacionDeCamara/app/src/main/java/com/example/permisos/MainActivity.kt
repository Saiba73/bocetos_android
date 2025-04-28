package com.example.permisos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.permisos.pantallas.PantallaCamara
import com.example.permisos.ui.theme.PermisosTheme

class MainActivity : ComponentActivity() {
    private val evaluacion_permisos_de_camara =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ esta_garantizado ->
            if(esta_garantizado){
                VistaDeLaCamara()
            }
            else{
                // En caso de que el usuario diga no ¿Que mostramos?
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when(PackageManager.PERMISSION_GRANTED){
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) -> {
                VistaDeLaCamara()
            }
            else->{
                evaluacion_permisos_de_camara.launch(Manifest.permission.CAMERA)
            }
        }
        enableEdgeToEdge()

    }

    private fun VistaDeLaCamara(){
        setContent {
            PermisosTheme {
                Surface () {
                    PantallaCamara()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PermisosTheme {
        Greeting("Android")
    }
}