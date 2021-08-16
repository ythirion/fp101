// String interpolation
val language = "Kotlin"
val sheetName = "Let's learn $language basics"
println(sheetName)

// VARIABLES
var x: Int = 5
x = 6
// Constants
val const = 5
// Explicit type
val explicit: Double = 5.0

// FUNCTIONS
// Define function : need types for every arg
fun square(x: Int) = x * x

// Use default parameter
fun squareWithDefaultParameter(x: Int = 4) = x * x

// Create type alias -> not available in scripts
//typealias R = Double

// Anonymous functions
(1..5).map { x -> x * x }

// it arg
(1..5).map { it * 2 }
(1..5).reduce { acc, element -> acc + element }
(1..5).map { it * it }

// Block style returns last expression
(1..5).map { x ->
    val y = x * 2
    println(y)
    y
}

// Pipeline style
(1..5)
    .filter { it % 2 == 0 }
    .map { it * 2 }

// Function composition
fun compose(
    g: (x: Double) -> Double,
    h: (x: Double) -> Double
): (Double) -> Double = { x -> g(h(x)) }

fun minus1times2(x: Double) = compose({ it * 2 }, { it - 1 })(x)
println(minus1times2(4.0))

// Currying
fun sum(a: Int, b: Int): Int = a + b
val curriedSum: (Int) -> Int = { sum(it, it) }
curriedSum(1)

// Generic Type
fun <T> mapMake(g: (T) -> T, seq: List<T>) = seq.map(g)
mapMake({ x -> x / 2 }, (1..5).toList())

// Varargs
fun sum(vararg args: Int) = args.reduce { acc, element -> acc + element }
sum(1, 2, 3, 4)

// DATA STRUCTURES
// Tuple literal -> Tuple3
Triple(1, 2, 3)
// Tuple destructuring
var (a, b, c) = Triple(1, 2, 3)

// Immutable list
var xs = listOf(1, 2, 3)
var ys = listOf(4, 5, 6)
// Indexing
xs[2]
// Range
(1..5) == 1 until 6

// CONTROL CONSTRUCTS
fun happy() = println(";-)")
fun sad() = println(":-(")

// Conditional
if (true) happy() else sad()

// While
while (x < 5) {
    println(x)
    x += 1
}

xs.filter { it % 2 == 0 }.map { it * 10 }

// PATTERN MATCHING
val v42 = 42
when (v42) {
    42 -> println("42")
    // Other cases
    else -> println("Not 42")
}

// OBJECT ORIENTATION
// x is only available in class body.
class C1(x: Double)

// Automatic public member defined
class C2(val x: Double)

val c1 = C2(9.0)
c1.x

// interfaces
interface APerson {
    val name: String
}

// Data classes -> records
// all constructor parameters are public and immutable
data class Student(override val name: String, val year: Int) : APerson
data class Teacher(override val name: String, val specialty: String) : APerson

// Pattern matching on case classes
fun personToString(p: APerson): String = when (p) {
    is Student -> "$p.name is a student in Year $p.year."
    is Teacher -> "$p.name teaches $p.whatTheyTeach."
    else -> throw IllegalArgumentException("Not supported person type")
}
// Data equality
val bart1 = Student("Bart Simpson", 2021)
val bart2 = Student("Bart Simpson", 2021)
bart1 == bart2
// case class has an automatically-generated copy method
// when you need to perform the process of a) cloning an object and b) updating one or more of the fields during the cloning
val homer = bart1.copy(name = "Homer Simpson")

// OPTIONS -> no out of the box
// If you want some you can use -> Arrow : https://arrow-kt.io/docs/patterns/monads/

// NULL SAFETY
// Does it mean I need to handle NullPointerException everywhere ?
// In Kotlin, the type system distinguishes between references that
// can hold null (nullable references) and those that cannot (non-null references)
var str: String = "abc"
// str = null -> DO NOT COMPILE

// To allow null you must explicitly say it
val nullStr: String? = null

// Safe calls
println(nullStr?.length)

// Perform operation if not null with let
val listWithNulls: List<String?> = listOf("Kotlin", null, "Clojure", null, "Scala")
for (item in listWithNulls) {
    item?.let { println(it) }
}

// Instead of this
val length: Int = if (nullStr != null) str.length else -1
// You can use the Elvis operator ?:
val elvisLength = nullStr?.length ?: -1

// METHOD EXTENSIONS
fun Int.isEven(): Boolean = this % 2 == 0
listOf(1, 4, 7, 8, 12).map { it.isEven() }

// Infix
infix fun Int.sq(x: Int): Int = x * x
println("Example of infix fun on sq") 3 sq 4