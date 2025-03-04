//Jakub Pasek PUM1 Lista 1
//Zadanie 1
fun num(number : Int) : Unit?
{
    var output = ""
    if(number % 3 == 0)
    {
        output = output + "trzy"
    }
    if(number % 5 == 0)
    {
        output = output + "piec"
    }
    if(number % 7 == 0)
    {
        output = output + "siedem"
    }
    if(number % 11 == 0)
    {
        output = output + "jedenascie"
    }

    if(output == "")
    {
        println(number)
        return null
    }

    println(output)
    return null
}

//zadanie 2
fun isPalindrome(word : String) : Boolean?
{   
    val word_length = word.length - 1
    var start_of_word = 0
    var end_of_word = word_length
    val for_range = word_length / 2 
    for(i in start_of_word..for_range)
    {
        if(word[start_of_word] != word[end_of_word])
        {
            println("This word IS NOT a plaindrome!")
            return false
        }

        start_of_word++
        end_of_word--
    }

    println("This word IS a plaindrome!")
    return true
}

//Zadanie 3
fun printPascal(height : Int) : Unit?
{   
    var pascal_array : Array<Array<Int>> = emptyArray()
    for(i in 0..height-1)
    {
        var temp_arr : Array<Int> = emptyArray()
        for(j in 1..i+1)
        {
            if(j == 1 || j == i+1)
            {
                temp_arr += 1
            }
            else
            {   
                val value = pascal_array[i-1][j-2] + pascal_array[i-1][j-1]
                temp_arr += value
            }
        }
        pascal_array += temp_arr
    }

    //printing out
    println()
    var spaces = (" ").repeat(height + 1)
    for(i in 0 until pascal_array.size)
    {
        print(spaces)
        spaces = spaces.dropLast(1)
        for(j in 0 until pascal_array[i].size)
        {
            print(pascal_array[i][j])
            print(" ")
        }
        println()
    }
    return null
}

//zadanie 4
fun isPerfect(number : Int) : String?
{
    //all dividors
    val dividors_list = mutableListOf<Int>()
    val size = Math.floor(Math.sqrt(number.toDouble())).toInt()
    for(i in 1..size)
    {
        if(number % i == 0)
        {
            dividors_list.add(i)
            if(number / i != i && i != 1)
            {
                dividors_list.add(number / i)
            }
        }
    }
    var liczba_alikwotowa = dividors_list.sum()
    return when
    {
        liczba_alikwotowa == number -> "perfect"
        liczba_alikwotowa > number -> "abundant"
        liczba_alikwotowa < number -> "deficient"
        else -> ""
    }
}

//Zadanie 5
fun checkArmstrong(number : Int) : Boolean?
{
    var calculated_result = 0;
    var number_string = number.toString()
    //val size = number_string.size
    for(i in 0..number_string.length - 1)
    {
        calculated_result += Math.pow((number_string[i].digitToInt()).toDouble(), number_string.length.toDouble()).toInt()
    }

    if(calculated_result == number)
    {
        return true
    }

    return false
}

fun main()
{
    //Zadanie 1
    println("\nZadanie 1")
    num(1155)
    num(3)
    num(15)
    num(21)
    num(77)

    //Zadanie 2
    println("\nZadanie 2")
    isPalindrome("kobylamamalybok")
    isPalindrome("Jakub")
    isPalindrome("kajak")
    isPalindrome("eye")
    isPalindrome("lalalll")

    //Zadanie 3
    println("\nZadanie 3")
    printPascal(4)
    println("--------")
    printPascal(7)
    println("--------")
    printPascal(12)

    //Zadanie 4
    println("\nZadanie 4")
    println("Dla liczby 28: ${isPerfect(28)}")
    println("Dla liczby 12: ${isPerfect(12)}")
    println("Dla liczby 8: ${isPerfect(8)}")
    println("Dla liczby 496: ${isPerfect(496)}")
    println("Dla liczby 23: ${isPerfect(23)}")
    println("Dla liczby 36: ${isPerfect(36)}")

    //zadanie 5
    println("\nZadanie 5")
    println("Dla liczby 153: ${checkArmstrong(153)}")
    println("Dla liczby 154: ${checkArmstrong(154)}")
    println("Dla liczby 154: ${checkArmstrong(407)}")
    println("Dla liczby 154: ${checkArmstrong(54748)}")
    println("Dla liczby 154: ${checkArmstrong(548834)}")
    println("Dla liczby 154: ${checkArmstrong(410)}")
}
