class Day5 {
    fun start() {
        val crates = getStartingCrates()
        Utils.puzzleInput(5).forEachLine { line ->
            if (line.startsWith("move")) {
                val actions = line.split(' ')
                val number = actions[1].toInt()
                val source = actions[3].toInt() - 1
                val destination = actions[5].toInt() - 1
                val temp = mutableListOf<Char>()
                for (i in 1 .. number) {
                    temp.add( crates[source].removeLast() )
                }
                for (i in 1 .. number) {
                    crates[destination].add( temp.removeLast() )
                }
            }
        }
        var output = ""
        crates.forEach { output += it.last() }
        println(output)
    }

    fun getStartingCrates(): List<MutableList<Char>> {
        return listOf(
            "DLVTMHF", "HQGJCTNP", "RSDMPH",
            "LBVF", "NHGLQ", "WBDGRMP",
            "GMNRCHLQ", "CLW", "RDLQJZMT")
            .map { it.toMutableList() }
    }
}