package com.codezilla.taskmanager.View

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codezilla.taskmanager.Adapter.TaskAdapter
import com.codezilla.taskmanager.Model.TaskModel
import com.codezilla.taskmanager.R
import com.codezilla.taskmanager.ViewModel.TaskViewModel
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() ,TaskAdapter.itemClickListener{
//    var txtview: TextView?=null
    lateinit var taskViewModel:TaskViewModel
    var recView: RecyclerView?=null
    var altm:ArrayList<TaskModel> ?= ArrayList<TaskModel>()
    var btnclick: Button?=null
    var ADD_REQUEST_CODE=1

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar?
        actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#FF018786"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        var shpf = getSharedPreferences("MAIN_KEY", MODE_PRIVATE)
        var flag:Boolean=shpf.getBoolean("Logged_In",false)
        Log.d("TAG", "flag ${flag}")

        if(flag==false) {
            var intent: Intent = Intent(this@MainActivity, AuthActivity::class.java)
            finish()
            startActivity(intent)

        }

        auth= FirebaseAuth.getInstance()
        recView=findViewById(R.id.rcView)
        recView!!.setHasFixedSize(true)
        recView!!.layoutManager=LinearLayoutManager(this)

        var taskAdapter= TaskAdapter(this,this@MainActivity,altm!!,)
        recView!!.adapter=taskAdapter

        var str:String=""
        var userid:String?=auth.currentUser?.uid
        if(!(userid.equals("")||userid.equals(null))) {
            taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
            taskViewModel.getAllData().observe(this, Observer()
            {
                altm?.removeAll(altm!!)
                it.forEach { itx ->
                    altm?.add(itx)
                }
//              altm?.addAll(it)
                altm?.reverse()
                taskAdapter.notifyDataSetChanged()
            })
        }
        Log.d("tag", "getAllData")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater=getMenuInflater().inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.adder -> {
                val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
                startActivityForResult(intent,ADD_REQUEST_CODE )
                true
            }
            R.id.log_out->
            {
                auth.signOut()
                val shpf = getSharedPreferences("MAIN_KEY", MODE_PRIVATE)
                val editor = shpf.edit()
                editor.putBoolean("Logged_In", false)
                editor.apply()

                val intent = Intent(this@MainActivity, AuthActivity::class.java)
                finish()
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_REQUEST_CODE && resultCode == 1) {
            val title = data!!.getStringExtra("EXTRA_TITLE")
            val description = data.getStringExtra("EXTRA_DES")
            val timeStamp = data.getStringExtra("EXTRA_TIMESTAMP")
            val task = TaskModel(title,description,timeStamp)
            taskViewModel.AddData(task)
            Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(taskModel: TaskModel,code:Int) {
        if(code==1) {
            var intent = Intent(this@MainActivity, ViewTaskActivity::class.java)
            intent.putExtra("EXTRA_TITLE", "${taskModel.getTitle()}")
            intent.putExtra("EXTRA_DESCRIPTION", "${taskModel.getDescription()}")
            intent.putExtra("EXTRA_TIMESTAMP", "${taskModel.gettimeStamp()}")
            intent.putExtra("EXTRA_ID", "${taskModel.gettaskId()}")
            startActivity(intent)
        }
        else
        {
            taskViewModel.DeleteData(taskModel.gettaskId().toString())
        }
    }

}