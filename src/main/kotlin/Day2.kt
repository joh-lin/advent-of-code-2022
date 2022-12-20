import java.lang.RuntimeException

class Day2 {
    fun start() {
        var score = 0
        Utils.puzzleInput(2).forEachLine { line ->
            println(line)
            val moves = line.split(' ')
            val a = moveToNum(moves[0])
            var b = moveToNum(moves[1])
            b = requiredMove(a, b)
            println(b)
            score += b
            score += winOrLoss(a, b)
        }
        println(score)
    }

    fun start2() {
        println(winOrLoss(1, 2))
    }

    fun requiredMove(a: Int, b: Int): Int {
        if (b == 2) return a
        else if (b == 1) { // loose
            return when(a) {
                1 -> 3
                2 -> 1
                3 -> 2
                else -> throw RuntimeException()
            }
        } else { // win
            return when(a) {
                1 -> 2
                2 -> 3
                3 -> 1
                else -> throw RuntimeException()
            }
        }
    }

    fun moveToNum(move: String): Int {
        return when(move) {
            "A" -> 1
            "B" -> 2
            "C" -> 3
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> throw RuntimeException("wrong move")
        }
    }

    fun winOrLoss(a: Int, b: Int): Int {
        val diff = b - a
        return when(diff) {
            0 -> 3
            -1 -> 0
            -2 -> 6
            1 -> 6
            2 -> 0
            else -> throw RuntimeException("error on diff")
        }
    }
}