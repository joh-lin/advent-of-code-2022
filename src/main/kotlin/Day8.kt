import kotlin.math.max

class Day8 {
    fun start() {
        val forest = mutableListOf<MutableList<Int>>()
        Utils.puzzleInput(8).forEachLine { line ->
            forest.add(line.toCharArray().map { it.toString().toInt() }.toMutableList())
        }

        var tempForest = forest
        var visibleForest = MutableList(tempForest.size) {MutableList(tempForest[0].size) {0} }

        for (i in 1..4) {
            for (x in tempForest.indices) {
                var tallest = -1
                for (y in tempForest[x].indices) {
                    if (tempForest[x][y] > tallest) {
                        visibleForest[x][y] = 1
                        tallest = tempForest[x][y]
                    }
                }
            }
            tempForest = rotateMatrix(tempForest)
            visibleForest = rotateMatrix(visibleForest)
        }

        var visibleCount = 0
        for (row in visibleForest) {
            for (tree in row) {
                visibleCount += tree
            }
        }
        println(visibleCount)
    }

    fun getForest(): MutableList<MutableList<Int>> {
        val forest = mutableListOf<MutableList<Int>>()
        Utils.puzzleInput(8).forEachLine { line ->
            forest.add(line.toCharArray().map { it.toString().toInt() }.toMutableList())
        }
        return forest
    }

    fun start2() {
        val forest = getForest()
        var best = 0
        for (x in forest.indices) {
            for (y in forest[x].indices) {
                best = max(getScoreForTree(forest, P(x, y)), best)
            }
        }
        println(best)
    }

    fun getScoreForTree(forest: MutableList<MutableList<Int>>, treePos: P): Int {
        println("current tree: ${forest[treePos.x][treePos.y]}")
        val directions = mutableListOf(P(-1,0), P(1,0), P(0,-1), P(0,1))
        var score = 1
        for (dir in directions) {
            var pos = treePos.copy()
            var visibleTrees = 0
            while (true) {
                pos += dir
                if (!posInBounds(forest, pos)) break
                visibleTrees++
                if (forest[pos.x][pos.y] >= forest[treePos.x][treePos.y]) break
            }
            score *= visibleTrees
        }
        return score
    }

    fun posInBounds(matrix: List<List<Int>>, pos: P): Boolean {
        return (pos.x >= 0 && pos.y >= 0 && pos.x < matrix.size && pos.y < matrix[0].size)
    }

    fun rotateMatrix(matrix: List<List<Int>>): MutableList<MutableList<Int>> {
        val result = MutableList(matrix[0].size) {MutableList(matrix.size) {0} }
        for (x in result.indices) {
            for (y in result[x].indices) {
                result[x][y] = matrix[matrix.size-y-1][x]
            }
        }
        return result
    }

    class P(var x: Int, var y: Int) {
        fun copy() = P(x, y)
        operator fun plus(otherPos: P) = P(x+otherPos.x, y+otherPos.y)
        operator fun minus(otherPos: P) = P(x-otherPos.x, y-otherPos.y)
    }
}