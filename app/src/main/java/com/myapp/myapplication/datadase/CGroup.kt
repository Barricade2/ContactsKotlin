package com.myapp.myapplication.datadase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.myapp.myapplication.R

@Entity(tableName = "groups")
class CGroup(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var description: String? = null,
    var color: Int = R.color.colorPrimary
)