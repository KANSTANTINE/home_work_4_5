package kg.geeks.taskapp5.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.geeks.taskapp5.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): TaskDao
}