package com.pwol.flutter_app_1agas2.database.services

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pwol.flutter_app_1agas2.database.faqs.Faq


class FaqsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FaqsRepository(application)
    val services = repository.getFaqs()

    fun saveFaq(faq: Faq) {
        repository.insert(faq)
    }
}