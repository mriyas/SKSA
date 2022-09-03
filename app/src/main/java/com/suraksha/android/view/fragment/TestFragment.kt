package com.suraksha.android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suraksha.app.databinding.FragmentTestBinding

class TestFragment : BaseFragment() {
    override fun onClick(vararg data: Any) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding=FragmentTestBinding.inflate(inflater,container,false)

        return binding.root
    }
}