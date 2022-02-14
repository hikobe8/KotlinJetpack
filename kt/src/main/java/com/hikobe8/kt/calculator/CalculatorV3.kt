package com.hikobe8.kt.calculator

import java.lang.StringBuilder
import kotlin.system.exitProcess

class CalculatorV3 {
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

    fun calculate(input: String): String? {
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

    private fun divString(left: String, right: String): String {
        val res = left.toInt() / right.toInt()
        return res.toString()
    }

    private fun multiString(left: String, right: String): String {
        val res = left.toInt() * right.toInt()
        return res.toString()
    }

    private fun minusString(left: String, right: String): String {
        val res = left.toInt() - right.toInt()
        return res.toString()
    }

    private fun addString(left: String, right: String): String? {
        val l = left.trim()
        val r = right.trim()
        var carry = 0
        var leftIndex = l.length - 1
        var rightIndex = r.length - 1
        val sb = StringBuilder()
        while (leftIndex > -1 || rightIndex > -1) {
            val n1 = if (leftIndex > -1) l[leftIndex].digitToInt() else 0
            val n2 = if (rightIndex > -1) r[rightIndex].digitToInt() else 0
            val sum = n1 + n2 + carry
            carry = sum / 10
            sb.append(sum % 10)
            leftIndex --
            rightIndex --
        }
        if (carry != 0) {
            sb.append(carry)
        }
        return sb.toString().reversed()
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

fun main(args: Array<String>) {
    CalculatorV3().start()
}

