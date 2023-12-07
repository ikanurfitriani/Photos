// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.photosapp.ui.screens

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.photosapp.R
import com.ikanurfitriani.photosapp.ui.theme.PhotosAppTheme

// Fungsi composable yang menampilkan layar utama dari hasil status foto
@Composable
fun HomeScreen(
    photosUiState: PhotosUiState, modifier: Modifier = Modifier
) {
    when (photosUiState) {
        // Jika masih loading maka akan menampilkan LoadingScreen dengan modifikasi penuh ukuran (full size)
        is PhotosUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        // Jika berhasil maka akan menampilkan ResultScreen dengan modifikasi penuh lebar (full width)
        is PhotosUiState.Success -> ResultScreen(
            photosUiState.photos, modifier = modifier.fillMaxWidth()
        )

        // Jika gagal maka akan menampilkan ErrorScreen dengan modifikasi penuh ukuran (full size)
        is PhotosUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

// Fungsi composable yang menampilkan LoadingScreen atau layar loading
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        // Mengatur lebar dan tinggi gambar menjadi 200 * 200 density-independent pixels
        modifier = modifier.size(200.dp),
        // Menampilkan gambar loading dengan menggunakan resource drawable loading_img
        painter = painterResource(R.drawable.loading_img),
        // Untuk menampilkan deskripsi dengan menggunakan sumber daya string
        contentDescription = stringResource(R.string.loading)
    )
}

// Fungsi composable yang menampilkan ErrorScreen atau layar gagal memuat data
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    // Menyusun komponen secara vertikal
    Column(
        // Menyesuaikan tata letak kolom, termasuk ukuran dan penataan
        modifier = modifier,
        // Menyusun elemen secara vertikal dan menempatkannya di tengah vertikal kolom
        verticalArrangement = Arrangement.Center,
        // Menyusun elemen secara horizontal dan menempatkannya di tengah horizontal kolom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            // Menampilkan ikon error dari resource drawable
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        // Menampilkan pesan error dengan string resource dari resource string
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

// Fungsi composable yang menampilkan ResultScreen atau Layar Hasil yang menampilkan jumlah foto yang diambil.
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        // Konten berada ditengah kotak
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        // Menampilkan text
        Text(text = photos)
    }
}

// Tampilan pratinjau untuk fungsi LoadingScreen
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    PhotosAppTheme {
        LoadingScreen()
    }
}

// Tampilan pratinjau untuk fungsi ErrorScreen
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    PhotosAppTheme {
        ErrorScreen()
    }
}

// Tampilan pratinjau untuk fungsi ResultScreen
@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    PhotosAppTheme {
        ResultScreen(stringResource(R.string.placeholder_success))
    }
}