package com.suraksha.android.view.fragment.pharmacy

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.suraksha.android.view.adapter.UniversalRecyclerViewAdapter
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view_model.LabsViewModel
import com.suraksha.android.view_model.MedicinesViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentLabTestListBinding
import com.suraksha.app.databinding.FragmentMedicineListBinding
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.Medicines
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MedicineListFragment : BaseFragment() {

    lateinit var mBinding: FragmentMedicineListBinding
    private val mAdapter = UniversalRecyclerViewAdapter<Medicines>(null, R.layout.rv_medicines)

    private val mLabsViewModel: MedicinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onClick(view: View) {
        super.onClick(view)
        when(view?.id){
            R.id.iv_create_test->{
                //navigate(MedicineListFragmentDirections.goToAdd(null))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu_labs, menu)
        val myActionMenuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.getActionView() as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Toast like print
                showSnackBar("SearchOnQueryTextSubmit: $query")

                myActionMenuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                mAdapter.filter.filter(query)
                return false
            }
        })


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentMedicineListBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.clickHelper=this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread(Runnable {
                    setActionBar("LAB NAME",true)

                })

            }

        },100)

    }

    override fun onClick(vararg data: Any) {
        val test=data[0]
        if(test is Medicines){
            navigate(MedicineListFragmentDirections.goMedicineDetailPage(test))
        }
    }

    private fun setRecyclerView() {
        mAdapter.setListener(this)
        mBinding.rvList.adapter = mAdapter
        mLabsViewModel.getMedicines(0, 100)
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launch {

            mLabsViewModel.apiState.collect {

                // When state to check the
                // state of received data
                when (it.status) {

                    ApiState.Status.LOADING -> {
                        // hideKeyboard()
                    }
                    ApiState.Status.SUCCESS -> {
                        // showSnackBar("Login Success")
                        val data = it.data
                        if (data is List<*>) {
                            mAdapter.setData(data as List<Medicines>?)
                        }
                    }
                    ApiState.Status.ERROR -> {
                        // showSnackBarError(it.error?.errorMessage)

                    }
                    ApiState.Status.IDLE -> {

                    }

                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MedicineListFragment().apply {

            }
    }
}