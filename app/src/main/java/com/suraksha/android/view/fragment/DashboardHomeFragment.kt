package com.suraksha.android.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentDashboardHomeBinding


class DashboardHomeFragment : Fragment() {

    private lateinit var binding: FragmentDashboardHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDashboardHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nestedNavHostFragment = childFragmentManager.findFragmentById(R.id.nestedNavHostFragment) as NavHostFragment
              val navController: NavController = nestedNavHostFragment.navController
        val bottomNavigationView: BottomNavigationView =
           binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardHomeFragment().apply {

            }
    }
}