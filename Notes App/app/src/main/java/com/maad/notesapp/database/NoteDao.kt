    package com.maad.notesapp.database

    import androidx.room.Dao
    import androidx.room.Delete
    import androidx.room.Query
    import androidx.room.Upsert
    import kotlinx.coroutines.flow.StateFlow

    @Dao
    interface NoteDao {

        @Upsert
        fun upsertNote(note: Note)

        @Query("SELECT * FROM note")
        fun getAllNotes(): StateFlow<List<Note>>

        @Delete
        fun deleteNote(note: Note)

    }