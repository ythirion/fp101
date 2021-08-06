enum class PetType { CAT, DOG, HAMSTER, TURTLE, BIRD, SNAKE }
data class Pet(val type: PetType, val name: String, val age: Int)
data class Person(val firstName: String, val lastName: String, val pets: List<Pet> = emptyList())
data class Park(val name: String, val authorizedPets: Set<PetType> = emptySet())

object Data {
    val people = listOf(
        Person("Mary", "Smith", listOf(Pet(PetType.CAT, "Tabby", 2))),
        Person("Bob", "Smith", listOf(
            Pet(PetType.CAT, "Dolly", 3),
            Pet(PetType.DOG, "Spot", 2)
        )),
        Person("Ted", "Smith", listOf(Pet(PetType.DOG, "Spike", 4))),
        Person("Jake", "Snake", listOf(Pet(PetType.SNAKE, "Serpy", 1))),
        Person("Barry", "Bird", listOf(Pet(PetType.BIRD, "Tweety", 2))),
        Person("Terry", "Turtle", listOf(Pet(PetType.TURTLE, "Speedy", 1))),
        Person("Harry", "Hamster", listOf(
            Pet(PetType.HAMSTER, "Fuzzy", 1),
            Pet(PetType.HAMSTER, "Wuzzy", 1)
        )),
        Person("John", "Doe")
    )

    val parks = listOf(
        Park("Jurassic", setOf(PetType.BIRD, PetType.SNAKE, PetType.TURTLE)),
        Park("Central", setOf(PetType.BIRD, PetType.CAT, PetType.DOG)),
        Park("Hippy", setOf(PetType.BIRD, PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.SNAKE))
    )
}