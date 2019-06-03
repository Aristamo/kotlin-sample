package com.example.kotlinsample.entity

data class Joke(val jokeDescription: String, val tag: List<String> = emptyList())
