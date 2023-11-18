package com.example.myapplication.ui.home.view.activity

//class DetailsActivity : ComponentActivity() {
//    private val viewModel: PhotosViewModel by viewModel()
//    private var url = ""
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            MyApplicationTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    InitValues()
//                    InitObservables()
//                }
//            }
//        }
//    }
//
//    @Composable
//    private fun InitValues() {
//        viewModel.getAlbumsById(intent.getIntExtra("id", 0))
//        url = intent.getStringExtra("url").toString()
//    }
//
//    @Composable
//    private fun InitObservables() {
//        viewModel.album.observeAsState().value.apply {
//            when (this) {
//                is StateFlow.Loading -> Loading(loading)
//                is StateFlow.Error -> Error(errorMessage)
//                is StateFlow.Success<*> -> InitPhotos(data as ArrayList<*>)
//                else -> {}
//            }
//        }
//    }
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
//    private fun InitPhotos(result: ArrayList<*>) {
//        viewModel.fillAlbum(result)
//
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
//            Body(Modifier
//                .constrainAs(body) {
//                    top.linkTo(appBar.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })
//
//
//        }
//    }
//
//    @Composable
//    fun Body(modifier: Modifier) {
//        Surface(
//            modifier = modifier.padding(0.dp, 36.dp),
//        ) {
//            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//                val (image, txtId, txtUserId, txtTitle) = createRefs()
//                AsyncImage(
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .clip(RoundedCornerShape(10.dp))
//                        .constrainAs(image) {
//                            top.linkTo(parent.top)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        },
//                    contentDescription = viewModel.albumObj.title,
//                    model = url
//                )
//
//                Text(
//                    modifier = Modifier.constrainAs(txtTitle) {
//                        top.linkTo(image.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    },
//                    text = "Title: ${viewModel.albumObj.title}"
//                )
//
//                Text(
//                    modifier = Modifier.constrainAs(txtUserId) {
//                        top.linkTo(txtTitle.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    },
//                    text = "Id: ${viewModel.albumObj.userId}"
//                )
//                Text(
//                    modifier = Modifier.constrainAs(txtId) {
//                        top.linkTo(txtUserId.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    },
//                    text = "User Id: ${viewModel.albumObj.id}"
//                )
//            }
//        }
//    }
//}


