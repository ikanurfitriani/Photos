// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.photosapp.network

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.photosapp.model.PhotosApp
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

// Deklarasi variabel konstan dengan nama BASE_URL yang berisi URL dasar untuk API
private const val BASE_URL =
    "https://jsonplaceholder.typicode.com/"

// Menggunakan pembuat Retrofit untuk membuat objek retrofit menggunakan konverter kotlinx.serialization
private val retrofit = Retrofit.Builder()
    // Menambahkan konverter dari Kotlin Serialization ke tipe media JSON
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    // Menentukan URL dasar untuk seluruh permintaan API
    .baseUrl(BASE_URL)
    // Menghasilkan objek Retrofit yang sudah dikonfigurasi
    .build()

// Retrofit objek layanan untuk membuat panggilan api
interface PhotosApiService {
    // Metode ini akan digunakan untuk permintaan HTTP GET ke endpoint "photos" di URL dasar
    @GET("photos")
    // Mendeklarasikan fungsi yang mengembalikan daftar objek PhotosApp secara asinkron
    suspend fun getPhotos(): List<PhotosApp>
}

// Objek Api publik yang mengekspos layanan Retrofit yang diinisialisasi lambat
object PhotosApi {
    val retrofitService: PhotosApiService by lazy {
        retrofit.create(PhotosApiService::class.java)
    }
}