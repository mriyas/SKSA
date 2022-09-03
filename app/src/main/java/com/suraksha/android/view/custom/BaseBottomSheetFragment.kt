package com.suraksha.android.view.custom

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.suraksha.app.R
import dagger.hilt.android.internal.managers.FragmentComponentManager


open class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    val TAG = javaClass.simpleName
    var alert: AlertDialog? = null
    open fun onOkClick(){
        this.dismissAllowingStateLoss()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    view.viewTreeObserver.removeGlobalOnLayoutListener(this)
                } else {
                    view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
                val dialog = dialog as BottomSheetDialog?
                val bottomSheet =
                        dialog!!.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                val bottomSheetBehavior = bottomSheet.let { BottomSheetBehavior.from(it) }
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
                bottomSheetBehavior?.peekHeight = 0
                bottomSheetBehavior?.setBottomSheetCallback(object :
                        BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_COLLAPSED) dismissAllowingStateLoss()
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    }
                })

            }

        })
    }

    fun showMsg(msg: String?){
        activity?.runOnUiThread(Runnable {
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })
    }
    open fun OkClick(){
        alert?.dismiss()
    }

    fun cancelClick(){
        alert?.dismiss()
    }
    fun dismissKeyboard(){
        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState)
//        dialog.setOnShowListener(object : DialogInterface.OnShowListener{
//            override fun onShow(dialogInterface: DialogInterface?) {
//                val bottomSheetDialog = dialogInterface as BottomSheetDialog?
//                setupFullHeight(bottomSheetDialog!!)
//            }
//        })
//        return dialog
//    }

     fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!)
        val layoutParams: ViewGroup.LayoutParams? = bottomSheet.layoutParams
        val windowHeight = (getWindowHeight() - 200)
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (FragmentComponentManager.findActivity(view?.context) as Activity?)?.getWindowManager()?.getDefaultDisplay()?.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
    fun showToast(msg: String?) {
        activity?.runOnUiThread(Runnable {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        })
    }

}