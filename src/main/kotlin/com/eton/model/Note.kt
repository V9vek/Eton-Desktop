package com.eton.model

import com.eton.ui.value.*

var lastId = 0      // manually increasing note id

data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int = lastId
) {
    companion object {
        val noteColors = listOf(
            YellowOrange,
            RedOrange,
            Violet,
            BabyBlue,
            LightGreen
        )
    }

    init {
        ++lastId
    }
}















