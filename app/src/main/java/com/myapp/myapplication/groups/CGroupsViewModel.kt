package com.myapp.myapplication.groups


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.myapp.myapplication.datadase.AppDatabase
import com.myapp.myapplication.datadase.CGroup
import com.myapp.myapplication.datadase.CGroupWithContact

class CGroupsViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {
    var groups: MutableLiveData<List<CGroupWithContact>> = MutableLiveData()
    private var database: AppDatabase = AppDatabase.getDatabase(app)

    private var groupsDao = database.getGroupsDao()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private  val job: Job = Job()


    fun requestGroups() {
        launch(Dispatchers.Main) {
            groups.value = withContext(Dispatchers.Default) {
                groupsDao.getAll()
            }
        }
    }


    fun saveGroup(group: CGroup) {
        launch(Dispatchers.Default) {
            groupsDao.insert(group)
        }
        requestGroups()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
