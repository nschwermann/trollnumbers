package trollnumbers

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TrollNumberUtilsTest : StringSpec({

    "Creating troll numbers from integers should generate correct troll numbers" {
        val three = 3.toTrollNumber()
        val five = 5.toTrollNumber()
        val eleven = 11.toTrollNumber()

        three.toString() shouldBe "three"
        five.toString() shouldBe "many-one"
        eleven.toString() shouldBe "many-many-three"
    }

    "Adding two troll numbers should generate correct complex troll numbers" {
        val two = one + one
        val five = many + one
        val fifteen = many-many-many + three
        val ten = many-one + many-one

        two.value shouldBe 2
        five.value shouldBe 5
        fifteen.value shouldBe 15
        ten.value shouldBe 10

        two.toString() shouldBe "two"
        five.toString() shouldBe "many-one"
        fifteen.toString() shouldBe "many-many-many-three"
        ten.toString() shouldBe "many-many-two"
    }
})