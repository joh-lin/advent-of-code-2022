import java.lang.RuntimeException

class Day7 {
    fun start() {
        val rootDirectory = Directory("/")
        var currentDirectory = rootDirectory

        Utils.puzzleInput(7).forEachLine { line ->
            if (line.startsWith("$")) {
                val params = line.split(" ")
                if (params[1] == "cd") {
                    if (params[2] == "..") currentDirectory = currentDirectory.parent
                        ?: throw RuntimeException("Directory ${currentDirectory.name} has no parent.")
                    else {
                        currentDirectory = currentDirectory.findOrAddDirectory(params[2])
                    }
                }
            } else if (!line.startsWith("dir")) {
                val fileData = line.split(" ")
                currentDirectory.findOrAddFile(fileName = fileData[1], fileSize = fileData[0].toInt())
            }
        }

        val totalSize = 70_000_000
        val maximumSize = totalSize - 30000000
        val currentSize = rootDirectory.determineSize()
        println("Current Size: $currentSize - ${(currentSize/totalSize.toFloat()*100).toInt()}%")

        val allDirectories = rootDirectory.getAllSubdirectories()
        val viableDirectories = mutableListOf<Directory>()

        for (dir in allDirectories) {
            if (currentSize - dir.size <= maximumSize) viableDirectories.add(dir)
        }

        viableDirectories.sortBy { it.size }
        println("Best Directory: \"${viableDirectories[0].name}\" with size of ${viableDirectories[0].size}")
    }

    class Directory(val name: String, val parent: Directory? = null) {
        val files = mutableListOf<File>()
        val directories = mutableListOf<Directory>()
        var size = 0
        fun findOrAddFile(fileName: String, fileSize: Int): File {
            for (file in files) {
                if (file.name == fileName) return file
            }
            return addFile(fileName, fileSize)
        }
        fun findOrAddDirectory(dirName: String): Directory {
            for (dir in directories) {
                if (dir.name == dirName) return dir
            }
            return addDirectory(dirName)
        }
        private fun addFile(fileName: String, fileSize: Int): File {
            val newFile = File(fileName, fileSize, this)
            files.add(newFile)
            return newFile
        }
        private fun addDirectory(dirName: String): Directory {
            val directory = Directory(dirName, this)
            directories.add(directory)
            return directory
        }
        fun determineSize(): Int {
            size = files.sumOf { it.size } +
                   directories.sumOf { it.determineSize() }
            return size
        }
        fun getAllSubdirectories(): List<Directory> {
            val result = mutableListOf<Directory>()
            result.addAll(directories)
            directories.forEach { dir ->
                result.addAll(dir.getAllSubdirectories())
            }
            return result
        }
    }
    data class File(val name: String, val size: Int, val parent: Directory)
}