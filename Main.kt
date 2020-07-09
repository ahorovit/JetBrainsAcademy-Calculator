package calculator

fun main() {

    val calculator = Calculator()

    loop@ do {
        val rawInput = readLine()!!

        if (rawInput.contains("=")) {
            calculator.saveVariable(rawInput)
            continue
        }

        val input = rawInput.split("\\s+".toRegex())

        when (input[0]) {
            "" -> continue@loop
            "/exit" -> continue@loop
            "/help" -> println("The program calculates the sum of numbers")
            else -> {
                if (input[0][0] == '/'){
                    println("Unknown command")
                    continue@loop
                }

                try {
                    parseExpression(input)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    } while (input[0] != "/exit")

    println("Bye!")
}

