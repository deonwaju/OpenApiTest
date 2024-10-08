package com.saucecode6.openapiapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.sauceCode6.openapiapp.R

data class Page(
    val title: String,
    val desc: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Welcome to Sauce News",
        desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.ic_skyline
    ),
    Page(
        title = "Get Free News Updates",
        desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.ic_skyline
    ),
    Page(
        title = "All In The Palm Of Your Hand",
        desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.ic_skyline
    )
)