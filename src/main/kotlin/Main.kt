fun main() {
    val plate = mutableListOf("[1]", "[2]", "[3]", "[4]", "[5]", "[6]", "[7]", "[8]", "[9]")
    val currentPlayer = "X"

    fun displayBoard(plate: MutableList<String>) {
        for (i in 0 until 9 step 3) {
            println("${plate[i]} ${plate[i + 1]} ${plate[i + 2]}")
        }
    }

    displayBoard(plate)

    fun checkWins(plate: MutableList<String>): Boolean {
        val winningPositions = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )

        for (i in plate.indices) {
            if (plate[i] == "[X]" || plate[i] == "[O]") {
                for (j in winningPositions.indices) {
                    if (plate[winningPositions[j][0]] == plate[winningPositions[j][1]] && plate[winningPositions[j][1]] == plate[winningPositions[j][2]]) {
                        println("Player ${plate[i]} wins!")
                        return true
                    }
                }
            }
        }
        return false
    }

    fun selectPosition(plate: MutableList<String>, currentPlayer: String){
        while (!checkWins(plate)) {
            if (plate.all { it == "[X]" || it == "[O]" }) {
                println("It's a tie!")
                return
            }
            println("Player $currentPlayer, please enter a position (1-9):")
            val position = readlnOrNull()?.toIntOrNull()
            if ( position == null || position < 1 || position > 9 ) {
                println("Invalid Input, Enter a position between 1 and 9")
                return selectPosition(plate, currentPlayer)
            } else {
                if (plate[position - 1] == "[X]" || plate[position - 1] == "[O]") {
                    println("Position already taken, please enter another position")
                    return selectPosition(plate, currentPlayer)
                } else {
                    plate[position - 1] = "[$currentPlayer]"
                    displayBoard(plate)
                }
                return selectPosition(plate, if (currentPlayer == "X") "O" else "X")
            }
        }
    }

    selectPosition(plate, currentPlayer)
}

