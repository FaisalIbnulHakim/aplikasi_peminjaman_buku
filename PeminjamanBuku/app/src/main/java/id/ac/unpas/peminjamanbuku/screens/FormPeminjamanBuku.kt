package id.ac.unpas.peminjamanbuku.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.peminjamanbuku.model.KumpulanBuku
import id.ac.unpas.peminjamanbuku.persistences.KumpulanBukuDao
import id.ac.unpas.peminjamanbuku.ui.theme.Purple700
import id.ac.unpas.peminjamanbuku.ui.theme.Red700
import id.ac.unpas.peminjamanbuku.ui.theme.Teal200
import id.ac.unpas.peminjamanbuku.ui.theme.White200
import kotlinx.coroutines.launch

@Composable
fun FormPeminjamanBuku(kumpulanBukuDao: KumpulanBukuDao) {
    val tgl_pj = remember { mutableStateOf(TextFieldValue("")) } //tanggal pinjaman
    val nama_pj = remember { mutableStateOf(TextFieldValue("")) } //nama peminjam
    val nama_bk = remember { mutableStateOf(TextFieldValue("")) } //nama dari buku
    val kode = remember { mutableStateOf(TextFieldValue("")) } //no kode buku
    val tgl_dl = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Tanggal Pinjaman") },
            value = tgl_pj.value,
            onValueChange = {
                tgl_pj.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama Peminjam") },
            value = nama_pj.value,
            onValueChange = {
                nama_pj.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama Buku") },
            value = nama_bk.value,
            onValueChange = {
                nama_bk.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Kode Pinjaman") },
            value = kode.value,
            onValueChange = {
                kode.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "00000") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Pengembalian") },
            value = tgl_dl.value,
            onValueChange = {
                tgl_dl.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = KumpulanBuku(
                    id, tgl_pj.value.text,
                    nama_pj.value.text, nama_bk.value.text, kode.value.text, tgl_dl.value.text
                )
                scope.launch {
                    kumpulanBukuDao.insertAll(item)
                }
                tgl_pj.value = TextFieldValue("")
                nama_pj.value = TextFieldValue("")
                nama_bk.value = TextFieldValue("")
                kode.value = TextFieldValue("")
                tgl_dl.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tgl_pj.value = TextFieldValue("")
                nama_pj.value = TextFieldValue("")
                nama_bk.value = TextFieldValue("")
                kode.value = TextFieldValue("")
                tgl_dl.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}