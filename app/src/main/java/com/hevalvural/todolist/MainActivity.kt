package com.hevalvural.todolist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hevalvural.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var planList : ArrayList<Plan>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        planList = ArrayList<Plan>()

        getDataFromDatabase()

    }
    fun addButton(view : View){
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("Recycle")
    fun getDataFromDatabase(){
        val database = this.openOrCreateDatabase("List",MODE_PRIVATE,null)

        val cursor = database.rawQuery("SELECT * FROM plans",null)
        val idIx = cursor.getColumnIndex("id")
        val titleIx = cursor.getColumnIndex("title")

        while (cursor.moveToNext()){
            val title = cursor.getString(titleIx)
            val id = cursor.getInt(idIx)
            val plan = Plan(title,id)
            planList.add(plan)
        }

        cursor.close()
    }
}