import java.io.File

class Utils {
    companion object {
        fun puzzleInput(day: Int): File {
            return File("puzzle-inputs/day$day.txt")
        }
    }
}