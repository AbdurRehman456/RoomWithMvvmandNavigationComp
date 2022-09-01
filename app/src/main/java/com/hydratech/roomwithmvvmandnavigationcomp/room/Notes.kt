package com.hydratech.roomwithmvvmandnavigationcomp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity ( tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    val title : String,
    val description:String
    ):Serializable{

}