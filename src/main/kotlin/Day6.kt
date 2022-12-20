class Day6 {
    fun start() {
        val signal = Utils.puzzleInput(6).readText()
        for (i in 13 until signal.length) {
            val symbols = mutableSetOf<Char>()
            for (c in 0 until 14) {
                symbols.add(signal[i-c])
            }
            if (symbols.size == 14) {
                println(i + 1)
                return
            }
        }
    }
}