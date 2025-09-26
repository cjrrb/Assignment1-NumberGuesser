package sheridan.reynocor.assignment1.ui.guesser

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import sheridan.reynocor.assignment1.R
import sheridan.reynocor.assignment1.ui.theme.OnDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuesserTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onHelpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) = CenterAlignedTopAppBar(
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
    title = {
        Text(
            text = stringResource(R.string.app_name),
            color = OnDark,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge
        )
    },
    actions = {
        IconButton(onClick = onHelpButtonClick) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = stringResource(R.string.about_guesser),
                tint = OnDark
            )
        }
    },
    scrollBehavior = scrollBehavior,
    modifier = modifier
)