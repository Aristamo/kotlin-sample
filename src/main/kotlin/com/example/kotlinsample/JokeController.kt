package com.example.kotlinsample

import com.example.kotlinsample.entity.Joke

interface JokeController {
    fun addJoke(joke: Joke): Boolean

    fun numberOfJokes(): Int

    fun getAllJokes(): List<Joke>

    fun deleteJoke(joke: Joke): Boolean

    fun getJokesByTag(tag: String): List<Joke>
}
