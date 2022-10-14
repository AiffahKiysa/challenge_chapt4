package com.example.challengechapter4_revisi.dao

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val id_notes : Int,
    val title : String,
    val content : String,
) : Parcelable