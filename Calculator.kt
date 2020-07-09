package calculator

import kotlin.math.exp

class Calculator {
    val variables: MutableMap<String, Int> = mutableMapOf()

    val numeric = "(-|\\+)?\\d+".toRegex()
    val latin = "[a-z]+".toRegex(RegexOption.IGNORE_CASE)

    fun saveVariable(expression: String) {
        val elements = expression.split("\\s*=\\s*".toRegex())

        if (
                elements.size == 2
                && latin.matches(elements[0])
                && (numeric.matches(elements[1]) || variables.containsKey(elements[1]))
        ) {
            if (variables.containsKey(elements[1])) {
                variables[elements[0]] = variables[elements[1]]!!
            } else if (numeric.matches(elements[1])) {
                variables[elements[0]] = elements[1].toInt()
            }

        } else {
            println("Invalid expression")
        }
    }

    fun parseExpression(input: List<String>) {
        var result = 0
        var currentOperator = "+"

        for (element in input) {
            when {
                "-+".toRegex().matches(element) -> {
                    currentOperator = if (element.length % 2 == 0) "+" else "-"
                }
                "\\++".toRegex().matches(element) -> currentOperator = "+"
                numeric.matches(element) -> {
                    if (currentOperator == "+") {
                        result += element.toInt()
                    } else {
                        result -= element.toInt()
                    }
                }
                variables.containsKey(element) -> {
                    if (currentOperator == "+") {
                        result += variables[element]!!
                    } else {
                        result -= variables[element]!!
                    }
                }
                latin.matches(element) -> throw Exception("Unknown variable")
                else -> throw Exception("Invalid expression")
            }
        }

        if (!numeric.matches(input.last()) && !variables.containsKey(input.last())) {
            throw Exception("Invalid expression")
        }

        println(result)
    }
}