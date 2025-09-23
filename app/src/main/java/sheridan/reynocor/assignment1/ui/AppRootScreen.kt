package sheridan.reynocor.assignment1.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import sheridan.reynocor.assignment1.R
import sheridan.reynocor.assignment1.ui.guesser.GuesserBody
import sheridan.reynocor.assignment1.ui.guesser.GuesserTopBar
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRootScreen(){
    var showAboutDialog: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            GuesserTopBar(
                scrollBehavior = scrollBehavior,
                onHelpButtonClick = { showAboutDialog = true }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        innerPadding ->
        GuesserBody(
            viewModel = viewModel(),
            modifer = Modifier.padding(innerPadding)
        )
    }

    if(showAboutDialog){
        GuesserAbout(
            onDismissRequest = { showAboutDialog = false }
        )
    }
}

@Composable
fun GuesserAbout(onDismissRequest: () -> Unit) =
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(stringResource(R.string.app_name)) },
        text = {
            Text(
                text = stringResource(R.string.about_guesser),
                fontSize = 18.sp
            )
        },
        confirmButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = stringResource(R.string.ok))
            }
        }
    )