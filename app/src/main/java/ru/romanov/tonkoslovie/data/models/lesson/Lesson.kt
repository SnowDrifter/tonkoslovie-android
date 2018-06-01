package ru.romanov.tonkoslovie.data.models.lesson

data class Lesson (
        val id: Long,
        val title: String,
        val annotation: String,
        val content: String,
        val previewImage: String
)