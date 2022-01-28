package com.pwol.flutter_app_1agas2.database.services

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pwol.flutter_app_1agas2.database.GlobalDatabase

class ServicesRepository(application: Application) {
    private val serviceDao: ServiceDao? = GlobalDatabase.getInstance(application)?.serviceDao()

    fun insert(service: Service) {
        if (serviceDao != null) InsertAsyncTask(serviceDao).execute(service)
    }

    fun getServices(): LiveData<List<Service>> {
        return serviceDao?.getOrderedAgenda() ?: MutableLiveData<List<Service>>()
    }

    private class InsertAsyncTask(private val serviceDao: ServiceDao) :
        AsyncTask<Service, Void, Void>() {
        override fun doInBackground(vararg services: Service?): Void? {
            for (contact in services) {
                if (contact != null) serviceDao.insert(contact)
            }
            return null
        }
    }
}