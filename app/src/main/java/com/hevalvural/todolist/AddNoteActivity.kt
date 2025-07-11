package com.hevalvural.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hevalvural.todolist.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun saveButton(view : View){
        val title = binding.titleEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        try {
            val database = this.openOrCreateDatabase("List",MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS plans(id INTEGER PRIMARY KEY, title VARCHAR, description VARCHAR)")
            val sqlString = "INSERT INTO plans (title,description) VALUES (?,?)"
            val statement = database.compileStatement(sqlString)
            statement.bindString(1,title)
            statement.bindString(2,description)
            statement.execute()
        }
        catch (e: Exception){
            e.printStackTrace()
        }

        val intent = Intent(this@AddNoteActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}