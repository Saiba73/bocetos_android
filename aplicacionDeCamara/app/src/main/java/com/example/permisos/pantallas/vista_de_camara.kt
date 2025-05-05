package com.example.permisos.pantallas

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun PantallaCamara(){
    val ciclo_vida_dueño = LocalLifecycleOwner.current
    val contexto = LocalContext.current

    var lente_a_usar by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK) }


    val prevista = remember { Preview.Builder().build() }
    val capturador_de_imagen = remember { ImageCapture.Builder().build() }
    val vista_prevista = remember { PreviewView(contexto) }

    //val camaraX_selector = CameraSelector.Builder().requireLensFacing(lente_a_usar).build()



    LaunchedEffect(lente_a_usar) {
        val proveedor_local_camara = contexto.obtenerProveedorDeCamara()
        proveedor_local_camara.unbindAll()

        val selector = CameraSelector.Builder().requireLensFacing(lente_a_usar).build()

        proveedor_local_camara.bindToLifecycle(
            ciclo_vida_dueño,
            selector,
            prevista,
            capturador_de_imagen
        )

        //proveedor_local_camara.bindToLifecycle(ciclo_vida_dueño, camaraX_selector, prevista, capturador_de_imagen)
        prevista.setSurfaceProvider(vista_prevista.surfaceProvider)
    }



    Box(Modifier.fillMaxSize()){
        AndroidView(factory = {vista_prevista}, modifier = Modifier.fillMaxSize())

        Column(Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = {
                lente_a_usar = if(lente_a_usar == CameraSelector.LENS_FACING_BACK)
                    CameraSelector.LENS_FACING_FRONT
                else
                    CameraSelector.LENS_FACING_BACK
            }) {
                Text("Voltear Camara")
            }

            Button(onClick = {tomar_foto(capturador_de_imagen, contexto)}) {Text("Tomar Foto")}
        }
    }
}




private suspend fun Context.obtenerProveedorDeCamara(): ProcessCameraProvider =
    suspendCoroutine {
        continuacion ->
        ProcessCameraProvider.getInstance(this).also{
            proveedor_camara -> proveedor_camara.addListener({
                continuacion.resume(proveedor_camara.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }

private fun tomar_foto(capaturador_imagen: ImageCapture, contexto: Context){
    val nombre_archivo = "CapturaFoto.jpeg"

    val valores_del_contenido = ContentValues().apply{
        put(MediaStore.MediaColumns.DISPLAY_NAME, nombre_archivo)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/nuestra_app")
        }
    }

    val salida_foto = ImageCapture.OutputFileOptions.Builder(
        contexto.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        valores_del_contenido
    ).build()

    capaturador_imagen.takePicture(
        salida_foto,
        ContextCompat.getMainExecutor(contexto),
        object: ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Log.v("CAPTURA_EXITO", "Exito, no ha pasado nada")
            }

            override fun onError(exception: ImageCaptureException) {
                Log.v("CAPTURA_ERROR", "Se identifico el siguiente error: ${exception.message}")
            }
        }
    )
}