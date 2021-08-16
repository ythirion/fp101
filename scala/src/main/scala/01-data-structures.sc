object PetType extends Enumeration {
  type PetType = Value
  val CAT, DOG, HAMSTER, TURTLE, BIRD, SNAKE = Value
}

import PetType.PetType

case class Pet(`type`: PetType, name: String, age: Int)

case class Person(firstName: String, lastName: String, pets: List[Pet] = Nil)

case class Park(name: String, authorizedPets: Set[PetType])

object Data {
  val people = List(
    Person("Mary", "Smith", List(Pet(PetType.CAT, "Tabby", 2))),
    Person("Bob", "Smith", List(
      Pet(PetType.CAT, "Dolly", 3),
      Pet(PetType.DOG, "Spot", 2)
    )),
    Person("Ted", "Smith", List(Pet(PetType.DOG, "Spike", 4))),
    Person("Jake", "Snake", List(Pet(PetType.SNAKE, "Serpy", 1))),
    Person("Barry", "Bird", List(Pet(PetType.BIRD, "Tweety", 2))),
    Person("Terry", "Turtle", List(Pet(PetType.TURTLE, "Speedy", 1))),
    Person("Harry", "Hamster", List(
      Pet(PetType.HAMSTER, "Fuzzy", 1),
      Pet(PetType.HAMSTER, "Wuzzy", 1)
    )),
    Person("John", "Doe")
  )

  val parks = List(
    Park("Jurassic", Set(PetType.BIRD, PetType.SNAKE, PetType.TURTLE)),
    Park("Central", Set(PetType.BIRD, PetType.CAT, PetType.DOG)),
    Park("Hippy", Set(PetType.BIRD, PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.SNAKE))
  )
}

import Data.{parks, people}

println("1. What are the first names of all people ?")

println("2. Who owns Cats ?")
// Create an extension on Person allowing you to filter efficiently

println("3. What are the names of Mary Smiths cats ?")

println("4. Do all people have pets ?")

println("5. How many people have a given type of pet ?")

println("6. How many pet types of all people ?")
// Create an extension on Person allowing you to get Pet Types owned by the person

println("7. Who owns the youngest Pet ?")

println("8. What is the average Pet age ?")

// You can define an implicit class to have access to an average function
implicit class ImplSeqInt(values: Seq[Int]) {
  def average = values.map(_.toDouble).sum / values.length
}

println("9. What are all the pets name sorted alphabetically ?")

println("10. What are the parks in which each person can walk with all their pets ?")
// For each person described as "firstName lastName" returns the list of names possible parks to go for a walk

println("11. Function composition - findPersonPets")
// Create a function findPersonPets taking 2 function args and returning a composed function of :
// - a searchPerson function taking a String as arg and returning a Person?
// - a petMapper function taking a Pet as arg and returning a String
