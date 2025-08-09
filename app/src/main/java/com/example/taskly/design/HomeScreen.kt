package com.example.taskly.design

import NotesCardView
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskly.NavItem
import com.example.taskly.R
import com.example.taskly.viewmodel.ViewModelView
import java.io.File


@Composable
fun HomeScreen(viewModel: ViewModelView = hiltViewModel(), onclick: () -> Unit) {
    var isSheet by remember {
        mutableStateOf(false)
    }
    var selectedIndex by remember { mutableStateOf(0) }
    val navItems = listOf(
        NavItem(
            "Index",
            arrayOf(painterResource(R.drawable.home_2), painterResource(R.drawable.home))
        ),
        NavItem(
            "Calendar",
            arrayOf(
                painterResource(R.drawable.calendar),
                painterResource(R.drawable.calendar_selected)
            )
        ),
        NavItem(
            "Clock",
            arrayOf(painterResource(R.drawable.clock), painterResource(R.drawable.clock_selected))
        ),
        NavItem(
            "Profile",
            arrayOf(painterResource(R.drawable.user), painterResource(R.drawable.user))
        )
    )
    Scaffold(
        containerColor = colorResource(R.color.black),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isSheet = true;
                }, modifier = Modifier
                    .padding(5.dp)
                    .size(64.dp)
                    .offset(y = 50.dp)
                    .zIndex(2f),
                containerColor = colorResource(R.color.floating_purple),
                shape = CircleShape
            ) {
                Icon(
                    Icons.Default.Add, contentDescription = "Add",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp),
                    tint = Color.White
                )
            }
        }, floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {

            NavigationBar(
                tonalElevation = 20.dp,
                containerColor = colorResource(R.color.primary_gray)

            ) {
                navItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                painter = if (selectedIndex == index) {
                                    item.icon[1]
                                } else {
                                    item.icon[0]
                                },
                                contentDescription = item.title,
                                tint = Color.White
                            )
                        },
                        label = {
                            Text(
                                item.title,
                                color = colorResource(R.color.white)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    )
    { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, end = 5.dp)
                    .height(42.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.filter),
                    contentDescription = "My photo",
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.Center),
                        text = "Index",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    )
                }

                Header(viewModel)
            }
            Spacer(
                modifier = Modifier
                    .size(15.dp)
            )
            SearchView()

            Spacer(
                modifier = Modifier
                    .size(12.dp)
            )
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(76.dp)
                    .height(31.dp)
                    .background(
                        color = colorResource(R.color.primary_gray),
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Text(
                    text = "Today",
                    color = Color.White,
                    modifier = Modifier
                        .align(alignment = Alignment.Center),
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            }

            Spacer(
                modifier = Modifier
                    .size(12.dp)
            )

            ListView()
        }

//        Box(modifier = Modifier.padding(innerPadding)) {
//            when (selectedIndex) {
//                0 -> IndexScreen()
//                1 -> CalendarScreen()
//                2 -> FocusScreen()
//                3 -> ProfileScreen()
//            }
//        }

    }
}

fun Icon(painter: Unit, contentDescription: String, tint: Color) {

}

@Composable
fun Header(viewModel: ViewModelView) {
    var showPicker = remember { mutableStateOf(false) }
    val bitmap by viewModel.imageBitmap
    Box(
        modifier = Modifier
            .width(42.dp)
            .height(42.dp)
            .clip(CircleShape)
            .background(Color.Gray)
            .clickable {
                showPicker.value = true
            }

    ) {

        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
    }

    if (showPicker.value) {
        ProfileImagePicker(viewModel, showDialog = showPicker)
    }
}

@Composable
fun SearchView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(48.dp)
            .border(
                width = 2.dp,
                color = colorResource(R.color.search_bax),
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
        ) {
            Image(
                painter = painterResource(R.drawable.search_normal),
                contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
            )

            Text(
                text = "Search for your task....",
                color = colorResource(R.color.search_bax),
                modifier = Modifier
                    .padding(12.dp),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
        }
    }
}

@Composable
fun ListView() {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(300.dp)
    ) {
        item {
            NotesCardView { }
        }
    }
}

@Composable
fun SpacerNavItem() {

}

@Composable
fun IndexScreen() {
    Text(
        "Index Screen",
        color = Color.White
    )
}

@Composable
fun CalendarScreen() {
    Text("Calendar Screen")
}

@Composable
fun FocusScreen() {
    Text("Focus Screen")
}

@Composable
fun ProfileScreen() {
    Text("Profile Screen")
}

//@kotlin.OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddNotes(onDismiss: () -> Unit) {
//    ModalBottomSheet(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .background(color = Color.Black),
//        onDismissRequest = onDismiss,
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(color = Color.White)
//        ) {
//            Text(text = "Add Task")
//        }
//    }
//}
@Composable
fun ProfileImagePicker(viewModel: ViewModelView, showDialog: MutableState<Boolean>) {
    val context = LocalContext.current


    val photoUri = remember {
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            File(context.cacheDir, "profile_temp.jpg")
        )
    }
    val cameraImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { bitmap ->
        if (bitmap) {
            viewModel.uriToBitmap(context, photoUri)?.let {
                viewModel.setImage(it)
            }
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        it?.let {
           viewModel.uriToBitmap(context, it)?.let {bmp->
               viewModel.setImage(bmp)
           }
        }
    }

    // permission required
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            cameraImage.launch(photoUri)
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("Select a Image") },
            text = { Text("Choose an option") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            cameraImage.launch(photoUri)
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                ) {
                    Text("Take Photo")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        galleryLauncher.launch("image/*")
                    }) {
                    Text("Choose from Gallery")
                }
            }
        )
    }

}

@Preview
@Composable
fun Preview() {
}