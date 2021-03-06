package com.krancpiler.basicmoviesapp.utility

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.krancpiler.basicmoviesapp.MainActivity
import com.krancpiler.basicmoviesapp.ui.dialogs.NoConnectionDialog
import com.krancpiler.basicmoviesapp.ui.dialogs.ProgressDialog
import com.krancpiler.basicmoviesapp.ui.dialogs.SimpleMessageDialog
import com.krancpiler.basicmoviesapp.utility.RegularConstants.NETWORK_ERROR_ARRAY_VALUE
import com.krancpiler.basicmoviesapp.utility.RegularConstants.NETWORK_ERROR_GENERATE_SESSION
import com.krancpiler.basicmoviesapp.utility.RegularConstants.NO_CONNECTION_DIALOG_TAG
import com.krancpiler.basicmoviesapp.utility.RegularConstants.PROGRESS_DIALOG_TAG
import com.krancpiler.basicmoviesapp.utility.RegularConstants.SIMPLE_MESSAGE_DIALOG_TAG
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

// Fragments

fun Fragment.showProgress() {
    ProgressDialog().show(this.childFragmentManager, PROGRESS_DIALOG_TAG)
}

fun Fragment.dismissProgress() {
    (childFragmentManager.findFragmentByTag(PROGRESS_DIALOG_TAG) as ProgressDialog?)?.dismiss()
}

fun Fragment.showSimpleMessageDialog(message: String) {
    val dialog = SimpleMessageDialog().newInstance(message.replaceFirstChar { it.uppercase() })
    dialog.show(this.childFragmentManager, SIMPLE_MESSAGE_DIALOG_TAG)
}

fun Fragment.changeToolbarTitle(title: String) {
    val activity = requireActivity() as MainActivity
    activity.changeToolbarTitle(title)
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.checkNetworkConnectivity(): Boolean {
    val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return capabilities != null
}

fun Fragment.showNoConnectionDialog() {
    val activity = requireActivity() as MainActivity
    NoConnectionDialog(activity).show(childFragmentManager, NO_CONNECTION_DIALOG_TAG)
}

// View Models

fun ViewModel.getErrorMessageFromListRequest(errorBody: ResponseBody): String {
    val jsonObject = JSONObject(errorBody.string())
    val errorsArray: JSONArray = jsonObject.getJSONArray(NETWORK_ERROR_ARRAY_VALUE)
    return errorsArray.get(0).toString()
}

fun ViewModel.getErrorMessageFromRequests(errorBody: ResponseBody): String {
    val jsonObject = JSONObject(errorBody.string())
    return jsonObject.getString(NETWORK_ERROR_GENERATE_SESSION)
}

// Strings

fun String.getYearFromString(): String {
    return this.substring(range = IntRange(0, 3))
}