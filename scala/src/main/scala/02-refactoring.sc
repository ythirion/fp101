println("1. Refactor the reverse function by not using var anymore")
def reverse(str: String): String = {
  if (str.isEmpty) return ""
  var reversed = ""
  for (i <- str.length - 1 to 0 by -1)
    reversed += str(i)
  reversed
}

reverse("Je suis un String")

def reverseYield(str: String): String = {
  (for (i <- str.length - 1 to 0 by -1)
    yield str(i)).mkString
}

reverseYield("Je suis un String")

def reverseRec(str: String): String = {
  if (str.isEmpty) ""
  else reverseRec(str.tail) + str.head
}

reverseRec("Je suis un String")

println("2. Refactor the printlist function to parameterize hardcoded stuffs")
def printList(list: List[Int], print: Int => Unit): Unit = list.foreach(print)
printList(List.range(1, 10), i => println(s"the number is $i"))

println("3. Refactor those 2 functions by identifying common stuff")
def product(n: Int) = {
  var product = 1
  for (i <- 1 to n)
    product *= i
  product
}

def sum(n: Int) = {
  var sum = 0
  for (i <- 1 to n)
    sum += i
  sum
}

product(10)
sum(10)

println("4. Refactor this function to make it more explicit")
def divide(top: Int, bottom: Int): Int = {
  bottom match {
    case 0 => throw new IllegalArgumentException("division by 0")
    case _ => top / bottom
  }
}
divide(89, 2)

println("5. Refactor the ugly function by chaining callbacks with continuations")

case class UserInput(key: Int)

def doSomething(input: UserInput): Option[UserInput] = Some(input.copy(input.key / 2))
def doSomethingElse(input: UserInput): Option[UserInput] = Some(input.copy(input.key / 2))
def doAThirdStuff(input: UserInput): Option[UserInput] = Some(input.copy(input.key / 2))

def uglyFunction(input: UserInput): Option[UserInput] = {
  val x = doSomething(input)
  if (x.isDefined) {
    val y = doSomethingElse(x.get)
    if (y.isDefined) {
      val z = doAThirdStuff(y.get)
      if (z.isDefined) {
        val result = z.get
        Some(result)
      }
      else None
    }
    else None
  }
  else None
}
uglyFunction(UserInput(90))