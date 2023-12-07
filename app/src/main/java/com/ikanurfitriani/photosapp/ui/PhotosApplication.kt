// Memberikan penanda bahwa eksperimental API dari Material 3 telah diaktifkan
@file:OptIn(ExperimentalMaterial3Api::class)

// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.photosapp.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikanurfitriani.photosapp.R
import com.ikanurfitriani.photosapp.ui.screens.HomeScreen
import com.ikanurfitriani.photosapp.ui.screens.PhotosViewModel

// Fungsi composable utama dari aplikasi PhotosApp
@Composable
fun PhotosApplication() {
    // Mendeklarasikan perilaku gulir bawaan
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    // Untuk membuat tata letak aplikasi dari Jetpack Compose
    Scaffold(
        // Untuk mengaktifkan perilaku gulir bersarang
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { PhotosTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                // Modifikasi penuh ukuran (full size)
                .fillMaxSize()
                .padding(it)
        ) {
            // Mendeklarasikan dan menginisialisasi variabel photosViewModel dengan menggunakan fungsi viewModel()
            val photosViewModel: PhotosViewModel = viewModel()
            // Untuk menampilkan layar utama dari aplikasi PhotosApp
            HomeScreen(photosUiState = photosViewModel.photosUiState)
        }
    }
}

// Fungsi composable dari aplikasi PhotosApp untuk menampilkan TopAppBar
@Composable
fun PhotosTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    // Untuk mengatur judul aplikasi agar berada di tengah
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                // Untuk menampilkan teks dengan menggunakan sumber daya string
                text = stringResource(R.string.app_name),
                // Memberikan gaya tipografi headline small pada text
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}