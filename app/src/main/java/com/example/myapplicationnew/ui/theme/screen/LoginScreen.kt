import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplicationnew.viewModel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    val username by viewModel.username.collectAsState()
    val errorMessage by viewModel.errormessage.collectAsState()
    val loginSuccess by viewModel.loginSuccess

    var account by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (loginSuccess) {
        LaunchedEffect(Unit) {
            val currentusername = username
            navController.navigate("review/$currentusername") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome App",
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = account,
            onValueChange = { account = it },
            label = { Text("Account") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { viewModel.login(account, password) }) {
                Text("Login")
            }

            Button(onClick = {
                navController.navigate("createuser")
            }) {
                Text("Create")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        username?.let {
            Text("Welcome, $it!", color = Color(0xFF2E7D32)) // Material Green
        }

        errorMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}

