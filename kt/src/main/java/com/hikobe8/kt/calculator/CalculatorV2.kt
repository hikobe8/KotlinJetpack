package com.hikobe8.kt.calculator

import kotlin.system.exitProcess

class CalculatorV2 {
    fun start() {
        while (true) {
            println(HELP)
            val input = readLine() ?: continue
            val result = calculate(input)
            if (null == result) {
                println("输入格式不对")
                continue
            } else {
                println("$input = $result")
            }
        }
    }

    private fun calculate(input: String): String? {
        if (shouldExit(input)) exitProcess(0)
        val expression = parseExpression(input) ?: return null
        val left = expression.left
        val op = expression.operator
        val right = expression.right
        return when (op) {
            Operation.ADD -> addString(left, right)
            Operation.MINUS -> minusString(left, right)
            Operation.MULTI -> multiString(left, right)
            Operation.DIV -> divString(left, right)
        }
    }

    private fun divString(left: String, right: String): String? {
        val res = left.toInt() / right.toInt()
        return res.toString()
    }

    private fun multiString(left: String, right: String): String? {
        val res = left.toInt() * right.toInt()
        return res.toString()
    }

    private fun minusString(left: String, right: String): String? {
        val res = left.toInt() - right.toInt()
        return res.toString()
    }

    private fun addString(left: String, right: String): String? {
        val res = left.toInt() + right.toInt()
        return res.toString()
    }

    private fun parseExpression(input: String): Expression? {
        val operator = parseOperator(input) ?: return null
        val list = input.split(operator.value)
        if (list.size != 2)
            return null
        return Expression(left = list[0].trim(), operator = operator, right = list[1].trim())
    }

    private fun parseOperator(input: String): Operation? {
        Operation.values().forEach {
            if (input.contains(it.value)) {
                return it
            }
        }
        return null
    }

    private fun shouldExit(input: String): Boolean {
        return input == EXIT
    }

}

data class Expression(
    val left: String,
    val operator: Operation,
    val right: String
)

fun main(args: Array<String>) {
    CalculatorV2().start()
}

