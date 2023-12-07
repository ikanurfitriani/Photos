// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.photosapp.ui.screens

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikanurfitriani.photosapp.network.PhotosApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Sebuah interface untuk merepresentasikan status UI dalam aplikasi foto
sealed interface PhotosUiState {
    // Ketika pengambilan data foto berhasil terdapat informasi jumlah foto yang berhasil diambil
    data class Success(val photos: String) : PhotosUiState
    // Ketika pengambilan data foto gagal terdapat informasi gagal
    object Error : PhotosUiState
    // Ketika pengambilan data foto loading terdapat informasi loading atau masih memuat data
    object Loading : PhotosUiState
}

// Sebuah kelas ViewModel sebagai bagian dari arsitektur MVVM untuk mengelola logika bisnis dan status UI aplikasi foto
class PhotosViewModel : ViewModel() {
    // Status yang dapat diubah yang menyimpan status permintaan terbaru
    var photosUiState: PhotosUiState by mutableStateOf(PhotosUiState.Loading)
        private set

    // Panggil getPhotosApp() pada init agar kami dapat segera menampilkan status.
    init {
        getPhotosApp()
    }

    // Mendapatkan informasi foto dari layanan Retrofit Photos API dan memperbaruinya
    fun getPhotosApp() {
        // Untuk melakukan pemanggilan ke layanan API secara asynchronous
        viewModelScope.launch {
            // Memperbarui status UI menjadi Loading sebelum memulai pengambilan data
            photosUiState = PhotosUiState.Loading
            photosUiState = try {
                val listResult = PhotosApi.retrofitService.getPhotos()
                // Jika berhasil, status UI diubah menjadi Success dengan informasi jumlah foto yang berhasil diambil
                PhotosUiState.Success(
                    "Success: ${listResult.size} Photos from JSONPlaceholder.com retrieved"
                )
            // Jika terjadi IOException, status UI diubah menjadi Error
            } catch (e: IOException) {
                PhotosUiState.Error
            // Jika terjadi HttpException, status UI diubah menjadi Error
            } catch (e: HttpException) {
                PhotosUiState.Error
            }
        }
    }
}