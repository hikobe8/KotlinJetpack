import com.hikobe8.kt.word_frequency.TextProcessorV1
import com.hikobe8.kt.word_frequency.TextProcessorV2
import org.junit.Test
import kotlin.test.assertEquals

class TestTextProcessor {

    @Test
    fun testV1(){
        val textProcessorV1 = TextProcessorV1()
        val processText = textProcessorV1.processText("Kotlin is good!!!! I love Kotlin!")
        println(processText)
        assertEquals(2, processText[0].count)
    }

    @Test
    fun testV2(){
        val textProcessorV2 = TextProcessorV2()
        val processText = textProcessorV2.processText("Kotlin is good!!!! I love Kotlin!")
        println(processText)
        assertEquals(2, processText[0].count)
    }

}