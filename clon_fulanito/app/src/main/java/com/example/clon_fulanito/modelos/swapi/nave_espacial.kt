package com.example.clon_fulanito.modelos.swapi

data class NaveEspacial(

    val name: String,
    val model: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val max_atmosphering_speed: String,
    val crew: String,
    val passengers: String,
    val cargo_capacity: String,
    val consumables: String,
    val hyperdrive_rating: String,
    val MGLT: String,
    val starship_class: String, //Peliculas en las que aparece
    val pilots: List<String>, //Pilotos que lo han usado
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String

){
    fun obtenerIDNave(): Int {
        TODO()
    }

    fun obtenerListaPeliculas(): List<Int>{
        TODO()
    }

    fun obtenerListaPilotos(): List<Int>{
        TODO()
    }
}