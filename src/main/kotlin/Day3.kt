class Day3 {
    fun start() {
        val file = Utils.puzzleInput(3)
        var priorityCount = 0
        /*("vJrwpWtwJgWrhcsFMMfFFhFp\n" +
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
                "PmmdzqPrVvPwwTWBwg\n" +
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
                "ttgJtRGJQctTZtZT\n" +
                "CrZsJsPPZsGzwwsLwLmpwMDw").split('\n').forEach { line ->*/
        file.forEachLine { line ->
            println(line)
            val firstPart = line.substring(0, line.length/2)
            val secondPart = line.substring(line.length/2)
            val wrongItems = mutableSetOf<Char>()
            secondPart.forEach { item ->
                if (firstPart.contains(item)) wrongItems.add(item)
            }
            wrongItems.forEach { priorityCount += getPriority(it) }
        }
        println(priorityCount)
    }

    fun start2() {
        val file = Utils.puzzleInput(3)
        var priorityCount = 0
        val lines = file.readLines()
        for (i in lines.indices step 3) {
            val matchingItems = mutableSetOf<Char>()
            lines[i].forEach {  item ->
                if (lines[i+1].contains(item) && lines[i+2].contains(item)) {
                    matchingItems.add(item)
                }
            }
            matchingItems.forEach { priorityCount += getPriority(it) }
        }
        println(priorityCount)
    }

    fun getPriority(item: Char): Int {
        return if (item.code > 90) item.code - 'a'.code + 1
               else item.code - 'A'.code + 27
    }
}