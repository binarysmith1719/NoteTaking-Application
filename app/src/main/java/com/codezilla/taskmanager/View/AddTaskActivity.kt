package com.codezilla.taskmanager.View

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.codezilla.taskmanager.R

class AddTaskActivity : AppCompatActivity() {
    lateinit var editTitle: EditText
    lateinit var editDes: EditText
    lateinit var editTimeStamp: EditText
    val RESULTCODE=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
         editTitle=findViewById(R.id.edit_text_title)
         editDes=findViewById(R.id.exit_text_desc)
         editTimeStamp=findViewById(R.id.edtxTimeStamp)

        val actionBar: ActionBar?
        actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#FF018786"))
        actionBar?.setBackgroundDrawable(colorDrawable)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater=getMenuInflater().inflate(R.menu.note_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                var title:String =editTitle.text.toString()
                var desc:String =editDes.text.toString()
                var timestamp:String =editTimeStamp.text.toString()
//                val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
//                startActivityForResult(intent,ADD_REQUEST_CODE )
                if (title.trim { it <= ' ' }.isEmpty() || desc.trim { it <= ' ' }.isEmpty()||timestamp.trim { it <= ' ' }.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Please insert a Title, Description or TimeStamp",
                        Toast.LENGTH_SHORT
                    ).show()
                    return true
                }
                var intent:Intent=Intent()
                intent.putExtra("EXTRA_TITLE",title)
                intent.putExtra("EXTRA_DES",desc)
                intent.putExtra("EXTRA_TIMESTAMP",timestamp)
                setResult(RESULTCODE,intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}