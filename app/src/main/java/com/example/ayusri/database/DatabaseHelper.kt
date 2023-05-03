package com.example.ayusri.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object{
        private val DB_NAME = "task"
        private val DB_VERSION = 1
        private val TABLE_NAME = "tasklist"
        private val ID = "id"
        private val TASK_NAME = "taskname"
        private val TASK_DETAILS = "taskdetails"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CRETE TABLE $TABLE_NAME ($ID INTEGER  "
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}