package id.ac.unpas.peminjamanbuku.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.peminjamanbuku.model.KumpulanBuku

@Database(entities = [KumpulanBuku::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kumpulanBukuDao(): KumpulanBukuDao
}