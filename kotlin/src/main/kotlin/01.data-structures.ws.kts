import Data.parks
import Data.people
import Person
import PetType
import Park
import Pet

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

println("9. What are all the pets name sorted alphabetically ?")

println("10. What are the parks in which each person can walk with all their pets ?")
// For each person described as "firstName lastName" returns the list of names possible parks to go for a walk

println("11. Function composition - findPersonPets")
// Create a function findPersonPets taking 2 function args and returning a composed function of :
// - a searchPerson function taking a String as arg and returning a Person?
// - a petMapper function taking a Pet as arg and returning a String
