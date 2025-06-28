package dev.lchang.countryappue.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lchang.countryappue.data.gemini.GeminiApiService
import dev.lchang.countryappue.data.model.Content
import dev.lchang.countryappue.data.model.GeminiRequest
import dev.lchang.countryappue.data.model.Part
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeminiViewModel: ViewModel() {

    private val apiService = Retrofit.Builder()
        .baseUrl("https://generativelanguage.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = apiService.create(GeminiApiService::class.java)

    var prompt by mutableStateOf("")
    var response by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun askGemini() {
        viewModelScope.launch {
            try {
                isLoading = true
                val request = GeminiRequest(
                    contents = listOf(
                        Content(parts = listOf(Part(text = prompt)))
                    )
                )
                val result = api.generateContent("AIzaSyDUV7ir1ZhYgNK0ko3vRbuS56FjNT-vCso", request)
                response = result.candidates[0].content.parts[0].text
            }
            catch (e: Exception) {
                response = "Error: ${e.message}"
            }
            finally {
                isLoading = false
            }

        }
    }
}