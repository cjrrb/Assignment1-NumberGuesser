package sheridan.reynocor.assignment1.ui.guesser

import kotlin.random.Random

data class GuesserUIState(
    val randomNumber: Int = Random.Default.nextInt(1, 11),
    val userGuess: Int = 0,
    val guessCorrect: Boolean = false,
    val guessAttempts: Int = 0

)
