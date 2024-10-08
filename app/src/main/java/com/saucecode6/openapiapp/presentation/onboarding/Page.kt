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
        title = "Lorem Ipsum is simply dummy",
        desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)