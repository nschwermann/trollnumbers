package trollnumbers

/**
 * An interface for troll numbers
 *
 * @property value The troll number's numeric value
 */
sealed class TrollNumber(val value : Int)

/** The simple troll number one, with the value of 1 */
object one : TrollNumber(1){
    override fun toString() : String = "one"
}

/** The simple troll number two, with the value of 2 */
object two : TrollNumber(2){
    override fun toString() : String = "two"
}

/** The simple troll number three, with the value of 3 */
object three : TrollNumber(3){
    override fun toString() : String = "three"
}

/** The simple troll number many, with the value of 4 */
object many : TrollNumber(4){
    override fun toString() : String = "many"
}

/** The simple troll number lots, with the value of 16 */
object lots : TrollNumber(16){
    override fun toString() : String = "lots"
}

/**
 * A class for creating complex troll numbers, given a pair of troll numbers
 *
 * For simple troll numbers, see [TrollNumber]
 *
 * For parsing troll numbers from strings, see [toTrollNumber]
 *
 * @property trollNumbers A list of troll numbers to construct the complex troll number from
 * @constructor Create a complex troll number given a list of troll numbers
 */
internal data class TrollNumberExpression(val first: TrollNumber, val second : TrollNumber) : TrollNumber(run {
    val eval1 = when (first) {
        is TrollNumberExpression -> first.first.value + first.second.value
        else -> first.value
    }
    val eval2 = when (second) {
        is TrollNumberExpression -> second.first.value + second.second.value
        else -> second.value
    }
    eval1 + eval2
}) {
    override fun toString() : String = "$first-$second"

}