package com.codezilla.taskmanager.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codezilla.taskmanager.Model.TaskModel
import com.codezilla.taskmanager.R

class TaskAdapter(itemClickListenerRef: itemClickListener ,context: Context,list:ArrayList<TaskModel>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    public var contxt: Context? = null
    private var arr: ArrayList<TaskModel>? = null
    lateinit var listener: itemClickListener
      init {
          contxt=context
          arr=list
          listener=itemClickListenerRef
      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
      var view:View = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklist,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
       var tm: TaskModel? =  arr?.get(position)
        var str:String=tm?.getTitle().toString()
        holder.txtView1.setText(str)
        holder.txtView1.setOnClickListener{
            listener.onItemClick(arr!!.get(position),1)
        }
        holder.imgview.setOnClickListener{
            listener.onItemClick(arr!!.get(position),0)
        }
    }

    override fun getItemCount(): Int {
        return arr!!.size
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var txtView1: TextView
         var imgview : ImageView
         init {
             txtView1= itemView.findViewById<View>(R.id.txttitle) as TextView
             imgview=itemView.findViewById(R.id.imgnew) as ImageView
         }
     }

    interface itemClickListener
    {
        fun onItemClick(taskModel:TaskModel,code:Int)
    }

}