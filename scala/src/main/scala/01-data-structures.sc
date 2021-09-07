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
people.map(p => p.firstName)

println("2. Who owns Cats ?")
// Create an extension on Person allowing you to filter efficiently
extension (p: Person)
  def hasPetType(`type`: PetType): Boolean = p.pets.map(_.`type`).contains(`type`)

people.filter(p => p.hasPetType(PetType.CAT))

println("3. What are the names of Mary Smiths cats ?")
people.find(p => s"${p.firstName} ${p.lastName}" == "Mary Smith") match {
  case Some(marySmith) => marySmith.pets.filter(_.`type` == PetType.CAT).map(_.name)
  case None => println("Mary smith not found")
}

println("4. Do all people have pets ?")
people.forall(!_.pets.isEmpty)

println("5. How many people have a given type of pet ?")
def countPeopleWithPet(`type`: PetType) = people.count(_.hasPetType(`type`))
countPeopleWithPet(PetType.CAT)

println("6. How many pet types of all people ?")
// Create an extension on Person allowing you to get Pet Types owned by the person
extension (p: Person)
  def getPetTypes(): Set[PetType] = p.pets.map(_.`type`).toSet

people.flatMap(_.getPetTypes())
  .toSet
  .size

println("7. Who owns the youngest Pet ?")
val maxAge = 100
people.minBy(person =>
  person.pets
    .minByOption(_.age)
    .map(_.age)
    .getOrElse(maxAge)
)

println("8. What is the average Pet age ?")

// You can define an implicit class to have access to an average function
implicit class ImplSeqInt(values: Seq[Int]) {
  def average = values.map(_.toDouble).sum / values.length
}

people.flatMap(_.pets)
  .map(_.age)
  .average

println("9. What are all the pets name sorted alphabetically ?")
people.flatMap(_.pets)
  .map(_.name)
  .sorted
  .mkString(",")

println("10. What are the parks in which each person can walk with all their pets ?")
// For each person described as "firstName lastName" returns the list of names possible parks to go for a walk
extension (parks: Seq[Park])
  def filterFor(person: Person): Seq[Park] = {
    parks.filter(park => person
      .getPetTypes()
      .forall(t => park.authorizedPets.contains(t)))
  }

people.groupMap(p => s"${p.firstName} ${p.lastName}")(p => parks.filterFor(p).map(_.name))

println("11. Function composition - findPersonPets")
// Create a function findPersonPets taking 2 function args and returning a composed function of :
// - a searchPerson function taking a String as arg and returning a Person?
// - a petMapper function taking a Pet as arg and returning a String
def findPersonByFullName(fullName: String): Option[Person] = people.find(p => s"${p.firstName} ${p.lastName}" == fullName)

def findPersonPets(searchPerson: (String) => Option[Person],
                   petMapper: (Pet) => String): (String) => List[String] = {
  fullName =>
    searchPerson(fullName)
      .map(person => person.pets.filter(_.`type` == PetType.CAT).map(petMapper))
      .getOrElse(Nil)
}

val composedFind = findPersonPets({findPersonByFullName(_)}, { p => p.name })
composedFind("Mary Smith")