package com.codezilla.taskmanager.Repository

import android.util.Log
import com.codezilla.taskmanager.Model.TaskModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class Repository {
    private var onFirestoreTaskCompletex: onFirestoreTaskComplete? = null
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val fbauth: FirebaseAuth =FirebaseAuth.getInstance()
    constructor(onFirestoreTaskComplete: onFirestoreTaskComplete?)
    {
        this.onFirestoreTaskCompletex=onFirestoreTaskComplete
    }
    public fun getModelData()
    {
        var listTaskModelx:ArrayList<TaskModel> = ArrayList<TaskModel>()
        firebaseFirestore.collection(fbauth.currentUser?.uid.toString()).get().addOnCompleteListener {
            if(it.isSuccessful)
            {
                for(document in it.result)
                {
                    var title:String? = document.data.getValue("title")  as String?
                    var description:String? = document.data.getValue("description")  as String?
                    var timeStamp:String? = document.data.getValue("timeStamp")  as String?
                    var taskId:String? = document.data.getValue("taskId")  as String?

                    Log.d("tag", "Task = ${title} + ${description} + ${timeStamp}")

                    var tm=TaskModel(taskId,title,description,timeStamp)
                    listTaskModelx?.add(tm)
                }
                onFirestoreTaskCompletex?.TaskDataLoaded(listTaskModelx)
                //it.getResult().toObjects(TaskModel::class.java)
            }
            else { Log.d("tag", "Database Empty") }
        }
    }

    public fun addModelData(task: TaskModel)
    {
        var latestId:Int=0
        firebaseFirestore.collection("Available_Id").document("Id_count").get().addOnCompleteListener {

            latestId=Integer.parseInt(it.result.data?.getValue("count") as String)
            var hashmap:HashMap<String,String> = HashMap<String,String>()
            hashmap.put("count","${latestId+1}")
            firebaseFirestore.collection("Available_Id").document("Id_count").set(hashmap).addOnSuccessListener {  }

            //ADDING CURRENT DATE
            var gotCalender= Calendar.getInstance()
            var day =gotCalender.get(Calendar.DATE)
            var month= gotCalender.get(Calendar.MONTH)
            var year = gotCalender.get(Calendar.YEAR)
            var dayMonthYear:String="${day}/${month}/${year}"

            task.settaskId("${latestId+1}")
            val setTask = hashMapOf(
                "description" to "${task.getDescription()}",
                "timeStamp" to "${task.gettimeStamp()}",
                "title" to "${task.getTitle()}",
                "taskId" to "${task.gettaskId()}",
                "autoDate" to  dayMonthYear
                )

            firebaseFirestore.collection(fbauth.currentUser?.uid.toString())
                .document("${latestId+1}").set(setTask)
                .addOnSuccessListener { getModelData() }
                .addOnFailureListener{Log.d("tag", "Data Not Added")}
        }

    }
    fun deleteData(taskId:String)
    {
        Log.d("DEL", "Database Empty")

        firebaseFirestore.collection(fbauth.currentUser?.uid.toString())
            .document(taskId).delete().addOnSuccessListener { getModelData() }
    }


    interface onFirestoreTaskComplete {
        fun TaskDataLoaded(TaskListModels: List<TaskModel?>?)
        fun onError(e: Exception?)
    }
}