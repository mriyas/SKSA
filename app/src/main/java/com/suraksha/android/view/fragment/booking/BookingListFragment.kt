package com.suraksha.android.view.fragment.booking

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.suraksha.android.view.adapter.UniversalRecyclerViewAdapter
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view.fragment.lab.LabTestListFragment
import com.suraksha.android.view.fragment.lab.LabTestListFragmentDirections
import com.suraksha.android.view_model.BookingsViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentLabTestListBinding
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.Bookings
import com.suraksha.cloud.model.LabTest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class BookingListFragment : BaseFragment() {

    lateinit var mBinding: FragmentLabTestListBinding
    private val mAdapter = UniversalRecyclerViewAdapter<Bookings>(null, R.layout.rv_bookings)

    private val mLabsViewModel: BookingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onClick(view: View) {
        super.onClick(view)
        when(view?.id){
            R.id.iv_create_test->{
                navigate(LabTestListFragmentDirections.goToAdd(null))
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
        mBinding = FragmentLabTestListBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.clickHelper=this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        mBinding.ivCreateTest.visibility=View.GONE
        mBinding.tvTitle.text=getString(R.string.bookings)

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
        if(test is Bookings){
            navigate(BookingListFragmentDirections.goToDetailsPage(test))
        }
    }

    private fun setRecyclerView() {
        mAdapter.setListener(this)
        mBinding.rvList.adapter = mAdapter
        mLabsViewModel.getBookings(0, 100)
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
                            mAdapter.setData(data as List<Bookings>?)
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
            LabTestListFragment().apply {

            }
    }
}