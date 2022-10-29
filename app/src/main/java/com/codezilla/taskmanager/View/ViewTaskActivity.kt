package com.codezilla.taskmanager.View

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codezilla.taskmanager.R

class ViewTaskActivity : AppCompatActivity() {
    lateinit var editTitle: TextView
    lateinit var editDes: TextView
    lateinit var editTimeStamp: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)
        editTitle=findViewById(R.id.text_title)
        editDes=findViewById(R.id.text_desc)
        editTimeStamp=findViewById(R.id.text_TimeStamp)

        val intent = intent
        editTitle.setText(intent.getStringExtra("EXTRA_TITLE"))
        editDes.setText(intent.getStringExtra("EXTRA_DESCRIPTION"))
        editTimeStamp.setText(intent.getStringExtra("EXTRA_TIMESTAMP"))
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG","FINISHED")
        finish()
    }
}