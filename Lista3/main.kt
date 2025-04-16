//Jakub Pasek PUM1 Lista 2
//347426

//Zadanie 1
fun findDuplicates(input_list : List<Int>) : List<Int>
{
    val duplicates = mutableListOf<Int>()
    val set = mutableSetOf<Int>()

    for(i in 0 until input_list.size)
    {
        if(input_list[i] in set && input_list[i] !in duplicates)
        {
            duplicates.add(input_list[i])
        }
        else
        {
            set.add(input_list[i])
        }
    }

    return duplicates.sorted()
}

//Zadanie 2
fun addToBoolean() : Map<Int, Boolean>
{
    val map = mutableMapOf<Int, Boolean>()
    for(i in 1 until 21)
    {
        if(i % 2 == 0)
        {
            map.put(i, true)
        }
        else
        {
            map.put(i, false)
        }
    }

    return map;
}

//Zadanie 3
fun suma(a : List<Int>) : Int
{
    val new_list = a.filter { it > 0 }
    return new_list.sum()
}

//Zadanie 4
fun countElements(input_list : List<List<String>>) : Map<String, Int>
{
    var input_list = input_list.flatten()
    var map = input_list.groupingBy{it}.eachCount()
    return map
}

//Zadanie 5
fun evenPositiveSquare(a : List<Int>) : List<Int>
{
    var new_list = a.filterIndexed{index, number -> (index + 1) % 2 == 0 && number > 0}
    new_list = new_list.map{it * it}
    return new_list
}

//Zadanie 6
fun perm(list : List<Int>) : List<List<Int>>
{
    if(list.size == 1)
    {
        return listOf(list)
    }

    val result = mutableListOf<List<Int>>()
    for(i in list.indices)
    {
        val current = list[i]
        val remaining = list.subList(0, i) + list.subList(i + 1, list.size)
        for(perm in perm(remaining))
        {
            result.add(listOf(current) + perm)
        }
    }
    return result
}

//Zadanie 7
fun srt(list : List<String>) : List<Pair<String, List<String>>>
{
    var even_words = list.filter({it.length % 2 == 0})
    var letters = mutableSetOf<String>()

    for(i in 0 until even_words.size)
    {
        letters.add(even_words[i].substring(0, 1))
    }
    println(letters)
    
    var final_list = mutableListOf<Pair<String, List<String>>>()
    for(i in letters)
    {
        var temp = even_words.filter({it.startsWith(i)})
        final_list.add(Pair(i, temp))
    }

    final_list.sortBy { it.first }

    return final_list
}

//Zadanie 8
fun convert(word : String) : String
{
    var letter_map = mapOf(
        'a' to "2",
        'b' to "22",
        'c' to "222",
        'd' to "3",
        'e' to "33",
        'f' to "333",
        'g' to "4",
        'h' to "44",
        'i' to "444",
        'j' to "5",
        'k' to "55",
        'l' to "555",
        'm' to "6",
        'n' to "66",
        'o' to "666",
        'p' to "7",
        'q' to "77",
        'r' to "777",
        's' to "7777",
        't' to "8",
        'u' to "88",
        'v' to "888",
        'w' to "9",
        'x' to "99",
        'y' to "999",
        'z' to "9999"

        )

        var result = ""
        for(letter in word)
        {
            result += letter_map[letter] ?: ""
        }

        return result
}

data class StudentScore(val name: String, val subject: String, val score: Int)

//Zadanie 9
fun analyzeResults(results : List<StudentScore>) : Triple<Map<String, List<StudentScore>>, List<StudentScore>, List<String>>
{
    val map = mutableMapOf<String, List<StudentScore>>()
    var not_passed = mutableListOf<StudentScore>()
    var set = mutableSetOf<String>()

    for(single_score in results)
    {
        set.add(single_score.subject)

        if(single_score.score >= 50)
        {
            map[single_score.subject] = map[single_score.subject].orEmpty() + single_score
        }
        else
        {
            not_passed.add(single_score)
        }
    }

    for(elem in not_passed)
    {
        set.remove(elem.subject)
    }

    return Triple(map.toMap(), not_passed.toList(), set.toList())
}

//Zadanie 10
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(
        x + other.x,
        y + other.y
    )

    operator fun minus(other: Point) = Point(
        x - other.x,
        y - other.y
    )

    operator fun times(other: Point) = Point(
        x * other.x,
        y * other.y
    )

    operator fun not() = Point(
        -x,
        -y
    )

    operator fun inc() = Point(
        x + 1,
        y + 1
    )

    operator fun dec() = Point(
        x - 1,
        y - 1
    )

    // operator fun plusAssign(other: Point) {
    //     x += other.x,
    //     y += other.y
    // }
}




fun main()
{
    println("\nZadanie 1")
    val lst = listOf(0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3, 3, 3)
    println(findDuplicates(lst))

    println("\nZadanie 2")
    println(addToBoolean())

    println("\nZadanie 3")
    println(suma(listOf( 1, -4, 12, 0, -3, 29, -150)))

    println("\nZadanie 4")
    println(countElements(listOf(listOf("a", "b", "c"), listOf("c", "d", "f"), listOf("d", "f", "g"))))

    println("\nZadanie 5")
    println(evenPositiveSquare(listOf(1, 2, 3, 5, -6, -1, -1, 2, 3)))

    println("\nZadanie 6")
    println(perm(listOf(1, 2, 3)))

    println("\nZadanie 7")
    println(srt(listOf(
        "cherry", 
        "blueberry", 
        "citrus", 
        "apple", 
        "apricot", 
        "banana", 
        "coconut")
    ))
    println(srt(listOf(
        "mangoo",
        "kiwi",
        "kalafior",
        "mandol",
        "mandarynka",
        "apples",
        "banana",
        "ananas",
        "arbuzy"
    )))

    println("\nZadanie 8")
    println(convert("franek"))
    
    println("\nZadanie 9")
    val students = listOf(
    StudentScore("Alice", "Math", 78),
    StudentScore("Bob", "Math", 45),
    StudentScore("Charlie", "Physics", 92),
    StudentScore("Dave", "Physics", 55),
    StudentScore("Eve", "Physics", 40),
    StudentScore("Frank", "CS", 60),
    StudentScore("Grace", "CS", 80)
    )

    val (passed, not_passed, subjects) = analyzeResults(students)
    println("Uczniowe, ktorzy zdali: ")
    for(i in passed)
    {
        println(i)
    }

    println("Uczniowe, ktorzy nie zdali: ")
    for(i in not_passed)
    {
        println(i)
    }

    println("Przedmioty, z ktorych wszyscy zdali: ")
    println(subjects)

    println("\nZadanie 10")
    var p1 = Point(1, 1)
    var p2 = Point(2, 2)

    println(p1 + p2)
    println(p1 - p2)
    println(p1 * p2)
    println(p1++)
    println(p1--)
    println(!p1)
    // println(p1 += p2)

}