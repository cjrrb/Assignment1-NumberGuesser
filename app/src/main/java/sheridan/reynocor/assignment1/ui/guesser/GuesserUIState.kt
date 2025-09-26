package sheridan.reynocor.assignment1.ui.guesser

import kotlin.random.Random

data class GuesserUIState(
    val randomNumber: Int = Random.nextInt(1, 11),
    val userGuess: String = "",
    val isGuessCorrect: Boolean = false,
    val guessAttempts: Int = 0,
    val dialogDescription: String? = "",
    val dialogTitle: String? = "",
    val showDialog: Boolean = false
)
