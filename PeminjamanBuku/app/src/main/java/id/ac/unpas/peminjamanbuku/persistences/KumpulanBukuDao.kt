package id.ac.unpas.peminjamanbuku.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.peminjamanbuku.model.KumpulanBuku

@Dao
interface KumpulanBukuDao {
    @Query("SELECT * FROM KumpulanBuku")
    fun loadAll(): LiveData<List<KumpulanBuku>>
    @Query("SELECT * FROM KumpulanBuku WHERE id = :id")
    fun find(id: String): KumpulanBuku?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: KumpulanBuku)
    @Delete
    fun delete(item: KumpulanBuku)
}