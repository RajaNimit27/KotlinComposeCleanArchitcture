package com.app.kotlincomposecleanarchitcture.ui.composeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.kotlincomposecleanarchitcture.ui.viewmodel.MainViewModel
import common.UiState
import data.model.PostEntity

@Composable
fun PostListScreen(mainViewModel: MainViewModel) {
    Scaffold(
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //add your code
            LaunchedEffect(key1 = Unit) {
               mainViewModel.getPostList()
            }
            val state = mainViewModel.uiStatePostList.collectAsState()
            when (state.value) {
                is UiState.Success -> {
                    (state.value as UiState.Success<List<PostEntity>>).data?.let {
                        PostList(it)
                    }
                }

                is UiState.Loading -> {
                }

                is UiState.Error -> {
                    //Handle Error
                }
            }
        }
    }


}

@Composable
fun PostListCard(postEntity: PostEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = postEntity.body ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostList(posts: List<PostEntity>) {
    LazyColumn {
        items(posts.size) { i ->
            PostListCard(postEntity = posts[i])
        }
    }
}