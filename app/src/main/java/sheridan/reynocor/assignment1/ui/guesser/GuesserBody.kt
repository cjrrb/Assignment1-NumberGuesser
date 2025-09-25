package sheridan.reynocor.assignment1.ui.guesser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sheridan.reynocor.assignment1.R

@Composable
fun GuesserBody(
    viewModel: GuesserViewModel,
    modifier: Modifier = Modifier
) {
    val uiState: GuesserUIState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter a number from 1-10")

        OutlinedTextField(
            value = uiState.userGuess,
            onValueChange = { viewModel.updateUserGuess(it)},
            label = { Text(text = "Guess") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.6f)
        )

        Button(
            onClick = { viewModel.submitGuess() },
            enabled = uiState.userGuess.isNotBlank()

        ) {
            Text(text = "Submit")
        }

        if (uiState.guessAttempts > 0 && !uiState.isGuessCorrect) {
            Text(text = "Attempts: ${uiState.guessAttempts}")
        }

        if (uiState.showDialog && uiState.message !== null) {
            AlertDialog(
                onDismissRequest = {
                    if (!uiState.isGuessCorrect) {
                        viewModel.hideDialog()
                    }
                },

                title = {
                    Text(text = if (uiState.isGuessCorrect) stringResource(R.string.correct) else stringResource(R.string.play_again))
                },
                text = {
                    Text(text = uiState.message!!)
                },
                confirmButton = {
                    if (uiState.isGuessCorrect) {
                        TextButton(onClick = { viewModel.playAgain() }) {
                            Text(text = "Play Again")
                        }
                    } else {
                        TextButton(onClick = { viewModel.hideDialog() }) {
                            Text(text = "Close")
                        }
                    }
                },
                dismissButton = {
                    if (uiState.isGuessCorrect) {
                        null
                    } else {
                        null
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuesserBodyPreview() {
    MaterialTheme {
        GuesserBody(viewModel = GuesserViewModel())
    }
}