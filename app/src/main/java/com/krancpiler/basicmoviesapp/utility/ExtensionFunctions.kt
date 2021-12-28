package com.krancpiler.basicmoviesapp.utility

import androidx.fragment.app.Fragment
import com.krancpiler.basicmoviesapp.utility.RegularConstants.PROGRESS_DIALOG_TAG

fun Fragment.showProgress() {
    ProgressDialog().show(this.childFragmentManager, PROGRESS_DIALOG_TAG)
}

fun Fragment.dismissProgress() {
    (this.childFragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG) as ProgressDialog).dismiss()
}