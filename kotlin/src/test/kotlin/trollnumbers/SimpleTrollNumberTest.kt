package trollnumbers

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class SimpleTrollNumberTest : StringSpec({

    "Constants should have correct values" {
        val table = table(
                headers("constant", "expected value"),
                row(one, 1),
                row(two, 2),
                row(three, 3),
                row(many, 4),
                row(lots, 16)
        )

        forAll<TrollNumber, Int>(table) { constant, expectedValue ->
            constant.value shouldBe expectedValue
        }
    }

    "Constants should have correct toString() value" {
        val table = table(
                headers("constant", "value"),
                row(one, "one"),
                row(two, "two"),
                row(three, "three"),
                row(many, "many"),
                row(lots, "lots"),
        )

        forAll(table) { constant, expectedToString ->
            constant.toString() shouldBe expectedToString
        }
    }

    "String parsing should give correct constant" {
        val table = table(
                headers("string", "constant"),
                row("one", one),
                row("two", two),
                row("three", three),
                row("many", many),
                row("lots", lots)
        )

        forAll(table) { string, expectedConstant ->
            string.toTrollNumber().value shouldBe expectedConstant.value
            string.toTrollNumber().toString() shouldBe expectedConstant.toString()
        }
    }

    "Parsing wrong stuff should throw exception" {
        shouldThrow<IllegalArgumentException> {
            "five".toTrollNumber()
        }
    }
})

