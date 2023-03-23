package id.ac.unpas.peminjamanbuku.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.peminjamanbuku.model.KumpulanBuku
import id.ac.unpas.peminjamanbuku.persistences.AppDatabase

@Composable
fun PengelolaanBukuScreen(){
    val context = LocalContext.current
    val db = Room.databaseBuilder(context, AppDatabase::class.java, "pengelolaan-buku").build()
    val kumpulanBukuDao = db.kumpulanBukuDao()

    val list : LiveData<List<KumpulanBuku>> = kumpulanBukuDao.loadAll()
    val items : List<KumpulanBuku> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPeminjamanBuku(kumpulanBukuDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(17.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama Peminjam", fontSize = 16.sp,fontWeight = FontWeight.Bold)
                        Text(text = item.nama_pj, fontSize = 14.sp)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama Buku", fontSize = 16.sp,fontWeight = FontWeight.Bold)
                        Text(text = item.nama_bk, fontSize = 14.sp)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Kode Pinjaman", fontSize = 16.sp,fontWeight = FontWeight.Bold)
                        Text(text = item.kode, fontSize = 14.sp)
                    }
                }
                Row(modifier = Modifier
                    .padding(17.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Peminjaman", fontSize = 16.sp,fontWeight = FontWeight.Bold)
                        Text(text = item.tgl_pj, fontSize = 14.sp)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Pengembalian", fontSize = 16.sp,fontWeight = FontWeight.Bold)
                        Text(text = item.tgl_dl, fontSize = 14.sp)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}