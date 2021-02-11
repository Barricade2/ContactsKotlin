package com.myapp.myapplication.datadase

import androidx.room.Embedded
import androidx.room.Relation

class CGroupWithContact {
    @Embedded
    var group: CGroup? = null
    @Relation(parentColumn = "id", entityColumn = "group_id", entity = Contact::class)
    var contacts: List<Contact> = listOf()
}