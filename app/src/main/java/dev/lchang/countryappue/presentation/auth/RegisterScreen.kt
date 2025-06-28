package dev.lchang.countryappue.presentation.auth

import android.webkit.WebView
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import dev.lchang.countryappue.data.remote.firebase.FirebaseAuthManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController){
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val context = LocalContext.current

    var acceptTerms by remember { mutableStateOf(false) }
    var showTermsDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Registro", style = MaterialTheme.typography.titleLarge)
        // Campo para ingresar el nombre completo
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth()
        )
        // Campo para ingresar el correo electrónico
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )
        // Campo para ingresar la contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
            )
        // Campo para confirmar la contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Repetir Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(checked = acceptTerms, onCheckedChange = { acceptTerms = it })
            Button(onClick = {showTermsDialog = true}) {
                Text("Acepto los términos y condiciones")
            }
        }


        // Botón para registrarse
        Button(onClick = {
            if(password == confirmPassword && fullName.isNotBlank()){
                CoroutineScope(Dispatchers.Main).launch {
                    val result = FirebaseAuthManager.registerUser(fullName, email, password)
                    if(result.isSuccess){
                        navController.navigate("login")
                    }else{
                        val error = result.exceptionOrNull()?.message?: "Error desconocido"
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                    }
                }
            }

        }, modifier = Modifier.fillMaxWidth()) {
            Text("Registrarse")
        }

        //Dialog Terms
        if(showTermsDialog) {
            AlertDialog(
                onDismissRequest = { showTermsDialog = false },
                confirmButton = {
                    TextButton(onClick = { showTermsDialog = false }) {
                        Text("Cerrar")
                    }
                }
                , title = { Text("Términos y Condiciones") },
                text = {
                    //WebView
                    AndroidView(
                        factory = {context ->
                            WebView(context).apply {
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                webViewClient = object : android.webkit.WebViewClient(){
                                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                                        url?.let { view?.loadUrl(it) }
                                        return true
                                    }
                                }
                                loadUrl("https://www.privacypolicies.com/live/c2d4dc46-977d-4270-a585-19e1058a6b4f")
                            }

                        }, modifier = Modifier.weight(1f)
                    )
                }
            )
        }
    }
}