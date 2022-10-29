package com.codezilla.taskmanager.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codezilla.taskmanager.Model.TaskModel
import com.codezilla.taskmanager.Repository.Repository

class TaskViewModel: ViewModel(), Repository.onFirestoreTaskComplete{

    //USING LIVEDATA
    private var taskListLiveData = MutableLiveData<List<TaskModel>>()
    private var repository: Repository = Repository(this)

    fun getAllData() :MutableLiveData<List<TaskModel>>{
        repository.getModelData();
        return  taskListLiveData
//        taskListLiveData.setValue(repository.getModelData())
    }
    fun AddData(task:TaskModel) {
        repository.addModelData(task)
//        taskListLiveData.setValue(repository.getModelData())
    }
    fun DeleteData(taskId:String) {
        repository.deleteData(taskId)
    }
    override fun TaskDataLoaded(taskListModels: List<TaskModel?>?) {
        taskListLiveData.setValue(taskListModels as List<TaskModel>?)
    }
    override fun onError(e: Exception?) {
//        Log.d("tag", "Error")
    }


}