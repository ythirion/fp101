import Domain_ws.*
import Domain_ws.Data.parks
import Domain_ws.Data.people

println("1. What are the first names of all people ?")
people.map { p -> p.firstName }

println("2. Who owns Cats ?")
// Create an extension on Person allowing you to filter efficiently
fun Person.hasPetType(type: PetType): Boolean = this.pets.map { it.type }.contains(type)
people.filter { p -> p.hasPetType(PetType.CAT) }

println("3. What are the names of Mary Smiths pets ?")
people.firstOrNull { "${it.firstName} ${it.lastName}" == "Mary Smith" }
    ?.pets
    ?.filter { it.type == PetType.CAT }
    ?.map { it.name }

println("4. Do all people have pets ?")
people.all { it.pets.isNotEmpty() }

println("5. How many people have a given type of pet ?")
fun countPeopleWithPet(type: PetType) = people.count { it.hasPetType(type) }
countPeopleWithPet(PetType.CAT)

println("6. How many pet types of all people ?")
// Create an extension on Person allowing you to get Pet Types owned by the person
fun Person.getPetTypes(): Set<PetType> = this.pets.map { it.type }.toSet()
people.flatMap { it.getPetTypes() }.toSet()

println("7. Who owns the youngest Pet ?")
val maxAge = 100
people.minByOrNull { person ->
    person.pets
        .map { it.age }
        .minByOrNull { it } ?: maxAge
}

println("8. What is the average Pet age ?")
people.flatMap { it.pets }
    .map { it.age }
    .average()

println("9. What are all the pets name sorted alphabetically ?")
people.flatMap { it.pets }
    .map { it.name }
    .sorted()
    .joinToString(",")

println("10. What are the parks in which each person can walk with all their pets ?")
// For each person described as "firstName lastName" returns the list of names possible parks to go for a walk
fun List<Park>.filterFor(person: Person): List<Park> = this.filter { it.authorizedPets.containsAll(person.getPetTypes()) }
people.groupBy { "${it.firstName} ${it.lastName}" }
    .mapValues { t ->
        t.value.map { person ->
            parks.filterFor(person)
                .map { park -> park.name }
        }
    }

println("11. Function composition - findPersonPets")
// Create a function findPersonPets taking 2 function args and returning a composed function of :
// - a searchPerson function taking a String as arg and returning a Person?
// - a petMapper function taking a Pet as arg and returning a String
fun findPersonByFullName(fullName: String): Person? =
    people.firstOrNull { "${it.firstName} ${it.lastName}" == fullName }

fun findPersonPets(
    searchPerson: (String) -> Person?,
    petMapper: (Pet) -> String
): (String) -> List<String> =
    { fullName -> searchPerson(fullName)?.pets?.map { petMapper(it) } ?: emptyList() }

val composedFind = findPersonPets({ findPersonByFullName(it) }, { pet -> pet.name })
composedFind("Mary Smith")