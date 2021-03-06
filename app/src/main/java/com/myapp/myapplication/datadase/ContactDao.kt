package com.myapp.myapplication.datadase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts WHERE group_id = :groupId")
    fun getContactsOfGroup(groupId: Int): LiveData<List<Contact>>

    @Query("SELECT COUNT(id) FROM contacts WHERE group_id = :groupId")
    fun getCount(groupId: Int): Int

    @Insert
    fun insert(group: Contact)

    @Update
    fun update(group: Contact)

    @Delete
    fun delete(group: Contact)

}

fun ContactDao.insertOrUpdate(contact: Contact) {
    if (contact.id != null) update(contact)
    else insert(contact)
}