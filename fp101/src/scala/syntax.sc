package samples

// String interpolation
val language = "Scala"
val sheetName = s"Let's learn $language basics"

// VARIABLES
var x = 5
x = 6
// Constants
val x = 5
// Explicit type
val x: Double = 5

// FUNCTIONS
// Define function : need types for every arg
def square(x: Int) = {
  x * x
}
// Use default parameter
def squareWithDefaultParameter(x: Int = 4) = {
  x * x
}
// Don't compile : def square(x) = { x * x }

// Create type alias
type R = Double

// Call by value
def f(x: R) = x
// Call by name -> lazy parameter
def f(x: => R) = x

// Anonymous functions
(x: R) => x * x
(1 to 5).map(x => x)

// Underscore is positionally matched arg
(1 to 5).map(_ * 2)
(1 to 5).reduceLeft(_ + _)
(1 to 5).map(x => x * x)

// Block style returns last expression
(1 to 5).map { x =>
  val y = x * 2
  println(y)
  y
}

// Pipeline style (or parens too)
(1 to 5) filter {
  _ % 2 == 0
} map {
  _ * 2
}

// Function composition
def compose(g: R => R, h: R => R) = (x: R) => g(h(x))
def minus1times2 = compose(_ * 2, _ - 1)
println(minus1times2(4))

// Currying
def sum(x: Int, y: Int): Int = x + y
def curriedSum(x: Int)(y: Int): Int = x + y
def curriedSum2: Int => Int => Int = (sum _).curried
curriedSum(1)(2)
curriedSum2(1)(2)

// Generic Type
def mapmake[T](g: T => T)(seq: List[T]) = seq.map(g)
mapmake[Int](x => x / 2)((1 to 5).toList)

// Varargs
def sum(args: Int*) = args.reduceLeft(_ + _)
sum(1, 2, 3, 4)

// DATA STRUCTURES
// Tuple literal -> Tuple3
(1, 2, 3)
// Tuple destructuring
var (x, y, z) = (1, 2, 3)

// Immutable list
var xs = List(1, 2, 3)
var ys = List(4, 5, 6)
// Indexing
xs(2)
// Cons -> short for construct a new List object
// Only at the beginning of a list
1 :: List(2, 3)
// Range
(1 to 5).equals(1 until 6)

// CONTROL CONSTRUCTS
def happy = println(";-)")
def sad = println(":-(")

// Conditional
if (true) happy else sad

// While
while (x < 5) {
  println(x)
  x += 1
}

// For loop
for (x <- xs if x % 2 == 0)
  yield x * 10
// Same as
xs.filter(_ % 2 == 0).map(_ * 10)

// PATTERN MATCHING
// Use case in function args for pattern matching
(xs zip ys) map {
  case (x, y) => x * y
}

// Regular matching
val v42 = 42
v42 match {
  case 42 => println("42")
  // Other cases
  case _ => println("Not 42")
}

// OBJECT ORIENTATION
// x is only available in class body.
class C(x: R)

// Automatic public member defined
class C(val x: R)

val c1 = new C(9)
c1.x

// Traits -> interfaces
trait APerson {
  def name: String
}

// Case class -> records
// all constructor parameters are public and immutable
case class Student(name: String, year: Int) extends APerson

case class Teacher(name: String, specialty: String) extends APerson

// Pattern matching on case classes
def personToString(p: Person): String = p match {
  case Student(name, year) => s"$name is a student in Year $year."
  case Teacher(name, whatTheyTeach) => s"$name teaches $whatTheyTeach."
}
// Case equality
val bart1 = Student("Bart Simpson", 2021)
val bart2 = Student("Bart Simpson", 2021)
bart1.equals(bart2)
// case class has an automatically-generated copy method
// when you need to perform the process of a) cloning an object and b) updating one or more of the fields during the cloning
val homer = bart1.copy(name = "Homer Simpson")

// OPTIONS
val option: Option[Int] = Some(42)
None
Option(null) == None

// Map it
option.map(f(_))
// equivalent to
option match {
  case Some(x) => Some(f(x))
  case None => None
}