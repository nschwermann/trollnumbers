package trollnumbers

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class ComplexTrollNumberTest : StringSpec({

    "String parsing should give correct value" {
        val table = table(
                headers("string", "expected value"),
                row("many-two", 6),
                row("many-many-three", 11),
                row("many", many.value),
                row("many-many-many-many", lots.value),
                row("lots", lots.value),
        )

        forAll(table) { string, expectedValue ->
            string.toTrollNumber().value shouldBe expectedValue
        }
    }

    "Parsing complex numbers should give correct toString() value" {
        val table = table(
                headers("complex number", "value"),
                row("many-one", "many-one"),
                row("many-many", "many-many"),
                row("many-many-three", "many-many-three"))

        forAll(table) { string, expectedToString ->
            string.toTrollNumber().toString() shouldBe expectedToString
        }
    }

    "Creating complex troll numbers should work properly" {
        val five = many-one
        val fourteen = many-many-many-two

        five.toString() shouldBe "many-one"
        fourteen.toString() shouldBe "many-many-many-two"

        five.value shouldBe 5
        fourteen.value shouldBe 14
    }
})