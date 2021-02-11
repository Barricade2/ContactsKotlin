package com.myapp.myapplication.datadase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "contacts"
)
class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "group_id")
    var groupId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "email")
    var email: String? = null,
    @ColumnInfo(name = "phone")
    var phone: String? = null,
    @ColumnInfo(name = "comment")
    var comment: String? = null,
    @ColumnInfo(name = "photo_url")
    var photoUrl: String? = null,
    @ColumnInfo(name = "facebookid")
    var facebookId: String? = null
)