package com.suraksha.android.view.fragment.time_slot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentBookingsHomeBinding
import com.suraksha.app.databinding.FragmentTimeSlotHomeBinding


class TimeSlotHomeFragment : BaseFragment() ,
NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: FragmentTimeSlotHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTimeSlotHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureToolbar()
        setupDrawerLayout()
        setActionBar("LAB NAME",true)

    }


    override fun onClick(vararg data: Any) {
    }

    private fun configureToolbar() {
        val toolbar = binding.mToolbar as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

    }


    private fun setupDrawerLayout() {
        val nestedNavHostFragment = childFragmentManager.findFragmentById(R.id.nestedNavHostFragment) as NavHostFragment
        val navController: NavController = nestedNavHostFragment.navController
        NavigationUI.setupWithNavController(binding.mToolbar,
            navController, binding.drawerLayout)

        NavigationUI.setupWithNavController(binding.navigationView, navController);

        binding.navigationView.setNavigationItemSelectedListener(this);
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimeSlotHomeFragment().apply {

            }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showSnackBar("Selected : ${item.title}")
        binding.drawerLayout.closeDrawers()
        return false
    }
}