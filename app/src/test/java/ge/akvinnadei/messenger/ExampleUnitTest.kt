package ge.akvinnadei.messenger

import ge.akvinnadei.messenger.model.Message
import ge.akvinnadei.messenger.model.User
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun checkUser(){
        var niko = User("Niko", 123, "TahmMain")
        println(niko.toString())

        var ika = User("a", 1, "a")
        ika.setUserName("ika")
        ika.setProfession("seraphine otp")

        ika.addContact(niko)
        niko.printContacts()
        ika.printContacts()
//        test123

        var message1 = Message("zd niko", ika, niko)
        var t = LocalDateTime.of(2021, 8, 9, 18, 40)
        println(message1.dateDisplayFormat(t))

    }

}