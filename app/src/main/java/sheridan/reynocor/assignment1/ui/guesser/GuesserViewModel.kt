package sheridan.reynocor.assignment1.ui.guesser

import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GuesserViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<GuesserUIState> =
        MutableStateFlow(GuesserUIState())
    val uiState: StateFlow<GuesserUIState> = _uiState

    fun updateUserGuess(guess: String) {
        if (guess.all { it.isDigit() } && guess.length <= 2) {
            _uiState.update { currentState -> currentState.copy(userGuess = guess)}
        }
    }

    fun submitGuess() {
        val guess = _uiState.value.userGuess.toIntOrNull()
        if (guess == null || guess !in 1..10) {
            _uiState.update { it.copy(
                message = "Please enter a number between 1 and 10",
                showDialog = true,
                isGuessCorrect = false
            ) }
            return
        }

        val currentAttempts = _uiState.value.guessAttempts + 1
        val randomNumber = _uiState.value.randomNumber

        when {
            //When user correct
            guess == randomNumber -> {
                _uiState.update { currentState -> currentState.copy(
                    guessAttempts = currentAttempts,
                    message = "You guessed the number in $currentAttempts attempts",
                    showDialog = true,
                    isGuessCorrect = true
                    )
                }
            }

            //When user guess is low
            guess < randomNumber -> {
                _uiState.update { currentState -> currentState.copy(
                        guessAttempts = currentAttempts,
                        message = "The number is larger.",
                        showDialog = true,
                        userGuess = ""
                    )
                }
            }

            //When user guess is high
            else -> {
                _uiState.update { currentState -> currentState.copy(
                        guessAttempts = currentAttempts,
                        message = "The number is smaller.",
                        showDialog = true,
                        userGuess = ""
                    )
                }
            }
        }
    }

    fun playAgain() {
        _uiState.value = GuesserUIState()
    }

    fun hideDialog() {
        _uiState.update { it.copy(showDialog = false, message = null)}
    }
}