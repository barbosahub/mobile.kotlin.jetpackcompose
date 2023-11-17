package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.albumlist.AlbumListScreen

import com.example.myapplication.albumlist.AlbumsViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val viewModel = koinViewModel<AlbumsViewModel>()
                    val uiState = viewModel.uiState.value


                    NavHost(
                        navController = navController,
                        startDestination = "albums") {

                        composable("albums") {
                            AlbumListScreen(uiState = uiState)
                        }

                    }
                }
            }
        }
    }
}


//    @Composable
//    private fun InitValues(loaded: Boolean) {
//        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//            val (appBar, body) = createRefs()
//
//            MyTopAppBar(
//                Modifier
//                    .constrainAs(appBar) {
//                        top.linkTo(parent.top)
//                        start.linkTo(parent.start)
//                    }
//                    .fillMaxWidth()
//                    .height(56.dp))
//
//            InitRecyclerView(
//                Modifier
//                    .constrainAs(body) {
//                        top.linkTo(appBar.bottom)
//                        start.linkTo(parent.start)
//                    }
//                    .fillMaxSize())
//        }
//
//        if (!loaded)
//            viewModel.getPhotos()
//
//    }


/*
@Composable
private fun InitObservables() {
    viewModel.photosList.observeAsState().value.apply {
        when (this) {
            is StateFlow.Loading -> Loading(loading)
            is StateFlow.Error -> Error(errorMessage)
            is StateFlow.Success<*> -> InitPhotos(data as ArrayList<*>)
            else -> {}
        }
    }
}
 */
//
//    @Composable
//    private fun Loading(loading: Boolean) {
//        ProgressBar(visibility = loading)
//    }
//
//    @Composable
//    private fun Error(errorMessage: String) {
//        Toast.makeText(this, "ERROR:$errorMessage", Toast.LENGTH_SHORT).show()
//    }
//
//    @Composable
//    private fun InitPhotos(arrayList: ArrayList<*>) {
//        viewModel.fillPhotos(arrayList)
//
//        InitValues(loaded = true)
//    }
//
//    @Composable
//    fun InitRecyclerView(modifier: Modifier) {
//        MyRecyclerView(
//            photosArray = viewModel.photosArray, modifier
//        ) {photoJson ->
//            Intent(this, DetailsActivity::class.java).apply {
//                putExtra("id", photoJson.id)
//                putExtra("url", photoJson.url)
//                startActivity(this)
//            }
//        }
//    }




