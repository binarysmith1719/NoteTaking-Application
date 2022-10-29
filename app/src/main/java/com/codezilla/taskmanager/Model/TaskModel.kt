package com.codezilla.taskmanager.Model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

class TaskModel {
//    @DocumentId
    private var taskId: String? = null
    private var title: String? = null
    private var description:String?=null
    private var timeStamp:String?=null

    constructor(taskId:String? , title:String?, description:String?, timeStamp:String?)
    {
        this.taskId=taskId
        this.title=title
        this.description=description
        this.timeStamp=timeStamp
    }
    constructor( title:String?, description:String?, timeStamp:String?)
    {
        this.title=title
        this.description=description
        this.timeStamp=timeStamp
    }
    constructor()
    {}

    fun gettaskId(): String? {
        return taskId
    }

    fun settaskId(taskId: String?) {
        this.taskId = taskId
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description=description
    }
    fun gettimeStamp(): String? {
        return timeStamp
    }

    fun settimeStamp(timeStamp: String?) {
        this.timeStamp=timeStamp
    }

//    fun getDifficulty(): String? {
//        return difficulty
//    }
//
//    fun setDifficulty(difficulty: String) {
//        difficulty = difficulty
//    }
//
//    fun getQuestions(): Long {
//        return questions
//    }

//    fun setQuestions(questions: Long) {
//        this.questions = questions
//    }

//    fun QuizListModel() {}
//    fun QuizListModel(
//        quizId: String?,
//        title: String?,
//        image: String,
//        difficulty: String,
//        questions: Long
//    ) {
//        this.quizId = quizId
//        this.title = title
//        image = image
//        difficulty = difficulty
//        this.questions = questions
//    }
}