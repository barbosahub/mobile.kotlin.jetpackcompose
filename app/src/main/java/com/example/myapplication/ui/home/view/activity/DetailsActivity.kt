package com.example.myapplication.ui.home.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.myapplication.stateFlow.StateFlow
import com.example.myapplication.ui.MyTopAppBar
import com.example.myapplication.ui.ProgressBar
import com.example.myapplication.ui.home.viewModel.AlbumsViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : ComponentActivity() {
    private val viewModel: AlbumsViewModel by viewModel()
    private var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InitValues()
                    InitObservables()
                }
            }
        }
    }

    @Composable
    private fun InitValues() {
        viewModel.getAlbumsById(intent.getIntExtra("id", 0))
        url = intent.getStringExtra("url").toString()
    }

    @Composable
    private fun InitObservables() {
        viewModel.album.observeAsState().value.apply {
            when (this) {
                is StateFlow.Loading -> Loading(loading)
                is StateFlow.Error -> Error(errorMessage)
                is StateFlow.Success<*> -> InitPhotos(data as ArrayList<*>)
                else -> {}
            }
        }
    }

    @Composable
    private fun Loading(loading: Boolean) {
        ProgressBar(visibility = loading)
    }

    @Composable
    private fun Error(errorMessage: String) {
        Toast.makeText(this, "ERROR:$errorMessage", Toast.LENGTH_SHORT).show()
    }

    @Composable
    private fun InitPhotos(result: ArrayList<*>) {
        viewModel.fillAlbum(result)

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (appBar, body) = createRefs()

            MyTopAppBar(
                Modifier
                    .constrainAs(appBar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
                    .height(56.dp))

            Body(Modifier
                .constrainAs(body) {
                    top.linkTo(appBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })


        }
    }

    @Composable
    fun Body(modifier: Modifier) {
        Surface(
            modifier = modifier.padding(0.dp, 36.dp),
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (image, txtId, txtUserId, txtTitle) = createRefs()
                AsyncImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentDescription = viewModel.albumObj.title,
                    model = url
                )

                Text(
                    modifier = Modifier.constrainAs(txtTitle) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    text = "Title: ${viewModel.albumObj.title}"
                )

                Text(
                    modifier = Modifier.constrainAs(txtUserId) {
                        top.linkTo(txtTitle.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    text = "Id: ${viewModel.albumObj.userId}"
                )
                Text(
                    modifier = Modifier.constrainAs(txtId) {
                        top.linkTo(txtUserId.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    text = "User Id: ${viewModel.albumObj.id}"
                )
            }
        }
    }
}


