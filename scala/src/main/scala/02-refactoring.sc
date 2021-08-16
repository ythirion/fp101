println("1. Refactor the reverse function by not using var anymore")
def reverse(str: String): String = {
  if (str.isEmpty) return ""
  var reversed = ""
  for (i <- str.length - 1 to 0 by -1)
    reversed += str(i)
  reversed
}

reverse("t'nod ohw esoht dna yranib dnatsrednu ohw esoht : dlrow eht ni elpoep fo sepyt 01 era erehT")
def reverseYield(str: String): String = {
  (for (i <- str.length - 1 to 0 by -1)
    yield str(i)).mkString
}

reverseYield("t'nod ohw esoht dna yranib dnatsrednu ohw esoht : dlrow eht ni elpoep fo sepyt 01 era erehT")

def reverseRec(str: String): String = {
  if (str.isEmpty) ""
  else reverseRec(str.tail) + str.head
}

reverseRec("t'nod ohw esoht dna yranib dnatsrednu ohw esoht : dlrow eht ni elpoep fo sepyt 01 era erehT")

println("2. Refactor the printlist function to parameterize hardcoded stuffs")
def actOnList(list: List[Int], action: (Int) => Unit): Unit = list.foreach(action)
def printList(list: List[Int]): Unit = list.foreach(i => println(s"the number is $i"))
printList(List.range(1, 10))

println("3. Refactor those 2 functions by identifying common stuff")
def transform(initialValue: Int, n: Int, action: (Int, Int) => Int): Int =
  (1 to n).fold(initialValue)(action)

def product(n: Int) = transform(1, n, (product, i) => product * i)
def sum(n: Int) = transform(0, n, (sum, i) => sum + i)

product(10)
sum(10)

println("4. Refactor this function to make it more explicit")
println("Constrain the input : make it impossible to represent an invalid state")

case class NonZeroInteger(private val value: Int) {
  def toInt() = value
}

extension (i: Int)
  def toNonZeroInteger(): NonZeroInteger = {
    if (i == 0) throw new IllegalArgumentException("0 is not authorized")
    new NonZeroInteger(i)
  }

def divide(top: Int, bottom: NonZeroInteger): Int = top / bottom.toInt()
divide(89, 2.toNonZeroInteger())

println("Read this article : https://enterprisecraftsmanship.com/posts/specification-pattern-always-valid-domain-model/\n")

println("Extend the output by using Option")
def divideWithExtendedOutput(top: Int, bottom: Int): Option[Int] = {
  bottom match {
    case 0 => None
    case _ => Some(top / bottom)
  }
}
divideWithExtendedOutput(89, 2)

println("5. Refactor the ugly function by chaining callbacks with continuations")

case class UserInput(key: Int)

def doSomething(input: UserInput): Option[UserInput] = Some(input.copy(input.key / 2))
def doSomethingElse(input: UserInput): Option[UserInput] = Some(input.copy(input.key / 2))
def doAThirdStuff(input: UserInput): Option[UserInput] = Some(input.copy(input.key / 2))

def uglyRefactored(input: UserInput): Option[UserInput] = {
  doSomething(input)
    .flatMap(doSomethingElse)
    .flatMap(doAThirdStuff)
}
uglyRefactored(UserInput(90))