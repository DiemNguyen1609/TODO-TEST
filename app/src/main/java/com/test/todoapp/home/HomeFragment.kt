package com.test.todoapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.test.common.BaseFragment
import com.test.common.BaseViewModel
import com.test.common.extension.setSafeOnClickListener
import com.test.domain.entities.BuyItemResult
import com.test.domain.entities.Status
import com.test.todoapp.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal

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

        btnCallList.setSafeOnClickListener {
            viewModel.getCallList()
        }
        btnBuyList.setSafeOnClickListener {
            viewModel.getBuyList()
        }
        btnSellList.setSafeOnClickListener {
            viewModel.getCallList()
        }
    }

    override fun initUI() {

    }

    override fun initEvent() {
        with(viewModel) {
            callListLiveData.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let { discoverResult ->
                    when (discoverResult.responseType) {
                        Status.SUCCESSFUL -> {
                            hideLoadingDialog()
                            discoverResult.data?.let {
                                navigate(
                                    HomeFragmentDirections.actionHomeFragmentToCallListFragment(
                                        Gson().toJson(it.callResults)
                                    )
                                )
                            }
                        }
                        Status.ERROR -> {
                            hideLoadingDialog()
                        }
                        Status.LOADING -> {
                            showLoadingDialog()
                        }
                    }

                }
            })
            buyListLiveData.observe(viewLifecycleOwner, Observer {
                it.getContentIfNotHandled()?.let { discoverResult ->
                    when (discoverResult.responseType) {
                        Status.SUCCESSFUL -> {
                            hideLoadingDialog()
                            discoverResult.data?.let {
                                navigate(
                                    HomeFragmentDirections.actionHomeFragmentToBuyListFragment(
                                        Gson().toJson(it.buyResults)
                                    )
                                )
                            }
                        }
                        Status.ERROR -> {
                            hideLoadingDialog()
                        }
                        Status.LOADING -> {
                            showLoadingDialog()
                        }
                    }

                }
            })
        }
    }

    override fun initConfig() {
        viewModel.apply {
            addSellList(BuyItemResult().apply {
                this.id = 1
                this.price = BigDecimal.valueOf(1235456)
                this.name = "Test 1"
                this.type = 2
                this.quantity = 4
            })
            addSellList(BuyItemResult().apply {
                this.id = 2
                this.price = BigDecimal.valueOf(543453)
                this.name = "Test 2"
                this.type = 2
                this.quantity = 45
            })
            addSellList(BuyItemResult().apply {
                this.id = 3
                this.price = BigDecimal.valueOf(3356546)
                this.name = "Test 3"
                this.type = 2
                this.quantity = 67
            })
        }
    }

}