package trollnumbers

/**
 * A numeral system based on powers of 4.
 * The base numerals are [one] (1), [two] (2), [three] (3), [many] (4) and [lots] (16), which can be combined to form higher numbers.
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
 * For simple troll numbers, see [one], [two], [three], [many], [lots]
 *
 * For parsing troll numbers from strings, see [toTrollNumber]
 *
 * @property first A simple troll number or expression
 * @property second A simple troll number or expression
 * @constructor Evaluates the two troll numbers
 */
data class TrollNumberExpression(val first: TrollNumber, val second : TrollNumber) : TrollNumber(run {
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