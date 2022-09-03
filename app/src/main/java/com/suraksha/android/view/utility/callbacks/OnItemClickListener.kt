package com.suraksha.android.view.utility.callbacks
import android.view.View
interface OnItemClickListener {
    fun onItemClick(v: View, obj: Any)
    fun onClick(obj: Any, actionToPerform: OnItemClickActions){}
    fun onClick(actionToPerform: OnItemClickActions){}
    fun onCheckChange(v: View, obj: Any,value:Boolean){}


    enum class OnItemClickActions{
        GO_TO_MOBILE_NUMBER_SCREEN,
        GO_TO_MOBILE_OTP_SCREEN,
        GO_TO_USER_TYPES_SCREEN,
        GO_TO_TEACHER_REG_SCREEN,
        GO_TO_CREATE_CLASS_ROOM,
        GO_TO_CLASS_ROOM_TIMING,
        GO_TO_DASH_BOARD_SCREEN,
        GO_TO_MANAGE_CLASS_SCREEN,
        GO_TO_DRAWER_SCREEN,
        GO_TO_CLASS_DETAILS_SCREEN,
        DRAWER_CANCEL_CLICK,
        GO_TO_STUDENT_REG_SCREEN,
        GO_TO_STUDENT_ENROLLMENT_SCREEN,
        GO_TO_CLASS_ENROLLMENT_SCREEN,
        GO_TO_CLASS_APPROVAL_SCREEN,
        GO_TO_ENROLLED_STUDENTS_SCREEN,
        GO_TO_DASHBOARD_STUDENT_SCREEN,
        GO_TO_LIVE_CLASS_SCREEN,
        APPROVE_ALL_CLICK
    }
}
