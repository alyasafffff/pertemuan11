package com.example.room_persistence

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.room_persistence.room.MainViewModel
import com.example.room_persistence.room.NoteAdapter
import com.example.room_persistence.ui.AddNoteActivity

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Mengatur padding untuk menyesuaikan dengan sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerViewNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi ViewModel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Mengamati LiveData dari ViewModel
        mainViewModel.allNotes.observe(this, { notes ->
            noteAdapter = NoteAdapter(notes)
            recyclerView.adapter = noteAdapter
        })

        // Menambahkan fungsi untuk tombol "Add Note"
        val buttonAddNote: Button = findViewById(R.id.buttonAddNote)
        buttonAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}
j.