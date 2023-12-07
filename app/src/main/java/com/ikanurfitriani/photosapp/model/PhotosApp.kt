// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.photosapp.model

// Import library yang dibutuhkan
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Kelas data ini mendefinisikan PhotosApp dari jsonplaceholder yang mencakup albumId
 * id, title, URL gambar, dan properti lainnya.
 */
@Serializable
data class PhotosApp(
    @SerialName("albumId")
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String
)
