package com.myapp.myapplication.datadase

import androidx.room.*

@Dao
interface CGroupDao {

   @Query("SELECT * FROM groups")
    fun getAll() : List<CGroupWithContact>

    @Insert
    fun insert(group: CGroup)

    @Update
    fun update(group: CGroup)

    @Delete
    fun delete(group: CGroup)
}