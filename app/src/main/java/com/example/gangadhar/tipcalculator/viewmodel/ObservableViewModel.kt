package com.example.gangadhar.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.example.gangadhar.tipcalculator.BR
import java.util.*

abstract class ObservableViewModel(app: Application) : AndroidViewModel(app), Observable {
    @delegate:Transient
    private val mCallbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }

    fun notifyChange() {
        mCallbacks.notifyChange(this, BR._all)
    }

}