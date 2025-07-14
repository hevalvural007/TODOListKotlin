package com.hevalvural.todolist

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hevalvural.todolist.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var planTitle : String
    private lateinit var planDescription : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

         planTitle = intent.getStringExtra("planTitle") ?: ""
         planDescription = intent.getStringExtra("planDescription") ?: ""

        binding.titleEditText.setText(planTitle)
        binding.descriptionEditText.setText(planDescription)
    }

    fun deleteButton(view: View) {
        val planId = intent.getIntExtra("planId", -1)

        if (planId != -1) {
            try {
                val database = this.openOrCreateDatabase("List", MODE_PRIVATE, null)
                database.execSQL("DELETE FROM plans WHERE id = ?", arrayOf(planId))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val intent = Intent(this@DetailsActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    fun saveAgain(view: View) {
        planTitle = binding.titleEditText.text.toString()
        planDescription = binding.descriptionEditText.text.toString()

        val database = this.openOrCreateDatabase("List", MODE_PRIVATE, null)
        val planId = intent.getIntExtra("planId", -1)

        if (planId != -1) {
            val sqlString = "UPDATE plans SET title = ?, description = ? WHERE id = ?"
            val statement = database.compileStatement(sqlString)
            statement.bindString(1, planTitle)
            statement.bindString(2, planDescription)
            statement.bindLong(3, planId.toLong())
            statement.execute()
        }
        val intent = Intent(this@DetailsActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


}
