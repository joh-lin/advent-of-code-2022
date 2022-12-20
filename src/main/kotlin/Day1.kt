class Day1 {
    fun start() {
        println(Utils.puzzleInput(1).readText().split("\n\n").map { block ->
            block.split("\n").sumOf { if (it.isNotBlank()) it.toInt() else 0}
        }.sortedDescending().subList(0, 3).sum())
    }
}