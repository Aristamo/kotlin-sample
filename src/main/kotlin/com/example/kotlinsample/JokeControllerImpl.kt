package com.example.kotlinsample

import com.example.kotlinsample.entity.Joke

class JokeControllerImpl : JokeController {
    private val jokes = mutableListOf<Joke>()

    override fun addJoke(joke: Joke): Boolean {
        if (jokes.contains(joke)) {
            return false
        }
        return jokes.add(joke)
    }

    override fun numberOfJokes(): Int = jokes.size

    override fun getAllJokes(): List<Joke> = jokes.toList()

    override fun deleteJoke(joke: Joke): Boolean = jokes.remove(joke)

    override fun getJokesByTag(tag: String): List<Joke> {
        return jokes.filter { it.tag.contains(tag) }
    }

}
