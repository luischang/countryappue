package dev.lchang.countryappue.data.model

//Gemini Request
data class GeminiRequest(
    val contents: List<Content>
)

data class Content(
    val parts : List<Part>
)

data class Part (
    val text: String
)

//Gemini Response

data class GeminiResponse(
    val candidates: List<Candidate>
)

data class Candidate(
    val content: Content
)
