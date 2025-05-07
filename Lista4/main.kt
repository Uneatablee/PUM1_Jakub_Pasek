import kotlin.random.*
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

fun getMonthName(monthNumber: Int): String {
    return Month.of(monthNumber).getDisplayName(TextStyle.FULL, Locale.ENGLISH)
}

enum class CostType(val costType: String) {
    REFUELING("Tankowanie"),
    SERVICE("Serwis"),
    PARKING("Parking"),
    INSURANCE("Ubezpieczenie"),
    TICKET("Mandat")
}

data class Cost (
    val type: CostType,
    val date: LocalDate,
    val amount: Int
)

object DataProvider {
    public fun generalCosts(size: Int) = List(size) {
        Cost(
            CostType.values()[Random.nextInt(CostType.values().size)],
            LocalDate.of(
                2025, 
                Random.nextInt(1,13), 
                Random.nextInt(1,28)),
            Random.nextInt(5000)
        )
    }
}

//Zadanie 1
fun groupedCostMap(costs : List<Cost>) : Map<Int, List<Cost>>
{
    return costs.groupBy{it.date.monthValue}.toSortedMap()
}

//Zadanie 2
fun allCosts(costs : List<Cost>) : Unit
{
    val mapped_by_month = costs.groupBy{it.date.monthValue}.toSortedMap()
    mapped_by_month.forEach{
        println(Month.of(it.key).getDisplayName(TextStyle.FULL, Locale.ENGLISH))
        val cost_list = it.value.sortedBy{cost -> cost.date.dayOfMonth}
        cost_list.forEach{
            println("${it.date.dayOfMonth} ${it.type} ${it.amount}")
        }
    }
}

data class Car (
    val name: String,
    val brand: String,
    val model: String,
    val yearOfProduction: Int,
    val costs: List<Cost>
)

val cars = listOf(
        Car("Domowy", "Skoda", "Fabia", 2002, DataProvider.generalCosts(100)),
        Car("Służbowy", "BMW", "Coupe", 2015, DataProvider.generalCosts(50)),
        Car("Kolekcjonerski", "Fiat", "125p", 1985, DataProvider.generalCosts(10))
    )

//Zadanie 3
fun getCarCosts(name: String): List<Pair<CostType, Int>> {

    val car = cars.find { it.name == name } ?: return emptyList()
    val groupedCosts = car.costs.groupBy { it.type }.mapValues { cost_sum -> cost_sum.value.sumOf { it.amount } }
    val allTypes = CostType.values().associateWith { groupedCosts[it] ?: 0 }

    return allTypes.toList().sortedByDescending { it.second }
}

fun printCarCosts(costSummary: List<Pair<CostType, Int>>)
{
    costSummary.forEach { (type, total) ->
        println("${type.name} ${total} zł")
    }
}

//Zadanie 4
fun getFullCosts(car_list: List<Car>) : Map<CostType, Int>
{
    val all_costs = cars.flatMap{it.costs}
    val grouped_costs = all_costs.groupBy { it.type }.mapValues { cost_sum -> cost_sum.value.sumOf { it.amount } }
    return CostType.values().associateWith { grouped_costs[it] ?: 0 }
}

fun printFullCosts(costSummary: Map<CostType, Int>)
{
    val sorted_costs = costSummary.entries.sortedByDescending{it.value}
    sorted_costs.forEach { (type, total) ->
        println("${type.name} ${total} zł")
    }
}

fun main()
{
    //Zadanie 1
    val months_map = groupedCostMap(DataProvider.generalCosts(5))
    println("Zadanie 1.\nGenerujac 5 elementow:")
    months_map.forEach{ 
        print(Month.of(it.key).getDisplayName(TextStyle.FULL, Locale.ENGLISH))
        print("=")
        println(it.value)
    }


    //Zadanie 2
    println("\nZadanie 2.")
    allCosts(DataProvider.generalCosts(5))

    //Zadanie 3
    println("\nZadanie 3.")
    printCarCosts(getCarCosts("Domowy"))

    //Zadanie 4
    println("\nZadanie 4")
    printFullCosts(getFullCosts(cars))
}