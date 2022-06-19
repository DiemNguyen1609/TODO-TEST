package com.test.common

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.test.common.view.LoadingDialog

abstract class BaseFragment : Fragment() {

    private var loadingDialog: LoadingDialog? = null

    val fManager: FragmentManager by lazy {
        requireActivity().supportFragmentManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControl()
        initUI()
        initEvent()
        initConfig()



        with(getViewModel()) {

            errorLiveData.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let {
                    showAlertDialog(
                        title = it,
                        content = "",
                        confirmButtonTitle = "OK",
                        confirmCallback = {},
                        cancelButtonTitle = "",
                        cancelCallback = {}
                    )
                }
            })
        }
    }

    open fun showAlertDialog(
        title: String,
        content: String,
        confirmButtonTitle: String,
        cancelButtonTitle: String,
        confirmCallback: () -> Unit,
        cancelCallback: () -> Unit
    ) {
        val builder: AlertDialog.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AlertDialog.Builder(requireContext())
            } else {
                AlertDialog.Builder(requireContext())
            }

        val dialog = builder
            .setTitle(title)
            .setMessage(content)
            .setPositiveButton(confirmButtonTitle) { dialog, _ ->
                confirmCallback.invoke()
            }
            .setNegativeButton(cancelButtonTitle) { _, _ ->
                cancelCallback.invoke()
            }
            .show()

        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

        positiveButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.blue
            )
        )
        negativeButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.red
            )
        )
    }

    open fun showAlertDialog(
        content: String
    ) {
        val builder: AlertDialog.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AlertDialog.Builder(requireContext())
            } else {
                AlertDialog.Builder(requireContext())
            }

        val dialog = builder
            .setTitle(getString(R.string.notification))
            .setMessage(content)
            .setPositiveButton(getString(R.string.close)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()

        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)

        positiveButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.blue
            )
        )
    }

    abstract fun getViewModel(): BaseViewModel

    protected abstract fun initControl()

    protected abstract fun initUI()

    protected abstract fun initEvent()

    protected abstract fun initConfig()

    fun navigate(navDirection: NavDirections, navOptions: NavOptions? = null) {
        navOptions?.let {
            this.findNavController().navigate(navDirection, it)
        } ?: kotlin.run {
            this.findNavController().navigate(navDirection)
        }
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun backToPrevious() {
        this.findNavController().popBackStack()
    }

    fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(context)
            loadingDialog?.showLoadingDialog()
        } else {
            loadingDialog?.showLoadingDialog()
        }
    }

    fun hideLoadingDialog() {
        loadingDialog?.hideLoadingDialog()
    }

}