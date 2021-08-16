println("1. Refactor the reverse function by not using var anymore")
fun String.head() = this[0]
fun String.tail() = this.drop(1)

fun reverse(str: String): String {
    return if(str.isEmpty()) "" else reverse(str.tail()) + str.head()
}
println(reverse("t'nod ohw esoht dna yranib dnatsrednu ohw esoht : dlrow eht ni elpoep fo sepyt 01 era erehT"))

println("2. Refactor the printlist function to parameterize hardcoded stuffs")
fun actOnList(list: List<Int>, action: (Int) -> Unit): Unit = list.forEach { action(it) }
fun printList(list: List<Int>) = actOnList(list) { i -> println("the number is $i") }
printList((1..10).toList())

println("3. Refactor those 2 functions by identifying common stuff")
fun transform(initialValue: Int, n: Int, action: (Int, Int) -> Int) = (1..n).fold(initialValue, action)
fun product(n: Int): Int = transform(1, n) { product, i -> product * i }
fun sum(n: Int): Int = transform(0, n) { sum, i -> sum + i }

product(10)
sum(10)

println("4. Refactor this function to make it more explicit")
println("Constrain the input : make it impossible to represent an invalid state")
class NonZeroInteger(private val value: Int) {
    fun toInt() = value
}
fun Int.toNonZeroInteger() : NonZeroInteger {
    return when(this) {
        0 -> throw IllegalArgumentException("0 is not authorized")
        else -> NonZeroInteger(this)
    }
}
fun divide(top: Int, bottom: NonZeroInteger): Int = top / bottom.toInt()
divide(89, 2.toNonZeroInteger())
println("Read this article : https://enterprisecraftsmanship.com/posts/specification-pattern-always-valid-domain-model/\n")

println("Extend the output (Could return an Option here with vavr or Arrow)")
fun divideWithExtendedOutput(top: Int, bottom: Int): Int? {
    return when(bottom) {
        0 -> null
        else -> top / bottom
    }
}
divideWithExtendedOutput(89, 2)

println("5. Refactor the ugly function by chaining callbacks with continuations")
data class UserInput(val key: Int)

fun doSomething(input: UserInput?): UserInput? = input?.copy(key = input.key / 2)
fun doSomethingElse(input: UserInput?): UserInput? = input?.copy(key = input.key / 2)
fun doAThirdStuff(input: UserInput?): UserInput? = input?.copy(key = input.key / 2)

println("Definitely use monads from vavr or Arrow for this kind of purpose")
println("Much more explicit and you can use flatMap on it")
println("Check scala solution to see the difference")

fun uglyFunctionRefactored(input: UserInput): UserInput? {
    return doSomething(input)
        .let { doSomethingElse(it) }
        .let { doAThirdStuff(it) }
}

fun uglyFunctionRefactoredShorten(input: UserInput): UserInput? = doAThirdStuff(doSomethingElse(doSomething(input)))

uglyFunctionRefactored(UserInput(90))
uglyFunctionRefactoredShorten(UserInput(90))