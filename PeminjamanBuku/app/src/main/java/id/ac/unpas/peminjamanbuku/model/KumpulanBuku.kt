package id.ac.unpas.peminjamanbuku.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KumpulanBuku (
    @PrimaryKey val id: String,
    val tgl_pj: String, //tanggal pinjaman
    val nama_pj: String, //nama peminjam
    val nama_bk: String, //nama dari buku
    val kode: String, //no kode buku
    val tgl_dl: String //tanggal deadline / pengumpulan
)