package com.pwol.flutter_app_1agas2.database.services

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pwol.flutter_app_1agas2.database.GlobalDatabase
import com.pwol.flutter_app_1agas2.database.faqs.Faq

class FaqsRepository(application: Application) {
    private val faqDao: FaqDao? = GlobalDatabase.getInstance(application)?.faqDao()

    fun insert(faq: Faq) {
        if (faqDao != null) InsertAsyncTask(faqDao).execute(faq)
    }

    fun getFaqs(): LiveData<List<Faq>> {
        return faqDao?.getOrderedFaqs() ?: MutableLiveData<List<Faq>>()
    }

    private class InsertAsyncTask(private val faqDao: FaqDao) :
        AsyncTask<Faq, Void, Void>() {
        override fun doInBackground(vararg faqs: Faq?): Void? {
            for (faq in faqs) {
                if (faq != null) faqDao.insert(faq)
            }
            return null
        }
    }
}