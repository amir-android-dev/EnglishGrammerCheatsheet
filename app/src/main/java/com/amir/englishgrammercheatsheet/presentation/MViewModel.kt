package com.amir.englishgrammercheatsheet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amir.englishgrammercheatsheet.data.model.GrammarModel
import com.amir.englishgrammercheatsheet.presentation.objects.GrammerObject

class MViewModel : ViewModel() {

   var title = MutableLiveData<String>()
   var descripton = MutableLiveData<String>()
    var grammar = MutableLiveData<GrammarModel>()


    fun setTitle() {
        var id : Int? = null
        when (id) {
            1 -> {
                title.value = GrammerObject.grammar1.title

            }
            2-> {
                title.value = GrammerObject.grammar2.title

            }
        }
    }
    @JvmName("setGrammar1")
    fun setDEscription() {
        var id : Int? = null
        when (id) {
            1 -> {

                descripton.value = GrammerObject.grammar1.description
            }
            2-> {

                descripton.value = GrammerObject.grammar2.description
            }
        }
    }

}


