package com.udacity.shoestore.viemodels

import androidx.lifecycle.*
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {


    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()
    val shoeImages = MutableLiveData<MutableList<String>>()

    private val formattedShoeSize = Transformations.map(shoeSize)
     { size ->
        size.toDoubleOrNull()
    }


    private val _isTaskDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean>
        get() = _isTaskDone

    init {
        _isTaskDone.value = false
        _shoeList.value = mutableListOf()
    }

    fun newTask() {
        _isTaskDone.value = false
    }


    fun onSaveBtnClick() {
        val images = mutableListOf<String>()
        images.add("image_one")

        if (formattedShoeSize.value != null) {
            val shoe =
                    Shoe(
                            shoeName.value.toString(),
                            formattedShoeSize.value!!,
                            shoeCompany.value.toString(),
                            shoeDescription.value.toString(),
                            images
                    )

            _shoeList.value?.add(shoe)
            _isTaskDone.value = true

            Timber.i("shoelistSize:${shoeList.value}")
        } else {
            Timber.i("Shoe size null${formattedShoeSize.value}")
        }
    }


    fun onCancelBtnClick() {
        //go to shoe list fragment using nav direction
        _isTaskDone.value = true
    }

}