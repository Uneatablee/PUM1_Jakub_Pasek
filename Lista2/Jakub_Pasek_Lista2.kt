//Jakub Pasek PUM1 Lista 2
//347426

//Zadanie 2
val <T> List<T>.tail: List<T>
    get()
    {
        return this.subList(1, this.size)
    }

val <T> List<T>.head: T
    get()
    {
        return this.subList(0,1)[0]
    }

//Zadanie 3
fun <A> isSorted(list: List<A>, order: (A, A) -> Boolean): Boolean
{
    for(i in 0..list.size - 2)
    {
        if(!order(list[i], list[i + 1]))
        {
            return false
        }
    }

    return true
}

//Zadanie 4
fun <A> tailToHead(lst: List<A>): List<A>
{
    if(lst.isEmpty())
    {
        throw IllegalStateException("Empty list")
    }
    val last_element_list = lst.subList(lst.size - 1, lst.size)
    var new_list = last_element_list + lst.dropLast(1)
    return new_list
}

//Zadanie 5
fun <A> setHead(lst: List<A>, item: A): List<A>
{
    if(lst.isEmpty())
    {
        throw IllegalStateException("Empty list")
    }

    var new_list = listOf(item) + lst.drop(1)
    return new_list
}

//Zadanie 6
fun check(N: Int, list: List<Int>): Int
{
    if(N > list.size)
    {
        return -1
    }

    for(i in N until list.size)
    {
        var current_target_element = list[i]
        var previous_values = list.subList(i-N, i)

        var unique_values = previous_values.toSet()
        var found = false;
        for(j in unique_values)
        {
            var first_number = current_target_element - j
            if(first_number in unique_values && first_number != j)
            {
                found = false
                break
            }
            else
            { 
                found = true
            }
        }

        if(found)
        {
            return current_target_element
        }
    }

    return -1
}

fun main()
{
    //zadanie1
    println("Zadanie 1")
    val string_multiply = {string_arg : String, number_of_copies : Int -> string_arg.repeat(number_of_copies)}
    println(string_multiply("Hello", 4))

    //zadanie2
    println("\nZadanie 2")
    println("Bez pierwszego:")
    val string_list = listOf("Banana", "Strawberry", "Apple", "Cherry", "Orange")
    println(string_list.tail)

    println("\nPierwszy element:")
    println(string_list.head)

    //zadanie 3
    println("\nZadanie 3")
    println(isSorted(listOf(1, 2, 3, 4), {i: Int, j: Int -> i < j}))
    println(isSorted(listOf(1, 1, 1, 1), {i: Int, j: Int -> i==j}))
    println(isSorted(listOf("ahyyhh", "bkjn", "cnn", "duu"), {i: String, j: String -> i.first() < j.first()}))
    println(isSorted(listOf(1,4,2,6), {i: Int, j: Int -> i < j}))

    //Zadanie 4
    println("\nZadanie 4")
    println(tailToHead(listOf(1,2,3,4,5)))

    //Zadanie 5
    println("\nZadanie 5")
    println(setHead(listOf(1, 2, 3), 5))

    //Zadanie 6
    println("Zadanie 6")
    println(check(2, listOf(1, 2, 3, 4, 5, 6)))
    println(check(5, listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)))
}