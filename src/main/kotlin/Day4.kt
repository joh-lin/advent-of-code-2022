class Day4 {
    fun start() {
        val file = Utils.puzzleInput(4)
        var count = 0
        file.forEachLine { line ->
        /*("2-4,6-8\n" +
                "2-3,4-5\n" +
                "5-7,7-9\n" +
                "2-8,3-7\n" +
                "6-6,4-6\n" +
                "2-6,4-8").split('\n').forEach {line ->*/
            val pairs = line.split(',')
            val elf1 = pairs[0].split('-').map { it.toInt() }
            val elf2 = pairs[1].split('-').map { it.toInt() }
            //println("el1: ${elf1}")
            //println("el2: ${elf2}")

            if (!(elf1[0] < elf2[0] && elf1[1] < elf2[0]) && !(elf1[0] > elf2[1] && elf1[1] > elf2[1])) {
                count++
            }

        }
        println(count)
    }

    fun start2() {
        Utils.puzzleInput(4).readLines().sumOf { line ->
            {(elf1: List<Int>, elf2: List<Int>): List<List<Int>> ->
                if (!(elf1[0] < elf2[0] && elf1[1] < elf2[0])
                    && !(elf1[0] > elf2[1] && elf1[1] > elf2[1])) 1 else 0
            }(line.split(',').map { pair ->  pair.split('-').map { it.toInt() }})
        }.also { println(it) }
    }
}