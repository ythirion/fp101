println("1. Refactor the reverse function by not using var anymore")
fun reverse(str: String): String {
    if (str.isEmpty()) return ""
    var reversed = ""
    for (i in str.length - 1 downTo 0) {
        reversed += str[i]
    }
    return reversed
}

println(reverse("t'nod ohw esoht dna yranib dnatsrednu ohw esoht : dlrow eht ni elpoep fo sepyt 01 era erehT"))

fun String.head() = this[0]
fun String.tail() = this.drop(1)
fun reverseRec(str: String): String {
    return if(str.isEmpty()) "" else reverseRec(str.tail()) + str.head()
}

println(reverseRec("t'nod ohw esoht dna yranib dnatsrednu ohw esoht : dlrow eht ni elpoep fo sepyt 01 era erehT"))

println("2. Refactor the printlist function to parameterize hardcoded stuffs")
fun printList(): Unit = (1..10).forEach { println("the number is $it") }
printList()

println("3. Refactor those 2 functions by identifying common stuff")
fun product(n: Int): Int {
    var product = 1
    for (i in 1..n)
        product *= i
    return product
}

fun sum(n: Int): Int {
    var sum = 0
    for (i in 1..n)
        sum += i
    return sum
}

product(10)
sum(10)

println("4. Refactor this function to make it more explicit")
fun divide(top: Int, bottom: Int): Int {
    return when(bottom) {
        0 -> throw IllegalArgumentException("division by 0")
        else -> top / bottom
    }
}
divide(89, 2)

println("5. Refactor the ugly function by chaining callbacks with continuations")
data class UserInput(val key: Int)

fun doSomething(input: UserInput): UserInput? = input.copy(input.key / 2)
fun doSomethingElse(input: UserInput): UserInput? = input.copy(input.key / 2)
fun doAThirdStuff(input: UserInput): UserInput? = input.copy(input.key / 2)

fun uglyFunction(input: UserInput): UserInput? {
    val x = doSomething(input)
    if (x != null) {
        val y = doSomethingElse(x)
        if(y != null) {
            val z = doAThirdStuff(y)
            if(z != null) {
                val result = z
                return result
            }
            else return null
        }
        else return null
    }
    else return null
}
uglyFunction(UserInput(90))