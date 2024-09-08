package br.edu.satc.todolistcompose.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.data.Task
import br.edu.satc.todolistcompose.data.TaskDao
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao: TaskDao = AppDatabase.getDatabase(application).taskDao()
    val tasks: LiveData<List<Task>> = taskDao.getAllTasks()

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            val task = Task(title = title, description = description, complete = false)
            taskDao.insert(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.update(task)
        }
    }
}
