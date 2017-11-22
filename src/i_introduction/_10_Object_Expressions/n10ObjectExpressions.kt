package i_introduction._10_Object_Expressions

import util.TODO
import util.doc10
import java.util.*

fun todoTask10(): Nothing = TODO(
    """
        Task 10.
        Read about object expressions that play the same role in Kotlin as anonymous classes in Java.

        Add an object expression that provides a comparator to sort a list in a descending order using 'java.util.Collections' class.
        In Kotlin you use Kotlin library extensions instead of java.util.Collections,
        but this example is still a good demonstration of mixing Kotlin and Java code.
    """,
    documentation = doc10()
)

fun task10(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    // sort takes Comparator type as second argument
    // kotlin equivalent of an anonymous class with a function override.
//    Collections.sort(arrayList, object: Comparator<Int> {
//        // body to override functions..
//        override fun compare(o1: Int, o2: Int) = o2 - o1
//    })
    // lambda way:
     Collections.sort(arrayList, {x, y -> y - x})
    return arrayList
}