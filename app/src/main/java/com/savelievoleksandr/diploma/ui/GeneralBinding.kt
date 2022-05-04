package com.savelievoleksandr.diploma.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class GeneralBinding<Bind : ViewBinding>
    (val bindingLocal: (LayoutInflater) -> Bind) : AppCompatActivity() {
    private var _binding: Bind? = null
    protected val binding: Bind
        get() = _binding as Bind

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingLocal(layoutInflater)
        setContentView(requireNotNull(_binding).root)
    }
}