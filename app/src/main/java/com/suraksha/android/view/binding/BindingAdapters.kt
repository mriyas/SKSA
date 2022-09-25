package com.suraksha.android.view.binding

import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import com.suraksha.app.R


@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("errorText")
fun setError(view: TextInputLayout, error: String?){
    view.error = error
}


@BindingAdapter("app:errorText")
fun showError(editText: EditText, msg:String?){
    editText.error = msg
    val editText: EditText? = editText
    if (editText != null) {
        val background = editText.background
        background?.clearColorFilter()
    }
}

/*@BindingAdapter("onSwitchChecked")
fun onSwitchChecked(view: SwitchCompat, data: TimeTable) {
    if (view.isChecked )
        data.enabled = TimeTable.ENABLED
    else
        data.enabled = TimeTable.DISABLED
}*/

@BindingAdapter("android:profileImage")
fun setProfileImage(imageView: ImageView, profileImage: String?) {
 //TODO change avatar and error image
    if (profileImage != null) {
        Glide.with(imageView.context).load(profileImage)
         //   .placeholder(R.drawable.ic_baseline_account_circle_24)
        //    .error(R.drawable.ic_baseline_account_circle_24)
            .into(imageView)
    }
}

@BindingAdapter("image","placeholder")
fun setImage(image: ImageView, imageUrl: String?, placeHolder: Drawable) {

    if (!imageUrl.isNullOrEmpty()){

        Glide.with(image.context).load(imageUrl).centerCrop()
            .placeholder(placeHolder)
            .into(image)
    }
    else{
        image.setImageDrawable(placeHolder)
    }


}


