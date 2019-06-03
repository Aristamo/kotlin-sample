package com.example.kotlinsample

/**
 *  Documentation can be written like this
 *
 *  See all the basics under this link: https://kotlinlang.org/docs/reference/basic-syntax.html
 *
 *  All the annotations are for th edoc available like @author, @exception, @param, @return, @sample....
 */
fun main() {



    // Declaration of variables

    // Defining a variable
    var a: Int
    a = 10


    // Can be inlined
    var b: Int = 10

    // Type can be omitted if known at compiletime
    var c = 10

    // a, b and c can be reassigned
    a = 11
    b = 12
    c = 13

    // Immutability
    val d = 20

    // d cannot be changed
//    d = 21
    // -> Compile error!











    println("// SUMS AND STRINGS")

    /*
    Here I have a multiline comment
    This part will be 25 if 5 and 25 are added
     */
    val sum = add(5, 20)















    // Type inference
    val string = "Here is some pretty String"
    // -> This will be inferred as a String

    // Template Strings
    val normalWay = "The sum of 5 and 20 is " + sum
    val templateWay = "The sum of 5 and 20 is $sum"

    println(normalWay)
    println(templateWay)


    val formattedString = addAndFormat(100, -25)

    println(formattedString)
















    // Control flow
    println("\n// BIGGEST NUMBER:")

    val biggestNumber = maxOf(20, 21)

    println("The biggest number is $biggestNumber")























    println("\n// NUMBER SIZES")

    val sizeA = howBigIsThatNumber(20)
    val sizeB = howBigIsThatNumber(1)
    val sizeC = howBigIsThatNumber(-100)
    val sizeD = howBigIsThatNumber(100)

    val bigTemplateString = """
        |   20 is <$sizeA>
        |    1 is <$sizeB>
        | -100 is <$sizeC>
        |  100 is <$sizeD>
    """.trimMargin("|")

    println(bigTemplateString)
























    println(
        """

         // GENERATED MATRIX
    """.trimIndent()
    )

    val zeroOrOneMatrix = listOf( // LIST CREATION
        listOf(0, 1, 1, 0),
        listOf(0, 0, 0, 0),
        listOf(1, 0, 0, 1),
        listOf(0, 1, 1, 0)
    )


    val generatedTemplate = """
        ${zeroOrOneMatrix.map {
        "| ${it.map { i ->
            zeroOrOne(i)
        }.joinToString(" ")
        }"
    }.joinToString("\n")}""".trimMargin("|")

    println(generatedTemplate)






















    println("""



        // NULLABILITY
    """.trimIndent())



    val somethingNull: String? = null
    var somthingChangeableAndNull: Int? = null

    // does not compile
//    val thisCannotBeNull: String = null


    println("$somethingNull and $somthingChangeableAndNull")

    somthingChangeableAndNull = 20

    println("$somethingNull and $somthingChangeableAndNull")
}



























fun add(a: Int, b: Int): Int {
    // Adds up some numbers
    return a + b
}


fun addAndFormat(a: Int, b: Int): String = "The sum of $a and $b is ${a + b}"

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}


fun howBigIsThatNumber(number: Int): String = when {
    number in 1..10 -> "Small" // RANGES
    number in 11..20 -> "Greater"
    number > 20 -> "HUGE"
    else -> "Tiny"
}


fun zeroOrOne(x: Int): String = when (x) {
    0 -> "Zero"
    1 -> "One"
    else -> "Not Binary"
}


