package com.suraksha.android.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suraksha.app.R

import com.suraksha.android.view_model.BaseViewModel
import com.suraksha.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SurakshaLaunchActivity : AppCompatActivity(){
   lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private val viewModel: BaseViewModel by viewModels()
    private var backPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.activity=this
        binding?.vm = viewModel
        binding?.lifecycleOwner = this

        navController = findNavController(R.id.nav_host_fragment)

       // val navView: BottomNavigationView = binding.bottomNavView
        // Hook your navigation controller to bottom navigation view
       // navView.setupWithNavController(navController)


        val token =viewModel.getToken()

        val bundle = intent.extras
        val isDeeplinked = false //bundle?.getBoolean(SurakshaAppConstants.BUNDLE_KEY_IS_DEEP_LINKED)


// with deep linking
       if(isDeeplinked==true)
       {
       /*  when(destination) {
              R.id.classEnrollmentScreen-> {
                 // Toast.makeText(this, "class room id : $classRoomId", Toast.LENGTH_LONG).show()
                  var bundle = bundleOf(SurakshaAppConstants.BUNDLE_KEY_CLASS_ID to classRoomId)
                  destination?.let { navController?.navigate(it, bundle) }
              }
             R.id.dashboardFragment-> navController?.navigate(destination)
             R.id.userMobileInputFragment-> navController?.navigate(destination)

           }*/
       }
        // without deep link
        else {
          /*  val destination:Int
            if (token?.isNullOrEmpty() == true || token == null)
                 destination = R.id.userMobileInputFragment
            else
            {
                if(viewModel.getUserType()==UserType.TEACHER)
                    destination = R.id.dashboardFragment
                else
                    destination = R.id.dashboardStudentFragment
            }
            navController?.navigate(destination)*/
        }

    }
    override fun onBackPressed() {
        val fragment =getCurrentFragment()

        super.onBackPressed()
    }

    fun getCurrentFragment(): Fragment? {
        val mNavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val currentFragment = mNavHostFragment?.childFragmentManager?.fragments?.get(0)
        return currentFragment
    }



}
