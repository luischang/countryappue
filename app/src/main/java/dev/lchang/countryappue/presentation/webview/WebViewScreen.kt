package dev.lchang.countryappue.presentation.webview

import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen() {
    var url by remember { mutableStateOf("")}
    var currentUrl  by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
    {
        Text("Ingrese una URL")
        OutlinedTextField(
            value = url,
            onValueChange = {url = it},
            label = {Text("URL")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier= Modifier.padding(top = 16.dp),
            onClick = {
                currentUrl = url
            }
        ){
            Text("Cargar página web")
        }

        if(currentUrl.isNotBlank()){
            key(currentUrl){
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
                            loadUrl(currentUrl)
                        }

                    }, modifier = Modifier.weight(1f)
                )
            }
        }
    }
}