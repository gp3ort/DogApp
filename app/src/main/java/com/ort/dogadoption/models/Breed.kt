package com.ort.dogadoption.models

data class Breed(
    val name: String,
    val subBreeds: List<SubBreed>?
)
