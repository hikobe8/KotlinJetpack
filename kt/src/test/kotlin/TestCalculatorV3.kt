import com.hikobe8.kt.calculator.CalculatorV3
import org.junit.Test
import kotlin.test.assertEquals

class TestCalculatorV3 {

    @Test
    fun testAdd(){
        val calculator = CalculatorV3()
        val res = calculator.calculate("1 +2")
        assertEquals("3", res)
    }

    @Test
    fun testAddEdge(){
        val calculator = CalculatorV3()
        val res = calculator.calculate("1+2147483647")
        assertEquals("2147483648", res)
    }

}