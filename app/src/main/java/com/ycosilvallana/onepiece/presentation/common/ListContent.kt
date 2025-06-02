package com.ycosilvallana.onepiece.presentation.common

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ycosilvallana.onepiece.R
import com.ycosilvallana.onepiece.domain.model.CharacterEntity
import com.ycosilvallana.onepiece.navigation.Screen
import com.ycosilvallana.onepiece.presentation.components.RatingWidget
import com.ycosilvallana.onepiece.ui.theme.CHARACTER_ITEM_HEIGHT
import com.ycosilvallana.onepiece.ui.theme.EXTRA_SMALL_PADDING
import com.ycosilvallana.onepiece.ui.theme.LARGE_PADDING
import com.ycosilvallana.onepiece.ui.theme.MEDIUM_PADDING
import com.ycosilvallana.onepiece.ui.theme.SMALL_PADDING
import com.ycosilvallana.onepiece.ui.theme.topAppBarContentColor
import com.ycosilvallana.onepiece.util.Constants.BASE_URL

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<CharacterEntity>,
    navController: NavHostController
) {
    Log.d("ListContent", characters.loadState.toString())
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(count = characters.itemCount) { index ->
            val character = characters[index]
            character?.let {
                CharacterItem(character = it, navController = navController)
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterEntity,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(CHARACTER_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passCharacterId(characterId = character.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(corner = CornerSize(LARGE_PADDING))) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_URL${character.image}")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                contentDescription = stringResource(R.string.character_image_placeholder),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = character.name,
                    color = MaterialTheme.colorScheme.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = character.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = character.rating
                    )
                    Text(
                        text = "(${character.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CharacterItemPreview() {
    CharacterItem(
        character = CharacterEntity(
            id = 13,
            name = "Marco the Phoenix",
            image = "/images/marco.jpg",
            about = "First division commander of the Whitebeard Pirates and a powerful Mythical Zoan user. Marco can transform into a phoenix and regenerate from injuries with blue flames.",
            rating = 4.8,
            power = 9400,
            month = "October",
            day = "5",
            family = listOf("Whitebeard Pirates"),
            abilities = listOf("Tori Tori no Mi, Model: Phoenix", "Blue Flame Regeneration", "Flight", "Healing"),
            natureTypes = listOf("Fire", "Air", "Resilience")
        ),
        navController = rememberNavController()
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CharacterItemDarkPreview() {
    CharacterItem(
        character = CharacterEntity(
            id = 13,
            name = "Marco the Phoenix",
            image = "/images/marco.jpg",
            about = "First division commander of the Whitebeard Pirates and a powerful Mythical Zoan user. Marco can transform into a phoenix and regenerate from injuries with blue flames.",
            rating = 4.8,
            power = 9400,
            month = "October",
            day = "5",
            family = listOf("Whitebeard Pirates"),
            abilities = listOf("Tori Tori no Mi, Model: Phoenix", "Blue Flame Regeneration", "Flight", "Healing"),
            natureTypes = listOf("Fire", "Air", "Resilience")
        ),
        navController = rememberNavController()
    )
}
