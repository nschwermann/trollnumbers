package trollnumbers

/**
 * An extension method to create troll numbers from a string.
 *
 * Examples:
 * * "one".toTrollNumber() => one
 * * "many-two".toTrollNumber() => many-two
 * * "lots".toTrollNumber() => lots
 */
fun String.toTrollNumber(): TrollNumber {
    if (this.contains("-")) {
        val split = this.split("-")
        val trollNumbers = split.map { s -> s.toTrollNumber() }
        return trollNumbers.reduce { acc, trollNumber -> acc + trollNumber }
    }

    return when (this) {
        "one" -> one
        "two" -> two
        "three" -> three
        "many" -> many
        "lots" -> lots
        else -> throw IllegalArgumentException("Unknown troll number")
    }
}

/**
 * An extension method to create a troll number from an integer.
 *
 * Examples:
 * * 3.toTrollNumber() => three
 * * 5.toTrollNumber() => many-one
 * * 11.toTrollNumber() => many-many-three
 */
fun Int.toTrollNumber(): TrollNumber {
    return when (this) {
        in Int.MIN_VALUE .. 0 -> throw IllegalArgumentException("Unknown troll number")
        1 -> one
        2 -> two
        3 -> three
        4 -> many
        in 5..15 ->{
            val noOfManys = this / 4
            val manyExpression = if(noOfManys > 1){
                List<TrollNumber>(noOfManys){many}.reduce{acc, next -> TrollNumberExpression(acc, next)}
            } else many
            val rest = this % 4
            if(rest > 0) TrollNumberExpression(manyExpression, rest.toTrollNumber()) else manyExpression
        }
        16 -> lots
        else -> {
            val noLots = this / 16
            val lotsList = List<TrollNumber>(noLots){ lots }
            val rest = this % 16
            val lotsExp = if(lotsList.size > 1){
                lotsList.reduce{acc, next -> TrollNumberExpression(acc, next)}
            } else {
                lots
            }
            if(rest > 0) TrollNumberExpression(lotsExp, rest.toTrollNumber()) else lotsExp
        }
    }
}

/**
 * A way to construct complex troll numbers using the minus sign.
 *
 * Examples:
 * * many-one => many-one
 * * many-many-three => many-many-three
 * * many-many-many-two => many-many-many-two
 */
operator fun TrollNumber.minus(other: TrollNumber): TrollNumber = this + other

/**
 * Add two troll numbers to create a new one.
 *
 * Examples:
 * * one + one => two
 * * many + two => many-two
 * * many-one + many-one => many-many-two
 */
operator fun TrollNumber.plus(other: TrollNumber): TrollNumber = (this.value + other.value).toTrollNumber()