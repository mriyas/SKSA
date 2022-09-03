package com.suraksha.android.view.utility

import android.content.Context
import com.suraksha.app.R
import java.util.regex.Pattern

open class Validator(context: Context?) {
    var mContext: Context?;

    init {
        mContext = context;
    }

   fun isValidMobile(mobileNumber: String?): Boolean {
        return if (!Pattern.matches("[a-zA-Z]+", mobileNumber.toString())) {
            mobileNumber.toString().length in 5..10
        } else false
    }

    fun isValidUserName(
        userName: String?,
    ): String? {
        var errorMSg: String? = null
        if (isNullOrEmpty(userName)|| userName.toString().length<=2) {
            errorMSg = mContext?.getString(R.string.invalid_user_name)
            return errorMSg;
        }
        return errorMSg;
    }



    fun validateUserNameLength(userName: String?, isFromLoginPage: Boolean): Boolean {
        var maxLength = 15
        var minLength = 3
        if (isFromLoginPage) {
            maxLength = 31
            minLength = 7
        }
        return isValidLength(userName, minLength, maxLength);
    }




    fun userNameContainsSpecialCharacter(value: String?, isFromLoginPage: Boolean): Boolean {
        return hasSpecialChar(value, isFromLoginPage)
    }


    fun hasSpecialChar(value: String?, isFromLoginPage: Boolean = false): Boolean {
        var p = Pattern.compile("[^a-z0-9A-Z._-\\u00c0-\\u017e ]", Pattern.CASE_INSENSITIVE)
        if (isFromLoginPage)
            p = Pattern.compile("[^a-z0-9A-Z._-\\u00c0-\\u017e@ ]", Pattern.CASE_INSENSITIVE)
        val m = p.matcher(value)
        val b = m.find()
        return b
    }

    fun userNameContainsSpace(value: String?): Boolean {
        return value!!.contains(" ")
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }
    private fun containsAtleastOneAlphabet(s: String?): Boolean {

        if (s != null) {
            return s.matches(".*[a-zA-Z]+.*".toRegex())
        }
        return false
    }

    fun hasSpace(value: String?): Boolean {
        if (value != null) {
            return value.contains(" ")
        }
        return true
    }

    private fun hasNumericValues(value: String?): Boolean {
        if (value != null) {
            return value.matches(".*\\d+.*".toRegex())
        }
        return true
    }

    fun validateUserPasswordLength(password: String?): Boolean {
        var maxLength = 30
        var minLength = 6

        return isValidLength(password, minLength, maxLength);
    }

    fun isValidLength(value: String?, minLength: Int, maxLength: Int): Boolean {

        if (value != null) {
            if (value.length < minLength || value.length > maxLength) {
                return false;
            }
        }
        return true;
    }


    private fun hasSpecialCharacters(value: String?): Boolean {
        var p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val m = p.matcher(value)
        val b = m.find()
        return b
    }
    fun isValidEmail(target: CharSequence?): Boolean {
        val emailPattern =
            "^([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22))*\\x40([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d))*\$"
        return target?.toString()?.matches(emailPattern.toRegex()) ?: false
    }
}