package roundA

import java.io.BufferedReader
import java.io.InputStream

class Solution {
    fun run(inputStream: InputStream) {
        val bufferedInput = inputStream.bufferedReader()
        val howManyProblems = bufferedInput.readLine().toInt()
        val problems = mutableListOf<Problem>()
        var caseNumber = 1
        repeat(howManyProblems){
            problems.add(Problem.createFromInputStream(caseNumber, bufferedInput))
            caseNumber++
        }

        problems.forEach { solve(it) }
    }

    fun solve(problem: Problem){
        var remainingBudget = problem.budget
        var housesBought = 0
        val housesSortedByPrice = problem.housePrices.sorted()
        housesSortedByPrice.forEach { housePrice ->
            if(housePrice <= remainingBudget){
                housesBought++
                remainingBudget -= housePrice
            }
        }
        println("Case #${problem.caseNumber}: $housesBought")
    }

}
data class Problem(val caseNumber: Int, val budget: Int, val housePrices: List<Int> ){
    companion object{
        fun createFromInputStream(caseNumber: Int, bufferedInput: BufferedReader): Problem{
            val firstLine = bufferedInput.readLine()
            val secondLine = bufferedInput.readLine()
            val budget = firstLine.split(" ")[1].toInt()
            val houses = secondLine.split(" ").map { it.toInt() }
            return Problem(caseNumber, budget, houses)
        }
    }
}

fun main(args: Array<String>) {
    val solution = Solution();
    //val input = readFileAsLinesUsingGetResourceAsStream("roundA.txt")
    val input = System.`in`
    solution.run(input)

}

fun readFileAsLinesUsingGetResourceAsStream(fileName: String) = Solution::class.java.getResourceAsStream(fileName)


