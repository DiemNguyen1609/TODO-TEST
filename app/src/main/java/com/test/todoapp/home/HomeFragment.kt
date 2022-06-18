package com.test.todoapp.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.test.common.BaseFragment
import com.test.common.BaseViewModel
import com.test.domain.entities.Status
import com.test.todoapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun initControl() {

    }

    override fun initUI() {
        viewModel.getCallList()
    }

    override fun initEvent() {
        with(viewModel) {
            callListLiveData.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let { discoverResult ->
                    when (discoverResult.responseType) {
                        Status.SUCCESSFUL -> {
                            discoverResult.data?.let {
                                Log.wtf("DATA - RESPONSE", it.callResults[0].name)
                            }
                        }
                        Status.ERROR -> {

                        }
                        Status.LOADING -> {

                        }
                    }

                }
            })
        }
    }

    override fun initConfig() {

    }

}