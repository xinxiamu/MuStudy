package ch01.ex1_ATasteOfKotlin

data class Person(val name: String,
                  val age: Int? = null)

data class User(val name: String, val sex: Boolean? = null)

fun main(args: Array<String>) {
    val persons = listOf(Person("Alice"),
            Person("Bob", age = 29))

    val oldest = persons.maxBy { it.age ?: 0 }
    println("The oldest is: $oldest")

    val user = User("mutou",true)
    println(user.name + "==" + user.sex)
}

// The oldest is: Person(name=Bob, age=29)
