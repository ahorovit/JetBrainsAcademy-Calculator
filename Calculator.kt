package calculator

import kotlin.math.exp

class Calculator {
    val variables: MutableMap<String,Int> = mutableMapOf()

    fun saveVariable(expression: String) {
        val elements = expression.split("\\s*=\\s*".toRegex())

        if(elements.size != 2) {
            throw new Exception("Invalid expression")
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
                "(-|\\+)?\\d+".toRegex().matches(element) -> {
                    if (currentOperator == "+") {
                        result += element.toInt()
                    } else {
                        result -= element.toInt()
                    }
                }
                else -> throw Exception("Invalid expression")
            }
        }

        if (!"(-|\\+)?\\d+".toRegex().matches(input.last())) {
            throw Exception("Invalid expression")
        }

        println(result)
    }
}