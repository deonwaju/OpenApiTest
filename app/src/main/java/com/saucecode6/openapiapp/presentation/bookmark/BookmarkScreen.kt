package com.saucecode6.openapiapp.presentation.bookmark

import EmptyScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.sauceCode6.openapiapp.R
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.presentation.common.ArticlesList
import com.saucecode6.openapiapp.presentation.onboarding.Dimens.MediumPadding1

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        if (state.articles.isNotEmpty()) {
            ArticlesList(
                articles = state.articles,
                onClick = { navigateToDetails(it) }
            )
        } else {
            EmptyScreen()
        }
    }
}
