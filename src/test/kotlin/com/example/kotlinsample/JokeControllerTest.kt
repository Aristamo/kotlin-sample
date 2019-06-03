package com.example.kotlinsample

import com.example.kotlinsample.entity.Joke
import com.example.kotlinsample.entity.SampleTags.DIFFERENT
import com.example.kotlinsample.entity.SampleTags.FUNNY
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*
import kotlin.random.Random

class JokeControllerTest {

    private lateinit var jokeController: JokeController

    @BeforeEach
    fun setup() {
        jokeController = JokeControllerImpl()
    }

    @Test
    fun `add a joke should return true`() {
        val joke = Joke("This is a funny Joke")

        expectThat(jokeController.addJoke(joke))
            .isTrue()
    }

    @Test
    fun `adding a joke updates the number of jokes`() {
        val joke = Joke("This is a funny Joke, too.")
        jokeController.addJoke(joke)

        expectThat(jokeController.numberOfJokes())
            .isGreaterThan(0)
    }

    @Test
    fun `adding multiple jokes returns the right ammount`() {
        val numberOfJokes = Random.nextInt(5, 10)

        (1..numberOfJokes).forEach {
            jokeController.addJoke(Joke("Funny Joke number $it"))
        }

        expectThat(jokeController.numberOfJokes())
            .isEqualTo(numberOfJokes)
    }


    @Test
    fun `inserting one joke returns the joke when all jokes are requested`() {
        val joke = Joke("The next funny fun Joke")

        jokeController.addJoke(joke)

        expectThat(jokeController.getAllJokes())
            .hasSize(1)
            .and {
                first().isEqualTo(joke)
            }
    }

    @Test
    fun `inserting multiple jokes returns the right number of jokes`() {
        val numberOfJokes = Random.nextInt(5, 10)
        val jokes = (1..numberOfJokes).map {
            Joke("Funny funny joke number $it")
        }

        jokes.forEach { jokeController.addJoke(it) }

        expectThat(jokeController.getAllJokes())
            .hasSize(numberOfJokes)
            .and {
                contains(jokes)
            }
    }

    @Test
    fun `if a joke is deleted it is not in the retuned list anymore`() {
        val joke = Joke("This is a not funny joke!")
        jokeController.addJoke(joke)
        expectThat(jokeController.getAllJokes())
            .first().isEqualTo(joke)

        jokeController.deleteJoke(joke)

        expectThat(jokeController.getAllJokes()).doesNotContain(joke)

    }

    @Test
    fun `a deleted joke can be readded to the controller`() {
        val joke = Joke("FUN FUN FUN FUN FUN!!!!!!!!")

        jokeController.addJoke(joke)

        jokeController.deleteJoke(joke)

        expectThat(jokeController.getAllJokes())
            .doesNotContain(joke)

        expectThat(jokeController.addJoke(joke))
            .isTrue()

        expectThat(jokeController.getAllJokes())
            .contains(joke)

    }

    @Test
    fun `no joke can be added twice`() {
        val joke = Joke("Hello Joke")
        expectThat(jokeController.addJoke(joke)).isTrue()
        expectThat(jokeController.addJoke(joke)).isFalse()

    }

    @Test
    fun `when joke with same name is inserted it shouldn't be added`() {
        val jokeFirst = Joke("This is a Joke")
        val jokeSecond = Joke("This is a Joke")

        jokeController.addJoke(jokeFirst)

        expectThat(jokeController.addJoke(jokeSecond)).isFalse()
    }

    @Test
    fun `get all jokes by a specific tag returns all jokes with this tag`() {
        val jokes = (1..5).map { Joke("This is the Joke $it with a tag!", listOf(FUNNY)) }
        jokes.forEach { jokeController.addJoke(it) }

        expectThat(jokeController.getJokesByTag(FUNNY)).hasSize(5)
    }

    @Test
    fun `adding annother joke with an different tag should not be included in the tagged search`() {
        val taggedJokes = (1..5).map { Joke("This is a Joke with a tag and the number $it!", listOf(FUNNY)) }
        val annotherJoke = Joke("I am a Joke with a differnt tag!", listOf(DIFFERENT))

        taggedJokes.forEach { jokeController.addJoke(it) }
        jokeController.addJoke(annotherJoke)

        expectThat(jokeController.getJokesByTag(FUNNY))
            .hasSize(5)
            .all {
                get { tag }.contains(FUNNY)
            }
    }


    @Test
    fun `jokes can have multiple tags`() {
        val joke = Joke("This joke has more tags", listOf(FUNNY, DIFFERENT))

        expectThat(joke.tag).contains(DIFFERENT, FUNNY)
    }

    @Test
    fun `jokes with no tag created have no tag`() {
        val joke = Joke("This joke has no tags")

        expectThat(joke.tag).isEmpty()
    }

    @Test
    fun `jokes can have dynamic created tags and can be searched by them`() {
        val joke = Joke("This is a joke with a dynamic tag", listOf("TAG A", "TAG B", "TAG C"))

        jokeController.addJoke(joke)

        expectThat(jokeController.getJokesByTag("TAG A"))
            .contains(joke)
    }


    @Test
    fun `multiple jokes can be initialised with predefined and dynamic tags`() {
        val joke = Joke("Funny Joke where new ", listOf(FUNNY, DIFFERENT, "NEW", "LOL"))
        val anotherJoke = Joke("Why Chuck Norris has a new tag....", listOf(FUNNY, DIFFERENT, "CHUCK NORRIS", "NEW", "LOL"))

        jokeController.addJoke(joke)
        jokeController.addJoke(anotherJoke)

        expectThat(jokeController.getJokesByTag("NEW")).containsExactlyInAnyOrder(joke, anotherJoke)

    }

}
