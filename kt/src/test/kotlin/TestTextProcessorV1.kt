import com.hikobe8.kt.word_frequency.TextProcessorV1
import org.junit.Test
import kotlin.test.assertEquals

class TestTextProcessorV1 {

    @Test
    fun testV1(){
        val textProcessorV1 = TextProcessorV1()
        val processText = textProcessorV1.processText("Kotlin is good!!!! I love Kotlin!")
        println(processText)
        assertEquals(2, processText[0].count)
    }

}