    package com.maad.notesapp.ui
    
    import android.app.Application
    import androidx.lifecycle.AndroidViewModel
    import com.maad.notesapp.database.Note
    import com.maad.notesapp.database.RoomDBHelper

    class NoteViewModel(app: Application) : AndroidViewModel(app) {

        private val db: RoomDBHelper = RoomDBHelper.getInstance(app)

        fun upsertNote(note: Note) {
            db.noteDao().upsertNote(note)
        }

        fun deleteNote(note: Note) {
            db.noteDao().deleteNote(note)
        }

        fun getNotes() = db.noteDao().getAllNotes()

    }