package sheridan.reynocor.assignment1.ui.guesser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    modifier: Modifier = Modifier,
    viewModel: GuesserViewModel
) {
    val uiState: GuesserUIState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter a number from 1-10",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

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
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Attempts: ${uiState.guessAttempts}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground)
        }

        if (uiState.showDialog) {
            AlertDialog(
                onDismissRequest = {
                    if (!uiState.isGuessCorrect) {
                        viewModel.hideDialog()
                    }
                },
                title = {
                    Text(text = uiState.dialogTitle!!)
                },
                text = {
                    Text(text = uiState.dialogDescription!!)
                },
                confirmButton = {
                    val buttonText =
                        if (uiState.isGuessCorrect) {
                            stringResource(R.string.play_again)
                        }
                        else {
                            stringResource(R.string.close)
                        }
                    TextButton(
                        onClick = {
                            if (uiState.isGuessCorrect) {
                                viewModel.playAgain()
                            }
                            else {
                                viewModel.hideDialog()
                            }
                        },
                    ) {
                        Text(buttonText)
                    }
                },
                dismissButton = null
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