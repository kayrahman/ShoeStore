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

    private val _authenticationState = MutableLiveData<Boolean>()
    val authenticationState : LiveData<Boolean>
    get() = _authenticationState

    private val _isInstructionScreenShown = MutableLiveData<Boolean>()
    val isInstructionScreenShown : LiveData<Boolean>
    get() = _isInstructionScreenShown


    private val _isWelcomeScreenShown = MutableLiveData<Boolean>()
    val isWelcomeScreenShown : LiveData<Boolean>
    get() = _isWelcomeScreenShown



    private val _isTaskDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean>
        get() = _isTaskDone

    init {
        _isTaskDone.value = false
        _shoeList.value = mutableListOf()
        _authenticationState.value = false
        _isInstructionScreenShown.value = false
        _isWelcomeScreenShown.value = false
    }

    fun newTask() {
        _isTaskDone.value = false
    }


    fun onSaveBtnClick() {
        val images = mutableListOf<String>()
        images.add("image_one")

        val formattedShoeSize = shoeSize.value?.toDoubleOrNull()
        if (formattedShoeSize != null) {
            val shoe =
                    Shoe(
                            shoeName.value.toString(),
                            formattedShoeSize,
                            shoeCompany.value.toString(),
                            shoeDescription.value.toString(),
                            images
                    )

            _shoeList.value?.add(shoe)
            _isTaskDone.value = true

            Timber.i("shoelistSize:${shoeList.value}")
        }
    }


    fun onCancelBtnClick() {
        //go to shoe list fragment using nav direction
        _isTaskDone.value = true
    }


    fun onLoginBtnClick(){
        _authenticationState.value = true
    }


    fun logout(){
        _authenticationState.value = false
    }

    fun navigateToShoeListScreen(){
        _isInstructionScreenShown.value = true
    }
    fun navigateToInstructionScreen(){
        _isWelcomeScreenShown.value = true
    }

}