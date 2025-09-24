package sheridan.reynocor.assignment1.ui.guesser

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import sheridan.reynocor.assignment1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuesserTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onHelpButtonClick: () -> Unit
) = CenterAlignedTopAppBar(
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
    ),
    title = {
        Text(
            text = stringResource(R.string.app_name)
        )
    },
    actions = {
        IconButton(
            onClick = onHelpButtonClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_help_outline_24),
                contentDescription = "Menu"
            )
        }
    },
    scrollBehavior = scrollBehavior
)